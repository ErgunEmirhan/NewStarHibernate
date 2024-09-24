package SoccerApp.service;

import SoccerApp.entity.Match;
import SoccerApp.repository.MatchRepository;

public class MatchService extends BaseServices<Match,Long> {
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