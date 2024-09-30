package SoccerApp.utility.HandleMockData.createMockData;

import SoccerApp.controller.PlayerController;
import SoccerApp.entity.mainEntity.Player;
import SoccerApp.utility.enums.Nationality;
import SoccerApp.utility.enums.Position;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateDemoPlayerData {
	Random rnd=new Random();
	PlayerController playerController=PlayerController.getInstance();
	public void createDemoPlayerData() {
		List<Player> playerList=new ArrayList<>();
		for (int i = 0; i < 3000; i++) {
			String name="Player"+i;
			String surname="Surname"+i;
			LocalDate birthDate=LocalDate.of(rnd.nextInt(1985,2008), rnd.nextInt(1,13), rnd.nextInt(1,28));
			String nationality= Nationality.values()[rnd.nextInt(Nationality.values().length)].toString();
			Integer salary=rnd.nextInt(10000,100000);
			int jerseyNumber=i%30;
			Double transferFee=rnd.nextDouble(10000,10000000);
			Position position=givePosition(i);
			int rating=rnd.nextInt(60,100);
			LocalDate contractEndDate=LocalDate.of(rnd.nextInt(2024,2030), rnd.nextInt(1,13), rnd.nextInt(1,28));
			Player player=
					Player.builder().firstName(name).lastName(surname).birthDate(birthDate).nationality(Nationality.valueOf(nationality)).salary(salary).jerseyNumber(jerseyNumber).transferFee(transferFee).position(position).rating(rating).contractEndDate(contractEndDate).build();
			System.out.println(player);
			playerList.add(player);
		}
		playerController.saveAll(playerList);
	}
	public Position givePosition(int i){
		return switch (i%30){
			case 1,2,3,4->Position.GOALKEEPER;
			case 5,6,7,8,9,10,11,12,13->Position.DEFENDER;
			case 14,15,16,17,18,19,20,21,22->Position.MIDFIELDER;
			case 23,24,25,26,27,28,29,0->Position.ATTACKER;
			default -> Position.MIDFIELDER;
		};
	}
}