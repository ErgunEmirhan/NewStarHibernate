package SoccerApp.service;

import SoccerApp.entity.mainEntity.League;
import SoccerApp.repository.LeagueRepository;

public class LeagueService extends BaseService<League,Long> {
	private static LeagueService instance;

	public static LeagueService getInstance() {
		if (instance == null) {
			instance = new LeagueService();
		}
		return instance;
	}
	private LeagueService() {
		super(LeagueRepository.getInstance());
	}
}