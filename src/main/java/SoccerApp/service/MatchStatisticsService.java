package SoccerApp.service;

import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.repository.MatchStatisticsRepository;

public class MatchStatisticsService extends BaseService<MatchStatistics,Long> {
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