package SoccerApp.services;

import SoccerApp.entity.MatchStatistics;
import SoccerApp.repository.MatchStatisticsRepository;

public class MatchStatisticsService extends BaseServices<MatchStatistics,Long> {
	private static MatchStatisticsService instance;
	public static MatchStatisticsService getInstance() {
		if (instance == null) {
			instance = new MatchStatisticsService();
		}
		return instance;
	}
	private MatchStatisticsService() {
		super(MatchStatisticsRepository.getInstance());
	}
}