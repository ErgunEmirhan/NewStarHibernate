package SoccerApp.service;

import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.repository.MatchStatisticsRepository;

import java.util.ArrayList;
import java.util.List;

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
	
	public void createMatchStatistics(League league) {
		List<Club> clubs = league.getClubs();
		List<MatchStatistics> stats = new ArrayList<>();
		for (Club club: clubs){
			var matchStatistics = MatchStatistics.builder()
					.club(club).league(league).build();
			stats.add(matchStatistics);
		}
		repository.saveAll(stats);
	}
}