package SoccerApp.engine;

import SoccerApp.controller.MatchStatisticsController;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.entity.mainEntity.Player;
import SoccerApp.utility.enums.MatchEngineStatus;
import SoccerApp.utility.enums.Position;
import SoccerApp.utility.enums.ScorerSide;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MatchEngine {
	private static Map<Position, Integer> homeStrength;
	private static Map<Position, Integer> awayStrength;
	private static Random random = new Random();
	private static Match currentMatch;
	private static MatchEngineStatus mes;
	private static MatchStatisticsController controller;
	public static void play(Match match){
		homeStrength =
				match.getHomeLineup().stream()
				     .collect(Collectors.groupingBy(player -> player.getPosition(),
                                Collectors.summingInt(player -> player.getRating())));
		awayStrength =
				match.getAwayLineup().stream()
				     .collect(Collectors.groupingBy(player -> player.getPosition(),
				                                    Collectors.summingInt(player -> player.getRating())));
		currentMatch = match;
		int timePointer = 0;
		mes = MatchEngineStatus.MIDFIELD;
		updateStatsForMatch(ScorerSide.NONE);
		while(timePointer < match.getTime()){
			timePointer += random.nextInt(3, 7);
			nextEvent();
		}
	}
	
	private static void nextEvent() {
		int randInt;
		switch (mes){
			case HOME_GOAL:
				int goalStrength = homeStrength.get(Position.GOALKEEPER)*3*2;
				int shootStrength = awayStrength.get(Position.ATTACKER);
				randInt = random.nextInt(goalStrength + shootStrength + 300);
				if(randInt < goalStrength) mes = MatchEngineStatus.DEFENSE;
				else if (randInt < goalStrength + shootStrength) {
					mes = MatchEngineStatus.MIDFIELD;
					updateStatsForMatch(ScorerSide.AWAY);
				}
				break;
			case DEFENSE:
				int defStrength = homeStrength.get(Position.DEFENDER)*2;
				int attackStrength = awayStrength.get(Position.ATTACKER);
				randInt = random.nextInt(defStrength + attackStrength + 300);
				if(randInt < defStrength) mes = MatchEngineStatus.MIDFIELD;
				else if (randInt < defStrength + attackStrength) mes = MatchEngineStatus.HOME_GOAL;
				break;
			case MIDFIELD:
				int homeMidStrength = homeStrength.get(Position.MIDFIELDER)*2;
				int awayMidStrength = awayStrength.get(Position.MIDFIELDER);
				randInt = random.nextInt(homeMidStrength + awayMidStrength + 300);
				if(randInt < homeMidStrength) mes = MatchEngineStatus.ATTACK;
				else if (randInt < homeMidStrength + awayMidStrength) mes = MatchEngineStatus.DEFENSE;
				break;
			case ATTACK:
				int homeAttackStrength = homeStrength.get(Position.ATTACKER)*2;
				int awayDefStrength = awayStrength.get(Position.DEFENDER);
				randInt = random.nextInt(homeAttackStrength + awayDefStrength + 300);
				if(randInt < homeAttackStrength) mes = MatchEngineStatus.AWAY_GOAL;
				else if (randInt < homeAttackStrength + awayDefStrength) mes = MatchEngineStatus.MIDFIELD;
				break;
			case AWAY_GOAL:
				int homeShootStrength = homeStrength.get(Position.ATTACKER)*2;
				int awayGoalStrength = awayStrength.get(Position.GOALKEEPER)*3;
				randInt = random.nextInt(homeShootStrength + awayGoalStrength + 300);
				if(randInt < homeShootStrength) mes = MatchEngineStatus.ATTACK;
				else if (randInt < homeShootStrength + awayGoalStrength) {
					mes = MatchEngineStatus.MIDFIELD;
					updateStatsForMatch(ScorerSide.HOME);
				}
				break;
			default:
				System.out.println("How did we come here?");
		}
	}
	
	private static void updateStatsForMatch(ScorerSide scorerSide){
		controller.updateStatsForMatch(currentMatch, scorerSide);
	}
}