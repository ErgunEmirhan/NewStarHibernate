package SoccerApp.service;

import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.repository.MatchStatisticsRepository;
import SoccerApp.utility.enums.ScorerSide;

import java.util.ArrayList;
import java.util.List;

public class MatchStatisticsService extends BaseService<MatchStatistics,Long> {
	private static MatchStatisticsService instance;
	private MatchStatisticsRepository repository;
	public static MatchStatisticsService getInstance() {
		if (instance == null) {
			instance = new MatchStatisticsService();
		}
		return instance;
	}
	private MatchStatisticsService() {
		super(MatchStatisticsRepository.getInstance());
		repository = MatchStatisticsRepository.getInstance();
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
	
	public void updateStatsForMatch(Match match, ScorerSide scorerSide) {
		MatchStatistics homeStat = findHomeStat(match);
		MatchStatistics awayStat = findAwayStat(match);
		
		switch (scorerSide){
			case ScorerSide.NONE:
				homeStat.setDrawCount(homeStat.getDrawCount() + 1);
				awayStat.setDrawCount(awayStat.getDrawCount() + 1);
				break;
			case ScorerSide.HOME:
				homeStat.setGoalsForCount(homeStat.getGoalsForCount() + 1);
				awayStat.setGoalsAgainstCount(awayStat.getGoalsAgainstCount() + 1);
				if (match.getHomeScore() == match.getAwayScore()){
					homeStat.setDrawCount(homeStat.getDrawCount() + 1);
					homeStat.setLoseCount(homeStat.getLoseCount() - 1);
					
					awayStat.setDrawCount(awayStat.getDrawCount() + 1);
					awayStat.setWinCount(awayStat.getWinCount() - 1);
				}
				else if (match.getHomeScore() - match.getAwayScore() == 1){
					homeStat.setDrawCount(homeStat.getDrawCount() - 1);
					homeStat.setWinCount(homeStat.getWinCount() + 1);
					
					awayStat.setDrawCount(awayStat.getDrawCount() - 1);
					awayStat.setLoseCount(awayStat.getLoseCount() + 1);
				}
				break;
			case ScorerSide.AWAY:
				awayStat.setGoalsForCount(awayStat.getGoalsForCount() + 1);
				homeStat.setGoalsAgainstCount(homeStat.getGoalsAgainstCount() + 1);
				if (match.getHomeScore() == match.getAwayScore()){
					awayStat.setDrawCount(awayStat.getDrawCount() + 1);
					awayStat.setLoseCount(awayStat.getLoseCount() - 1);
					
					homeStat.setDrawCount(homeStat.getDrawCount() + 1);
					homeStat.setWinCount(homeStat.getWinCount() - 1);
				}
				else if (match.getAwayScore() - match.getHomeScore() == 1){
					awayStat.setDrawCount(awayStat.getDrawCount() - 1);
					awayStat.setWinCount(awayStat.getWinCount() + 1);
					
					homeStat.setDrawCount(homeStat.getDrawCount() - 1);
					homeStat.setLoseCount(homeStat.getLoseCount() + 1);
				}
				break;
		}
	}
	
	private MatchStatistics findHomeStat(Match match) {
		return repository.findByClubAndLeague(match.getHome(), match.getLeague());
	}
	
	private MatchStatistics findAwayStat(Match match) {
		return repository.findByClubAndLeague(match.getAway(), match.getLeague());
	}
}