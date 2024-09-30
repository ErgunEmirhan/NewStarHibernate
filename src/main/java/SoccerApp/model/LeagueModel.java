package SoccerApp.model;

import SoccerApp.entity.combinedEntity.League;

import java.util.List;

public class LeagueModel {
	public static void showLeagues(List<League> leagues){
		int i = 0;
		System.out.println("### Leagues ###");
		for (League league: leagues){
			System.out.println("------  League " + (++i) + "  --------");
			System.out.println("Name: " + league.getName() + "\t\t\tSize: " + league.getTeamCount() + "teams");
			System.out.println("Region: " + league.getRegion() + "\t\tDivision: " + league.getDivision());
			System.out.println("Start: " + league.getStartDate() + "\t\t\tEnd: " + league.getEndDate());
		}
	}
}