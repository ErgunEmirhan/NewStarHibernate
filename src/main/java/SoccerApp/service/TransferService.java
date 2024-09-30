package SoccerApp.service;

import SoccerApp.entity.combinedEntity.Transfer;
import SoccerApp.repository.TransferRepository;

public class TransferService extends BaseService<Transfer,Long> {
	private static TransferService instance;
	private TransferRepository transferRepository;
	public static TransferService getInstance() {
		if (instance == null) {
			instance = new TransferService();
		}
		return instance;
	}
	private TransferService() {
		super(TransferRepository.getInstance());
		transferRepository=TransferRepository.getInstance();
	}
	
	
}