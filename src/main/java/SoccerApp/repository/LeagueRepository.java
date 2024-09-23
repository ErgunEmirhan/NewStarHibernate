package SoccerApp.repository;

import SoccerApp.entity.League;

public class LeagueRepository extends BaseRepository<League, Long> {
	private static LeagueRepository instance;
	public static LeagueRepository getInstance() {
		if (instance == null) {
			instance = new LeagueRepository();
		}
		return instance;
	}
	
	private LeagueRepository() {
		super(League.class);
	}
}