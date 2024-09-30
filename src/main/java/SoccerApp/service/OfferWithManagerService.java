package SoccerApp.service;

import SoccerApp.dto.request.OfferWithManagerRequestDto;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.entity.combinedEntity.OfferWithManager;
import SoccerApp.entity.mainEntity.Player;
import SoccerApp.repository.OfferWithManagerRepository;
import SoccerApp.utility.enums.ManagerOfferStatus;

import java.time.LocalDate;
import java.util.List;

public class OfferWithManagerService extends BaseService<OfferWithManager, Long> {
	private static OfferWithManagerService instance;
	private OfferWithManagerRepository repository;
	public static OfferWithManagerService getInstance() {
		if (instance == null) {
			instance = new OfferWithManagerService();
		}
		return instance;
	}
	private OfferWithManagerService() {
		super(OfferWithManagerRepository.getInstance());
		repository = OfferWithManagerRepository.getInstance();
	}
	public void makeTransferRequest(OfferWithManagerRequestDto transferRequestDto) {
		Manager buyerManager = transferRequestDto.getManager();
		Club buyerClub = buyerManager.getClub();
		
		Double transferFee = transferRequestDto.getTransferFee();
		Player player = transferRequestDto.getPlayer();
		LocalDate offerDate = LocalDate.now();
		ManagerOfferStatus offerStatus = ManagerOfferStatus.ACCEPTED;
		
		Club ownerClub = player.getClub();
		Manager ownerManager = ownerClub.getManager();
		
		var owm = OfferWithManager.builder()
		                          .transferFee(transferFee)
		                          .buyerManager(buyerManager)
		                          .offerDate(offerDate)
		                          .buyerClub(buyerClub)
		                          .ownerClub(ownerClub)
		                          .ownerManager(ownerManager)
		                          .offerStatus(offerStatus)
		                          .player(player)
		                          .build();
		
		save(owm);
	}
	
	public List<OfferWithManager> findByReceivingManager(Manager manager) {
		return repository.findByReceivingManager(manager);
	}
	
	public void acceptOffer(OfferWithManager offer) {
		offer.setOfferStatus(ManagerOfferStatus.ACCEPTED);
		OfferWithPlayerService.getInstance().saveOwmAsOwp(offer);
		update(offer);
	}
	
	public void refuseOffer(OfferWithManager offer) {
		offer.setOfferStatus(ManagerOfferStatus.REFUSED);
		update(offer);
	}
}