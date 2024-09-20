package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


public class MatchStatistics {
	
	private MatchStatisticsPk matchStatisticsPk;
	private int winCount;
	private int drawCount;
	private int loseCount;
	private int goalsForCount;
	private int goalsAgainstCount;
	
	
	static class MatchStatisticsPk implements Serializable {
		Club club;
		League league;
		
		public MatchStatisticsPk(Club club, League league) {
			this.club = club;
			this.league = league;
		}
		
		public MatchStatisticsPk() {
		}
	}

}