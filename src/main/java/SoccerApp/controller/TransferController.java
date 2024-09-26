package SoccerApp.controller;

import SoccerApp.dto.request.TransferRequestDto;
import SoccerApp.entity.Offer;
import SoccerApp.service.TransferService;

public class TransferController extends BaseController<Offer,Long> {
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
	
	public void makeTransferRequest(TransferRequestDto transferRequestDto) {
		try {
			service.makeTransferRequest(transferRequestDto);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}