package SoccerApp.model;

import SoccerApp.entity.Club;
import SoccerApp.entity.Manager;

import java.util.List;

public class ListedClubsModel {
	public static void listClubs(List<Club> clubs){
		int i = 1;
		for (Club club : clubs) {
			System.out.println("-----  " + (i++) + ". Club  -------");
			System.out.println(club.getName() + "\t\t\t" + club.getFoundationYear());
			System.out.println("-   -   -   -   -   -   -   -   -   -   -");
			System.out.println("Stadium: " + club.getStadiumName());
			System.out.println("Chairman: " + club.getChairman());
			Manager manager = club.getManager();
			if (manager != null) System.out.println("Manager: " + manager);
			else System.out.println("Manager: N/A");
			System.out.println("------------------------\n");
			
		}
	}
}