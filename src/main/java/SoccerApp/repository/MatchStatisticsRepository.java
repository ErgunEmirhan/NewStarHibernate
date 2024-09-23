package SoccerApp.repository;

import SoccerApp.entity.MatchStatistics;

public class MatchStatisticsRepository extends BaseRepository<MatchStatistics, Long> {
	private static MatchStatisticsRepository instance;

	public static MatchStatisticsRepository getInstance() {
		if (instance == null) {
			instance = new MatchStatisticsRepository();
		}
		return instance;
	}
	
	public MatchStatisticsRepository() {
		super(MatchStatistics.class);
	}
}