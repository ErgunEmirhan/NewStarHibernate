package SoccerApp.service;

import SoccerApp.dto.request.OfferWithManagerRequestDto;
import SoccerApp.entity.*;
import SoccerApp.repository.TransferRepository;
import SoccerApp.utility.enums.ManagerOfferStatus;

import java.time.LocalDate;

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