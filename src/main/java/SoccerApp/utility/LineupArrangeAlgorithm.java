package SoccerApp.utility;

import SoccerApp.entity.mainEntity.Match;
import SoccerApp.entity.mainEntity.Player;
import SoccerApp.utility.enums.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class LineupArrangeAlgorithm {
	private static List<Player> homePlayers = new ArrayList<>();
	private static List<Player> awayPlayers = new ArrayList<>();
	private static List<Player> homeLineup = new ArrayList<>();
	private static List<Player> awayLineup = new ArrayList<>();
	
	public static void assignLineups(Match match) {
		homePlayers.clear(); homePlayers.addAll(match.getHome().getSquad());
		awayPlayers.clear(); awayPlayers.addAll(match.getAway().getSquad());
		
		assignGoalkeepers();
		assignDefenders();
		assignMidfielders();
		assignAttackers();
		
		match.setHomeLineup(homeLineup);
		match.setAwayLineup(awayLineup);
	}
	
	private static void assignAttackers() {
		assignRole(Position.ATTACKER);
	}
	
	private static void assignMidfielders() {
		assignRole(Position.MIDFIELDER);
	}
	
	private static void assignDefenders() {
		assignRole(Position.DEFENDER);
	}
	
	private static void assignGoalkeepers() {
		assignRole(Position.GOALKEEPER);
	}
	
	private static void assignRole(Position position){
		Random random = new Random();
		List<Player> potentialHomePlayers = new ArrayList<>();
		potentialHomePlayers.addAll(homePlayers.stream()
                         .filter(player ->
                                 player.getPosition().equals(position)).toList());
		List<Player> potentialAwayPlayers = new ArrayList<>();
		potentialAwayPlayers.addAll(awayPlayers.stream()
               .filter(player ->
                player.getPosition().equals(position)).toList());
		switch(position){
			case Position.GOALKEEPER:
				homeLineup.add(potentialHomePlayers.get(random.nextInt(potentialHomePlayers.size())));
				awayLineup.add(potentialAwayPlayers.get(random.nextInt(potentialAwayPlayers.size())));
				break;
			case Position.DEFENDER:
				for (int i = 0; i < 4; i++) {
					Player homePlayer = potentialHomePlayers.get(random.nextInt(potentialHomePlayers.size()));
					potentialHomePlayers.remove(homePlayer);
					homeLineup.add(homePlayer);
					Player awayPlayer = potentialAwayPlayers.get(random.nextInt(potentialAwayPlayers.size()));
					potentialAwayPlayers.remove(awayPlayer);
					awayLineup.add(awayPlayer);
				}
				break;
			case Position.MIDFIELDER:
				for (int i = 0; i < 3; i++) {
					Player homePlayer = potentialHomePlayers.get(random.nextInt(potentialHomePlayers.size()));
					potentialHomePlayers.remove(homePlayer);
					homeLineup.add(homePlayer);
					Player awayPlayer = potentialAwayPlayers.get(random.nextInt(potentialAwayPlayers.size()));
					potentialAwayPlayers.remove(awayPlayer);
					awayLineup.add(awayPlayer);
				}
				break;
			case Position.ATTACKER:
				for (int i = 0; i < 3; i++) {
					Player homePlayer = potentialHomePlayers.get(random.nextInt(potentialHomePlayers.size()));
					potentialHomePlayers.remove(homePlayer);
					homeLineup.add(homePlayer);
					Player awayPlayer = potentialAwayPlayers.get(random.nextInt(potentialAwayPlayers.size()));
					potentialAwayPlayers.remove(awayPlayer);
					awayLineup.add(awayPlayer);
				}
				break;
			default:
				System.out.println("Unexpected");
			
		}
	}
}