package SoccerApp.model;

import SoccerApp.entity.mainEntity.Player;

public class PlayerDetailedModel {
	public static void showDetails(Player player){
		System.out.println(player.getFirstName() + " " + player.getLastName() + "\t\t\t" + player.getBirthDate());
		System.out.println("-  -  -  -  -  -  -  -   -  -  -   -  -   -  -   -  -   -   -  -   -  -   -");
		System.out.println("position: " + player.getPosition());
		System.out.println("nationality: " + player.getNationality());
		System.out.println("jersey number: " + player.getJerseyNumber());
		System.out.println("rating: " + player.getRating());
		System.out.println("transfer fee: " + player.getTransferFee());
		System.out.println("salary: " + player.getSalary());
		System.out.println("contract end date: " + player.getContractEndDate());
	}
}