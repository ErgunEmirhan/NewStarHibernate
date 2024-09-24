package eski.util;

import eski.entities.*;
import eski.models.DatabaseModel;
import eski.util.enums.EKokart;
import eski.util.enums.EMevki;
import eski.util.enums.EUyruk;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeneratorRex {
	
	private static DatabaseModel databaseModel=DatabaseModel.getInstance();
	
	
	
	
	public static void kaydetTumVerileri(){
		kaydetFutbolcularVerileri();
		kaydetMenajerlerVerileri();
		kaydetHakemlerVerileri();
		kaydetKuluplerVerileri();
		kaydetStadyumlarVerileri();
		kaydetLigVerileri();
		kaydetMusabakaVerileri();
		kaydetIstatistiklerVeriler();
	}
	
	public static void kaydetHakemlerVerileri(){
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\hakemler.bin"))){
			oos.writeObject(databaseModel.hakemDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetIstatistiklerVeriler(){
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\istatistikler.bin"))){
			oos.writeObject(databaseModel.istatistikDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetKuluplerVerileri(){
		try(ObjectOutputStream oos =
				    new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\kulupler.bin"))){
			oos.writeObject(databaseModel.kulupDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetFutbolcularVerileri(){
		try(ObjectOutputStream oos =
				    new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\futbolcular.bin"))){
			oos.writeObject(databaseModel.futbolcuDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetStadyumlarVerileri(){
		try(ObjectOutputStream oos=
				    new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\stadyumlar.bin"))){
			oos.writeObject(databaseModel.stadyumDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetLigVerileri(){
		try(ObjectOutputStream oos=
				    new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\ligler.bin"))){
			oos.writeObject(databaseModel.ligDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetMusabakaVerileri(){
		try(ObjectOutputStream oos=
				    new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\musabakalar.bin"))){
			oos.writeObject(databaseModel.musabakaDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void kaydetMenajerlerVerileri(){
		try(ObjectOutputStream oos=
				    new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\menajerler.bin"))){
			oos.writeObject(databaseModel.menajerDataBase.veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void getirLigler(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\ligler.bin"))){
			databaseModel.ligDataBase.saveAll((ArrayList<Lig>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getirIstatistikler(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\istatistikler.bin"))){
			databaseModel.istatistikDataBase.saveAll((ArrayList<Istatistik>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getirHakemler(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\hakemler.bin"))){
			databaseModel.hakemDataBase.saveAll((ArrayList<Hakem>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getirMenajerler(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\menajerler.bin"))){
			databaseModel.menajerDataBase.saveAll((ArrayList<Menajer>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getirFutbolcular(){ //
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\futbolcular.bin"))){
			databaseModel.futbolcuDataBase.saveAll((ArrayList<Futbolcu>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getirStadyumlar(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\stadyumlar.bin"))){
			databaseModel.stadyumDataBase.saveAll((ArrayList<Stadyum>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getirMusabakalar(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\musabakalar.bin"))){
			databaseModel.musabakaDataBase.saveAll((ArrayList<Musabaka>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getirKulupler(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\SoccerApp\\build\\kulupler.bin"))){
			databaseModel.kulupDataBase.saveAll((ArrayList<Kulup>)ois.readObject());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static List<Hakem> yaratHakemlerIO() {
		List<Hakem> hakemList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\hakemler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\hakemler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Hakem tempHakem = new Hakem();
				tempHakem.setId(String.valueOf(count));
				System.out.println(count);
				tempHakem.setAd(split[0].trim());
				tempHakem.setSoyad(split[1].trim());
				tempHakem.setDogumTarihi(LocalDate.parse(split[2].trim()));
				tempHakem.setUyruk(EUyruk.valueOf(split[3].trim()));
				tempHakem.setMaas(split[4].trim());
				tempHakem.setKokart(EKokart.valueOf(split[5].trim()));
				hakemList.add(tempHakem);
				count++;
			}
			oos.writeObject(hakemList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return hakemList;
	}
	public static List<Menajer> yaratMenajerlerIO() {
		List<Menajer> menajerList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\menajerler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\menajerler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Menajer tempMenajer = new Menajer();
				tempMenajer.setKulupId(String.valueOf(count));
				tempMenajer.setId(String.valueOf(count));
				tempMenajer.setAd(split[1].trim());
				tempMenajer.setSoyad(split[2].trim());
				tempMenajer.setDogumTarihi(LocalDate.parse(split[3].trim()));
				tempMenajer.setUyruk(EUyruk.valueOf(split[4].trim()));
				tempMenajer.setMaas(split[5].trim());
				tempMenajer.setSozlesmeBitisTarihi(LocalDate.parse(split[6].trim()));
				tempMenajer.setSifre("a" + count);
				menajerList.add(tempMenajer);
				count++;
			}
			oos.writeObject(menajerList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return menajerList;
	}
	public static List<Futbolcu> yaratFutbolcularIO() {
		List<Futbolcu> futbolcuList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\futbolcular.txt"))) {
			int count = 1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				
				String tempAd, tempSoyad, tempMaas, tempBonservis, tempKulupId;
				EUyruk tempUyruk;
				EMevki tempMevki;
				Integer tempFormaNumarasi, tempYetenekPuani;
				LocalDate tempDogumTarihi;
				tempAd = split[0].trim();
				tempSoyad = split[1].trim();
				tempDogumTarihi = LocalDate.parse(split[2].trim());
				tempMaas = (split[3].trim());
				tempUyruk = EUyruk.valueOf((split[4].trim()));
				tempFormaNumarasi = (((count) - (count % 100)) / 100) + 1;
				tempBonservis = (split[6].trim());
				tempMevki = EMevki.valueOf((split[7].trim()));
				tempMevki = (count <= 200)? EMevki.KALECI: tempMevki;
				tempYetenekPuani = Integer.parseInt((split[8].trim()));
				tempKulupId = String.valueOf((count % 100) + 1);
				futbolcuList.add(new Futbolcu(tempAd, tempSoyad, tempDogumTarihi, tempUyruk, tempMaas,
				                              tempFormaNumarasi, tempBonservis, tempMevki, tempYetenekPuani,
				                              tempKulupId, String.valueOf(count++)));
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\futbolcular.bin"))) {
			oos.writeObject(futbolcuList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return futbolcuList;
	}
	public static List<Stadyum> yaratStadyumIO() {
		List<Stadyum> stadyumList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\stadyumlar.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\stadyumlar.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Stadyum tempStadyum = new Stadyum();
				tempStadyum.setId(String.valueOf(count));
				tempStadyum.setAd(split[1].trim());
				tempStadyum.setKapasite(Integer.valueOf(split[0].trim()));
				stadyumList.add(tempStadyum);
				count++;
			}
			oos.writeObject(stadyumList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return stadyumList;
	}
	
	public static List<Kulup> yaratKulupIO() {
		List<Kulup> futbolcularList = new ArrayList<>();
		try (BufferedReader fr = new BufferedReader(new FileReader("src\\SoccerApp\\resources\\kulupler.txt"));ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream("src\\SoccerApp\\build\\kulupler.bin"))) {
			int count=1;
			while (true) {
				String str = fr.readLine();
				if (str == null) break;
				String[] split = str.split(",");
				Kulup tempKulup = new Kulup();
				tempKulup.setId(String.valueOf(count));
				tempKulup.setAd(split[1].trim());
				tempKulup.setKurulusTarihi(split[2].trim());
				tempKulup.setStadyumId(String.valueOf(count++));
				tempKulup.setVarMiMenajer(true);
				tempKulup.setStadyumAdi(split[4].trim());
				tempKulup.setBaskan(split[6].trim());
				tempKulup.setButce(split[7].trim());
				tempKulup.setMaasButceYillik(split[8].trim());
				futbolcularList.add(tempKulup);
			}
			oos.writeObject(futbolcularList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return futbolcularList;
	}
}