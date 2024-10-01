package SoccerApp.model;

import SoccerApp.entity.mainEntity.Club;

import java.util.List;

public class ClubsListedModel {
	public static void listClubs(List<Club> clubs){
		int i = 1;
		for (Club club : clubs) {
			System.out.println("-----  " + (i++) + ". Club  -------");
			System.out.println(club.getName() + "\t\t\t" + club.getFoundationYear());
			System.out.println("-   -   -   -   -   -   -   -   -   -   -");
			System.out.println("Stadium: " + club.getStadiumName());
			System.out.println("------------------------");
			
		}
	}
}