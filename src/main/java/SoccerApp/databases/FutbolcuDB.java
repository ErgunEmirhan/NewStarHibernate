package SoccerApp.databases;

import SoccerApp.entities.Futbolcu;
import SoccerApp.util.DatabaseManager;
import SoccerApp.util.enums.EMevki;
import SoccerApp.util.enums.EUyruk;

import java.time.LocalDate;
import java.util.List;

public class FutbolcuDB extends DatabaseManager<Futbolcu> {
	private static FutbolcuDB futbolcuDB=new FutbolcuDB();

	public static FutbolcuDB getInstance(){
		return futbolcuDB;
	}
	private FutbolcuDB() {
	}
	
	public boolean yaratFutbolcu(String tempAd, String tempSoyad, LocalDate tempDogumTarihi, EUyruk tempUyruk,
	                             String tempMaas, int tempFormaNumarasi, String tempBonservis, EMevki tempMevki,
	                             int tempYetenekPuani, String tempKulupId){
		Futbolcu newFutbolcu =
				new Futbolcu(tempAd, tempSoyad, tempDogumTarihi, tempUyruk, tempMaas, tempFormaNumarasi, tempBonservis, tempMevki, tempYetenekPuani, tempKulupId, getNextId());
		
		return true;
	}
	
	
	
	public List<Futbolcu> bulFutbolcularKulupId(String kulupId){
		return veriListesi.stream()
				.filter(futbolcu -> futbolcu.getKulupId().isPresent())
				.filter(futbolcu -> futbolcu.getKulupId().get().equals(kulupId))
				.toList();
	}
}