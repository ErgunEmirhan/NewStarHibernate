package SoccerApp.model;

import SoccerApp.entity.Club;
import SoccerApp.entity.Player;

import java.util.List;
import java.util.Set;

public class ClubSquadModel {
	public static void showSquad(List<Player> squad) {
		int i = 1;
		for (Player player : squad) {
			System.out.println("-----------  " + (i++) + ". player  ----------------");
			System.out.println(player.getFirstName() + " " + player.getLastName() + "\t\t\t" + player.getBirthDate());
			System.out.println("-  -  -  -  -  -  -   -  -   -  -   -   -  -   -  -   -");
			System.out.println("position: " + player.getPosition());
			System.out.println("nationality: " + player.getNationality());
			System.out.println("-----------------------------------\n");
		}
	}
}