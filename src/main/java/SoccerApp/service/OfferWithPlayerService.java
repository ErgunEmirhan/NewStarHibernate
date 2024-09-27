package SoccerApp.service;

import SoccerApp.entity.Manager;
import SoccerApp.entity.OfferWithManager;
import SoccerApp.entity.OfferWithPlayer;
import SoccerApp.repository.OfferWithPlayerRepository;
import SoccerApp.utility.ICRUD;
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
}