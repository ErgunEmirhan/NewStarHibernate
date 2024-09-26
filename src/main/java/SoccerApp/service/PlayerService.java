package SoccerApp.service;

import SoccerApp.entity.Player;
import SoccerApp.repository.PlayerRepository;
import SoccerApp.utility.enums.Nationality;
import SoccerApp.utility.enums.Position;

import java.util.List;
import java.util.Set;

public class PlayerService extends BaseServices<Player,Long> {
	private static PlayerService instance;
	private PlayerRepository repository;

	public static PlayerService getInstance() {
		if (instance == null) {
			instance = new PlayerService();
		}
		return instance;
	}
	
	private PlayerService() {
		super(PlayerRepository.getInstance());
		repository = PlayerRepository.getInstance();
	}
	
	public List<Player> findByNationality(Nationality nationality) {
		return findByFieldNameAndValue("nationality", nationality);
	}
	
	public List<Player> findByPosition(Position position) {
		return findByFieldNameAndValue("position", position);
	}
	
	public List<Player> findBetweenRatings(int minRating, int maxRating) {
		return repository.findBetweeenRatings(minRating, maxRating);
	}
	
	public List<Player> findBetweenTransferFees(double minTransferFee, double maxTransferFee) {
		return repository.findBetweenTransferFees(minTransferFee, maxTransferFee);
	}
	
	public List<Player> findBetweenAges(int minAge, int maxAge) {
		return repository.findBetweenAges(minAge, maxAge);
	}
}