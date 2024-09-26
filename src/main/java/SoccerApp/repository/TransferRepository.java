package SoccerApp.repository;

import SoccerApp.entity.Offer;

public class TransferRepository extends BaseRepository<Offer,Long> {
	private static TransferRepository instance;
	public static TransferRepository getInstance() {
		
		if (instance == null) {
			instance = new TransferRepository();
		}
		return instance;
	}
	private TransferRepository() {
		super(Offer.class);
	}
	
}