package SoccerApp.service;

import SoccerApp.entity.Player;
import SoccerApp.repository.PlayerRepository;

public class PlayerService extends BaseServices<Player,Long> {
	private static PlayerService instance;

	public static PlayerService getInstance() {
		if (instance == null) {
			instance = new PlayerService();
		}
		return instance;
	}
	
	private PlayerService() {
		super(PlayerRepository.getInstance());
	}
}