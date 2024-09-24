package eski.databases;

import eski.entities.Stadyum;
import eski.util.DatabaseManager;

public class StadyumDB extends DatabaseManager<Stadyum> {
	private static StadyumDB stadyumDB=new StadyumDB();
	private StadyumDB() {
	}
	public static StadyumDB getInstance() {
		return stadyumDB;
	}
	public boolean yaratStadyum(int tempKapasite,String tempAd) {
		Stadyum newStadyum = new Stadyum(tempKapasite, tempAd, getNextId());
		return true;
	}
}