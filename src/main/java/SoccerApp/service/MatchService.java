package SoccerApp.service;

import SoccerApp.dto.request.MatchByClubAndLeagueDtoRequest;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.repository.MatchRepository;

import java.util.List;

public class MatchService extends BaseService<Match,Long> {
	private static MatchService instance;
	private MatchRepository repository;
	public static MatchService getInstance() {
		if (instance == null) {
			instance = new MatchService();
		}
		return instance;
	}
	private MatchService() {
		super(MatchRepository.getInstance());
		repository = MatchRepository.getInstance();
	}
	
	public List<Match> findByClubAndLeague(MatchByClubAndLeagueDtoRequest request) {
		Club club = request.getClub();
		League league = request.getLeague();
		List<Match> matches = repository.findByClubAndLeagueId(club, league);
		return matches;
		
	}
}