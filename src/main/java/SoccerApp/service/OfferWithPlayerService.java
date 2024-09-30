package SoccerApp.service;

import SoccerApp.dto.request.OfferWithPlayerRequestDto;
import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.entity.combinedEntity.OfferWithManager;
import SoccerApp.entity.combinedEntity.OfferWithPlayer;
import SoccerApp.entity.combinedEntity.Transfer;
import SoccerApp.repository.OfferWithPlayerRepository;
import SoccerApp.utility.enums.PlayerOfferStatus;

import java.time.LocalDate;
import java.util.List;

public class OfferWithPlayerService extends BaseService<OfferWithPlayer, Long> {
	private OfferWithPlayerRepository repository;
	private static OfferWithPlayerService instance;
	public static OfferWithPlayerService getInstance() {
		if (instance == null) {
			instance = new OfferWithPlayerService();
		}
		return instance;
	}
	private OfferWithPlayerService() {
		super(OfferWithPlayerRepository.getInstance());
		this.repository = OfferWithPlayerRepository.getInstance();
	}
	
	public OfferWithPlayer owmToOwp(OfferWithManager offer) {
		return OfferWithPlayer.builder()
				.offerDate(LocalDate.now())
				.offerWithManager(offer)
				.status(PlayerOfferStatus.PLAYER_OFFER)
				.build();
	}
	
	public void saveOwmAsOwp(OfferWithManager offer) {
		var owp = owmToOwp(offer);
		owp.setOfferedSalary(0.);
		repository.save(owp);
	}
	
	public List<OfferWithPlayer> showOwpBox(Manager manager) {
		return repository.findOwpByBuyingManager(manager);
	}
	
	public void acceptOwp(OfferWithPlayer owp) {
		owp.setStatus(PlayerOfferStatus.ACCEPTED);
		update(owp);
		Transfer transfer = Transfer.builder().contractDate(LocalDate.now()).offer(owp).build();
		TransferService.getInstance().save(transfer);
	}
	
	public void rejectOwp(OfferWithPlayer owp) {
		owp.setStatus(PlayerOfferStatus.REFUSED);
		update(owp);
	}
	
	public void counterOffer(OfferWithPlayerRequestDto owpReqDto) {
		var oldOwp = owpReqDto.getOwp();
		var newSalary = owpReqDto.getNewSalary();
		OfferWithPlayer newOwp =
				OfferWithPlayer.builder().offerDate(LocalDate.now()).offerWithManager(oldOwp.getOfferWithManager())
				               .offeredSalary(newSalary).contractEndDate(oldOwp.getContractEndDate()).build();
		if (oldOwp.getStatus() == PlayerOfferStatus.PLAYER_OFFER) {
			oldOwp.setStatus(PlayerOfferStatus.COUNTERED_PLAYER_OFFER);
			newOwp.setStatus(PlayerOfferStatus.BUYER_OFFER);
		}
		else{
			oldOwp.setStatus(PlayerOfferStatus.COUNTERED_BUYER_OFFER);
			newOwp.setStatus(PlayerOfferStatus.PLAYER_OFFER);
		}
		update(oldOwp);
		save(newOwp);
	}
}