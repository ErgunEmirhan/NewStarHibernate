package SoccerApp.service;

import SoccerApp.dto.request.TransferRequestDto;
import SoccerApp.entity.Club;
import SoccerApp.entity.Manager;
import SoccerApp.entity.Player;
import SoccerApp.entity.Offer;
import SoccerApp.repository.TransferRepository;

public class TransferService extends BaseServices<Offer,Long>{
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
	
	public void makeTransferRequest(TransferRequestDto transferRequestDto) {
		Manager offeringManager = transferRequestDto.getManager();
		Club offeringClub = offeringManager.getClub();
		
		Double transferFee = transferRequestDto.getTransferFee();
		Player player = transferRequestDto.getPlayer();
		
		Club receivingClub = player.getClub();
		Manager receivingManager = receivingClub.getManager();
		
		Offer transfer =
				Offer.builder().offeringClub(offeringClub).receivingClub(receivingClub)
				     .transferFee(transferFee).offeringManager(offeringManager)
				     .receivingManager(receivingManager).player(player).build();
		
		save(transfer);
	}
}