package SoccerApp;

import SoccerApp.databases.*;
import SoccerApp.entities.Musabaka;
import SoccerApp.models.DatabaseModel;
import SoccerApp.modules.KulupMod;
import SoccerApp.modules.LigMod;
import SoccerApp.modules.MenajerMod;
import SoccerApp.utility.GeneratorRex;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.*;

public class NewStarSoccerApp {
	private KulupDB kulupDB = DatabaseModel.kulupDataBase;
	private FutbolcuDB futbolcuDB = DatabaseModel.futbolcuDataBase;
	private StadyumDB stadyumDB = DatabaseModel.stadyumDataBase;
	private MenajerDB menajerDB = DatabaseModel.menajerDataBase;
	private HakemDB hakemDB = DatabaseModel.hakemDataBase;
	private LigDB ligDB = DatabaseModel.ligDataBase;
	private Scanner scanner = new Scanner(System.in);
	private Thread otoKayit;
	private static NewStarSoccerApp nssApp = new NewStarSoccerApp();
	private KulupMod kulupMod = KulupMod.getInstance();
	private MenajerMod menajerMod = MenajerMod.getInstance();
	private LigMod ligMod = LigMod.getInstance();
	
	public static NewStarSoccerApp getInstance() {
		return nssApp;
	}
	private NewStarSoccerApp() {
	}
	
	public static void main(String[] args) {
		
		
		System.out.println("Program başlatılıyor");
		nssApp.baslatVeYurutVerileri();
		nssApp.otoKayitThread();
		//TODO menuleri tek bir cati altina topla menu(String menuMsg, Method menuSecenekleri)
		nssApp.nssMenu();
		
		/*List<Integer> takimIdlerListesi = new ArrayList<>(List.of(10, 25, 47, 130, 33, 44));
		List<Integer> indexes = new ArrayList<>();
		Collections.shuffle(takimIdlerListesi);
		
		for (int i = 0; i < takimIdlerListesi.size(); i++) {
			indexes.add(i);
		}
		
		List<Musabaka> fikstur = new ArrayList<>();
		Musabaka musabaka = new Musabaka();
		
		musabaka.setEvSahibiID(String.valueOf(takimIdlerListesi.get(0)));
		musabaka.setDeplasmanID(String.valueOf(takimIdlerListesi.get(1)));
		
		System.out.println(musabaka);
		Map<Integer, List<Musabaka>> fikstur = new HashMap<>();*/
	}
	
	private void otoKayitThread() {
		otoKayit = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(60000);
					GeneratorRex.kaydetTumVerileri(); // WIN + .
					System.out.println("\u001B[32m⚽🏃Basarili otomatik kaydedildi👁️👄👁️\u001B[0m");
				}
				catch (InterruptedException e) {
					break;
				}
			}
		});
		otoKayit.setDaemon(true);
		otoKayit.start();
	}
	
	private void baslatVeYurutVerileri() {
		System.out.println("Veriler yukleniyor...");
		ataModlaraDatabaseleri();
		getirBinarydenJavaya();
		System.out.println("Yuklenme tamamlandi");
	}
	
	private void yukleIO() {
		GeneratorRex.yaratStadyumIO();
		GeneratorRex.yaratMenajerlerIO();
		GeneratorRex.yaratKulupIO();
		GeneratorRex.yaratHakemlerIO();
		GeneratorRex.yaratFutbolcularIO();
	}
	
	private void getirBinarydenJavaya() {
		getirKulupler();
		getirFutbolcular();
		getirMenajerler();
		getirHakemler();
		getirStadyumlar();
	}
	
	private void ataModlaraDatabaseleri() {
		ataKulupModaDatabaseleri();
		ataMenajerModaDatabaseleri();
	}
	
	private void ataMenajerModaDatabaseleri() {
		menajerMod.setKulupDatabase(kulupDB);
		menajerMod.setFutbolcuDatabase(futbolcuDB);
		menajerMod.setStadyumDatabase(stadyumDB);
		menajerMod.setHakemDatabase(hakemDB);
		menajerMod.setMenajerDatabase(menajerDB);
	}
	
	private void ataKulupModaDatabaseleri() {
		kulupMod.setKulupDatabase(kulupDB);
		kulupMod.setFutbolcuDatabase(futbolcuDB);
		kulupMod.setStadyumDatabase(stadyumDB);
		kulupMod.setHakemDatabase(hakemDB);
		kulupMod.setMenajerDatabase(menajerDB);
	}
	
	private void getirHakemler() {
		GeneratorRex.setHakemDB(hakemDB);
		GeneratorRex.getirHakemler();
	}
	
	private void getirMenajerler() {
		GeneratorRex.setMenajerDB(menajerDB);
		GeneratorRex.getirMenajerler();
	}
	
	private void getirKulupler() {
		GeneratorRex.setKulupDb(kulupDB);
		GeneratorRex.getirKulupler();
	}
	
	private void getirStadyumlar() {
		GeneratorRex.setStadyumDB(stadyumDB);
		GeneratorRex.getirStadyumlar();
	}
	
	private void getirFutbolcular() {
		GeneratorRex.setFutbolcuDB(futbolcuDB);
		GeneratorRex.getirFutbolcular();
	}
	
	private int nssMenu() {
		int secim;
		do {
			System.out.println("""
					                   #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
					                           1. Kulüp Modül
					                           2. Menajer Modül
					                           3. Lig Modül
					                           0. Geri Dön
					                          -1. Çıkış
					                           """);
			secim = yapSecim();
			if (secim == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			secim = nssApp.nssMenuSecenekleri(secim);
		} while (secim != -1);
		return secim;
		
		
	}
	
	private int nssMenuSecenekleri(int secim) {
		switch (secim) {
			case 1:
				return kulupMod.menu();
			case 2:
				return menajerMod.girisYapMenajerMod();
			case 3:
				return ligMod.menu();
			case -1:
				System.out.println("Uygulama sonlandırılıyor....");
				otoKayit.interrupt();
				GeneratorRex.kaydetTumVerileri();
				return secim;
		}
		return secim;
	}
	
	public int yapSecim(String message) {
		int secim;
		while (true) {
			System.out.print(message);
			try {
				secim = scanner.nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("Gecersiz girdi, lütfen integer giriniz");
			}
			finally {
				scanner.nextLine();
			}
		}
		return secim;
	}
	
	public int yapSecim() {
		return yapSecim("Secim yapiniz: ");
	}
	
}