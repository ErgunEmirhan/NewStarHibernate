package SoccerApp.repository;

import SoccerApp.entity.mainEntity.Match;

public class MatchRepository extends BaseRepository<Match, Long> {
	private static MatchRepository instance;
	public static MatchRepository getInstance() {
		if (instance == null) {
			instance = new MatchRepository();
		}
		return instance;
	}
	
	private MatchRepository() {
		super(Match.class);
	}
}