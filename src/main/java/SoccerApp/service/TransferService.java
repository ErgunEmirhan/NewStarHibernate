package SoccerApp.service;

import SoccerApp.entity.Manager;
import SoccerApp.entity.Transfer;
import SoccerApp.repository.TransferRepository;
import SoccerApp.utility.ICRUD;

public class TransferService extends BaseServices<Transfer,Long>{
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
	
	public void makeTransferRequest(Manager manager) {
		transferRepository.makeTransferRequest(manager);
	}
}