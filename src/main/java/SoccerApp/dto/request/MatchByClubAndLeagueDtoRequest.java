package SoccerApp.dto.request;

import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;

public class MatchByClubAndLeagueDtoRequest {
	private Club club;
	private League league;
	
	public MatchByClubAndLeagueDtoRequest(Club club, League league) {
		this.club = club;
		this.league = league;
	}
	
	public Club getClub() {
		return club;
	}
	
	public League getLeague() {
		return league;
	}
}