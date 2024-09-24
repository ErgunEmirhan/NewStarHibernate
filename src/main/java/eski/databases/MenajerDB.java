package eski.databases;

import eski.entities.Menajer;
import eski.util.DatabaseManager;
import eski.util.enums.EUyruk;

import java.time.LocalDate;

public class MenajerDB extends DatabaseManager<Menajer> {
	private static MenajerDB menajerDB = new MenajerDB();
	private MenajerDB() {
	}
	public static MenajerDB getInstance() {
		return menajerDB;
	}
	public boolean yaratMenajer(String tempAd, String tempSoyad, LocalDate tempDogumTarihi, EUyruk tempUyruk,
	                            String tempMaas, String tempKulupID,
	                            int tempYil, String tempSifre) {
		String nextId = getNextId();
		Menajer newMenajer = new Menajer(tempAd,tempSoyad,tempDogumTarihi,tempUyruk,tempMaas,tempKulupID, tempYil,
		                                nextId , tempSifre);
		
		return true;
	}
}