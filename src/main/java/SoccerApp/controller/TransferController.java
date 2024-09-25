package SoccerApp.controller;

import SoccerApp.entity.Manager;
import SoccerApp.entity.Transfer;
import SoccerApp.service.TransferService;
import SoccerApp.utility.ICRUDService;

public class TransferController extends BaseController<Transfer,Long> {
	private static TransferController instance;
	private TransferService service;
	public static TransferController getInstance() {
		if (instance == null) {
			instance = new TransferController();
		}
		return instance;
	}
	private TransferController() {
		super(TransferService.getInstance());
		service = TransferService.getInstance();
	}
	
	public void makeTransferRequest(Manager manager) {
		service.makeTransferRequest(manager);
	}
}