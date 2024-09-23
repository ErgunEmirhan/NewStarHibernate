package SoccerApp.repository;

import SoccerApp.entity.Player;

public class PlayerRepository extends BaseRepository<Player, Long> {
	private static PlayerRepository instance;
	public static PlayerRepository getInstance() {
		if (instance == null) {
			instance = new PlayerRepository();
		}
		return instance;
	}
	public PlayerRepository() {
		super(Player.class);
	}
}