package SoccerApp.service;

import SoccerApp.entity.combinedEntity.Match;
import SoccerApp.repository.MatchRepository;

public class MatchService extends BaseService<Match,Long> {
	private static MatchService instance;

	public static MatchService getInstance() {
		if (instance == null) {
			instance = new MatchService();
		}
		return instance;
	}
	private MatchService() {
		super(MatchRepository.getInstance());
	}
}