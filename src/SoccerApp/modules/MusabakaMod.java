package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.entities.*;
import SoccerApp.models.DatabaseModel;
import SoccerApp.models.LigModel;
import SoccerApp.utility.enums.EGolAtan;
import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.ESkorTablosuDegisimYonu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MusabakaMod {
	private DatabaseModel databaseModel = DatabaseModel.getInstance();
	private LigModel ligModel = new LigModel();
	private static MusabakaMod musabakaMod = new MusabakaMod();
	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();
	
	public static MusabakaMod getInstance(){
		return musabakaMod;
	}
	
	private MusabakaMod(){
	
	}
	
	public int menu(){
		int secim = -1;
		do {
			System.out.println("""
				                  ### Musabaka Mod ###
				                  1. Mac Oynat
				                  0. Geri
				                  -1. Cikis
				                  """);
			secim = NewStarSoccerApp.yapSecim("Seciminiz: ");
			if (secim == 0) break;
			
			secim = menuSecenekleri(secim);
		} while(secim != -1);
		
		return secim;
	}
	
	private int menuSecenekleri(int secim) {
		switch (secim){
			case 1:
				macOynat();
		}
		return secim;
	}
	
	private void macOynat() {
		// TODO ligID almak yerine database'den arat.
		System.out.print("Lig id gir : ");
		String ligId = scanner.nextLine();
		Optional<Lig> optLig = databaseModel.ligDataBase.findByID(ligId);
		if (optLig.isEmpty()) return;
		System.out.print("Musabaka id gir: ");
		var lig=optLig.get();
		String musabakaId = scanner.nextLine();
		Optional<Musabaka> optMus = databaseModel.musabakaDataBase.findByID(musabakaId);
		if (optMus.isEmpty()) return;
		var musabaka = optMus.get();
		
		try {
			macOyna(musabaka, lig);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void playWavFile(String filePath) {
		try {
			File soundFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.start();
			
			audioClip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					audioClip.close();
				}
			});
			
			// Klip çalınırken programın kapanmaması için bekleme ekledim
			while (!audioClip.isRunning()) {
				Thread.sleep(10);
			}
			while (audioClip.isRunning()) {
				Thread.sleep(10);
			}
			
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void gol(String message, EGolAtan golAtan, Musabaka musabaka, Lig lig) {
		System.out.println(message);
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		Takim golAtanTakim = null;
		Takim golYiyenTakim = null;
		switch (golAtan){
			case EV_SAHIBI -> {
				golAtanTakim = musabaka.getEvSahibi();
				golYiyenTakim = musabaka.getDeplasman();
			}
			case DEPLASMAN -> {
				golAtanTakim = musabaka.getDeplasman();
				golYiyenTakim = musabaka.getEvSahibi();
			}
		}
		var atanIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(golAtanTakim.getKulupId(),
		                                                                           lig.getId());
		var yiyenIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(golYiyenTakim.getKulupId(),
		                                                                            lig.getId());
		switch (golYiyenTakim.getSkor() - golAtanTakim.getSkor()){
			case 1:
				atanIst.artirBeraberlik();
				atanIst.azaltMaglubiyet();
				
				yiyenIst.artirBeraberlik();
				yiyenIst.azaltGalibiyet();
				break;
			case 0:
				atanIst.artirGalibiyet();
				atanIst.azaltBeraberlik();
				
				yiyenIst.artirMaglubiyet();
				yiyenIst.azaltBeraberlik();
				break;
		}
		atanIst.artirAtilanGol();
		yiyenIst.artirYenilenGol();
		golAtanTakim.arttirSkor();
		guncellePuanTablosu(lig, atanIst, ESkorTablosuDegisimYonu.YUKARI);
		guncellePuanTablosu(lig, yiyenIst, ESkorTablosuDegisimYonu.ASAGI);
	}
	
	private void kadroBelirle(Musabaka musabaka,Lig lig){
		String evSahibiKulupId = musabaka.getEvSahibi().getKulupId();
		String deplasmanKulupId = musabaka.getDeplasman().getKulupId();
		
		List<Futbolcu> futbolcularEvSahibiKulup = databaseModel.futbolcuDataBase.bulFutbolcularKulupId(evSahibiKulupId);
		List<Futbolcu> futbolcularDeplasmanKulup = databaseModel.futbolcuDataBase.bulFutbolcularKulupId(deplasmanKulupId);
		Map<EMevki,List<Futbolcu>> evSahibiKadroIlkOnBir = new HashMap<>();
		Map<EMevki,List<Futbolcu>> deplasmanIlkOnBir = new HashMap<>();
		
		List<Futbolcu> kalecilerList =
				futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.KALECI).toList();
		evSahibiKadroIlkOnBir.put(EMevki.KALECI,List.of(kalecilerList.get(random.nextInt(0, kalecilerList.size()))));
		List<Futbolcu> deplasmanKalecilerlist =
				futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.KALECI).toList();
		deplasmanIlkOnBir.put(EMevki.KALECI,List.of(deplasmanKalecilerlist.get(random.nextInt(0, kalecilerList.size()))));
		
		List<Futbolcu> evSahibiDefanslistesi =
				futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.DEFANS).toList();
		Collections.shuffle(evSahibiDefanslistesi);
		evSahibiKadroIlkOnBir.put(EMevki.DEFANS,evSahibiDefanslistesi.subList(0,3));
		List<Futbolcu> deplasmanDefansListesi =
				futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.DEFANS).toList();
		Collections.shuffle(deplasmanDefansListesi);
		deplasmanIlkOnBir.put(EMevki.DEFANS,deplasmanDefansListesi.subList(0,3));
		
		List<Futbolcu> evSahibiOrtaSahaListesi =
				futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.ORTASAHA).toList();
		Collections.shuffle(evSahibiOrtaSahaListesi);
		evSahibiKadroIlkOnBir.put(EMevki.ORTASAHA, evSahibiOrtaSahaListesi.subList(0,4));
		
		List<Futbolcu> deplasmanOrtaSahaListesi =
				futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.ORTASAHA).toList();
		Collections.shuffle(deplasmanOrtaSahaListesi);
		deplasmanIlkOnBir.put(EMevki.ORTASAHA, deplasmanOrtaSahaListesi.subList(0,4));
		
		List<Futbolcu> evSahibiForvetListesi =
				futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.FORVET).toList();
		Collections.shuffle(evSahibiForvetListesi);
		evSahibiKadroIlkOnBir.put(EMevki.FORVET, evSahibiForvetListesi.subList(0,3));
		
		List<Futbolcu> deplasmanForvetListesi =
				futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.FORVET).toList();
		Collections.shuffle(deplasmanForvetListesi);
		deplasmanIlkOnBir.put(EMevki.FORVET, deplasmanForvetListesi.subList(0,3));
	}
	
	public void macaBasla(){
	
	}
	
	@Deprecated
	public void macOyna(Musabaka musabaka, Lig lig) throws InterruptedException {
		Random random = new Random();
		
		
		playWavFile("src/SoccerApp/sounds/MacOnu.wav");
		
		String evSahibiID = musabaka.getEvSahibi().getKulupId();
		Optional<Kulup> optEvSahibiKulup = databaseModel.kulupDataBase.findByID(evSahibiID);
		String evSahibiKulupAdi = optEvSahibiKulup.get().getAd();
		
		String deplasmanID = musabaka.getDeplasman().getKulupId();
		Optional<Kulup> optDeplasmanKulup = databaseModel.kulupDataBase.findByID(deplasmanID);
		String deplasmanKulupAdi = optDeplasmanKulup.get().getAd();
		
		
		Istatistik evSahibiIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(evSahibiID, lig.getId());
		evSahibiIst.artirBeraberlik();
		Istatistik deplasmanIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(deplasmanID, lig.getId());
		deplasmanIst.artirBeraberlik();
		playWavFile("src/SoccerApp/sounds/MacBaslangicDudugu.wav");
		
		boolean oyunDevam = true;
		int dakika = 0;
		
		while (oyunDevam) {
			//Thread.sleep(1500);
			dakika += 12;
			System.out.println(dakika + ". dakika");
			
			int olay = random.nextInt(5); // 0 ile 4 arasında rastgele bir tam sayı döner
			switch (olay) {
				case 0:
					gol(evSahibiKulupAdi + " harika bir gol attı!", EGolAtan.EV_SAHIBI, musabaka, lig);
					break;
				case 1:
					gol(deplasmanKulupAdi + " müthiş bir gol attı!", EGolAtan.DEPLASMAN, musabaka, lig);
					break;
				case 2:
					System.out.println(evSahibiKulupAdi + " sarı kart gördü.");
					break;
				case 3:
					System.out.println(deplasmanKulupAdi + " sarı kart gördü.");
					break;
				case 4:
					System.out.println("Top orta sahada gidip geliyor.");
					break;
			}
			
			if (dakika >= 90) {
				playWavFile("src/SoccerApp/sounds/MacBitisDudugu.wav");
				oyunDevam = false;
			}
		}
		
	}
	private void guncellePuanTablosu(Lig lig, Istatistik istatistik, ESkorTablosuDegisimYonu degisim){
		String yukariId;
		String istId = istatistik.getId();
		int yukariSiralama = -1;
		switch(degisim){
			case YUKARI -> {
				yukariSiralama = ligModel.getirSiralamaVerIstatistikId(lig, istId) - 1;
			}
			case ASAGI -> {
				yukariSiralama = ligModel.getirSiralamaVerIstatistikId(lig, istId);
			}
		}
		if (yukariSiralama < 1) return;
		else if(yukariSiralama == lig.getMaksLigTakimSayisi()) return;
		
		
		
		yukariId = lig.getPuanTablosu().get(yukariSiralama);
		String asagiId = lig.getPuanTablosu().get(yukariSiralama + 1);
		
		Istatistik yukariIst = databaseModel.istatistikDataBase.findByID(yukariId).get();
		Istatistik asagiIst = databaseModel.istatistikDataBase.findByID(asagiId).get();
		
		if (yukariIst.getPuan() > asagiIst.getPuan()) return;
		else if (yukariIst.getPuan() == asagiIst.getPuan() && yukariIst.getAveraj() > asagiIst.getAveraj()) return;
		else {
			lig.getPuanTablosu().replace(yukariSiralama, asagiId);
			lig.getPuanTablosu().replace(yukariSiralama + 1, yukariId);
			guncellePuanTablosu(lig,istatistik,degisim);
		}
	}
}

/*
istId1, istId2
k1    ,   k2
p1 p2  -> p2 > p1
tempId = tempId1
replace(k1, istId2)
replace(k2, tempId)


 */