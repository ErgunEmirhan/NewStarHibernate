package SoccerApp.model;

import SoccerApp.entity.Club;
import SoccerApp.entity.Manager;

public class ClubDetailsModel {
	public static void showDetails(Club club) {
		System.out.println(club.getName() + "\t\t\t" + club.getFoundationYear());
		System.out.println("-   -   -   -   -   -   -   -   -   -   -");
		System.out.println("Stadium: " + club.getStadiumName());
		System.out.println("Chairman: " + club.getChairman());
		Manager manager = club.getManager();
		if (manager != null) System.out.println("Manager: " + manager);
		else System.out.println("Manager: N/A");
		System.out.println("Budget: " + club.getBudget());
		System.out.println("wage bill: " + club.getWageBill());
	}
}