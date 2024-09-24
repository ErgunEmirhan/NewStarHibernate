package eski.databases;


import eski.entities.Lig;
import eski.models.DatabaseModel;
import eski.util.DatabaseManager;

import java.util.List;
import java.util.Optional;

public class LigDB extends DatabaseManager<Lig> {
	private static LigDB ligDB = new LigDB();
	private LigDB() {}
	public static LigDB getInstance() {
		return ligDB;
	}
	private DatabaseModel databaseModel = DatabaseModel.getInstance();
	
	
	public boolean ekleKulup(String ligID, String kulupID){
		
		Optional<Lig> optionalLig = findByID(ligID);
		if (optionalLig.isEmpty()){
			return false;
		}
		Lig lig = optionalLig.get();
		if (lig.getTakimlarIDList().size() < lig.getMaksLigTakimSayisi()){
			return lig.ekleTakimlarIDListeye(kulupID);
		}
		return false;
	}
	
	public List<String> listeleLigdekiKulupleri(String ligID){
		Optional<Lig> optionalLig = findByID(ligID);
		if (optionalLig.isEmpty()){
			return null;
		}
		return optionalLig.get().getTakimlarIDList();
	}
	
}