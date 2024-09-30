package SoccerApp.repository;

import SoccerApp.entity.combinedEntity.Transfer;

public class TransferRepository extends BaseRepository<Transfer,Long> {
	private static TransferRepository instance;
	public static TransferRepository getInstance() {
		
		if (instance == null) {
			instance = new TransferRepository();
		}
		return instance;
	}
	private TransferRepository() {
		super(Transfer.class);
	}
	
}