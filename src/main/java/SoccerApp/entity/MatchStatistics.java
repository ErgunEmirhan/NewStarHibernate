package SoccerApp.entity;

import jakarta.persistence.*;

@Table(name = "tblmatchstatistics")
public class MatchStatistics {
	private Long clubId;
	private String leagueId;
	private int galibiyet;
	private int beraberlik;
	private int maglubiyet;
	private int atilanGol;
	private int yenilenGol;

}