package SoccerApp.controller;

import SoccerApp.dto.request.OfferWithManagerRequestDto;
import SoccerApp.entity.Transfer;
import SoccerApp.service.TransferService;

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
	
	
}