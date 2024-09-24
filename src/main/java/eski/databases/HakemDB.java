package eski.databases;

import eski.entities.Hakem;
import eski.util.DatabaseManager;
import eski.util.enums.EKokart;
import eski.util.enums.EUyruk;

import java.time.LocalDate;

public class HakemDB extends DatabaseManager<Hakem> {
	private static HakemDB hakemDB = new HakemDB();
	private HakemDB() {
	
	}
	public static HakemDB getInstance() {
		return hakemDB;
	}
	public boolean yaratHakem(String tempAd, String tempSoyad, LocalDate tempDogumTarihi, EUyruk tempUyruk,
	                          String tempMaas, EKokart tempKokart) {
		Hakem newHakem = new Hakem(tempAd, tempSoyad, tempDogumTarihi, tempUyruk, tempMaas, tempKokart, getNextId());
		
		return true;
	}
}