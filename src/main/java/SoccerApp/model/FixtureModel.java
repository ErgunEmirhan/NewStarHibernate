package SoccerApp.model;

import SoccerApp.entity.mainEntity.Match;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FixtureModel {
	public static void showFixture(List<Match> fixture){
		Map<Byte, List<Match>> collect =
				fixture.stream().collect(Collectors.groupingBy(match -> match.getCurrentWeekofSeason()));
		
		for (Map.Entry<Byte, List<Match>> entryByWeek : collect.entrySet()) {
			System.out.println("###########################");
			System.out.println("### Week " + entryByWeek.getKey() + " ###");
			Map<LocalDate, List<Match>> matchesByDay = entryByWeek
					.getValue().stream()
                    .collect(Collectors.groupingBy(match -> match.getMatchDate().toLocalDate()));
			for (Map.Entry<LocalDate, List<Match>> entryByDayOfWeek : matchesByDay.entrySet()) {
				System.out.println("---- Day " + entryByDayOfWeek.getKey().getDayOfWeek() + " ----");
				entryByDayOfWeek.getValue().forEach(match -> {
                                System.out.println("HOME: " + match.getHome().getName() +
		                                                   "\t\t\tAWAY: " + match.getAway());
                                System.out.println("STADIUM: " + match.getStadium().getName() +
		                                "\t\t\tTIME: " + match.getMatchDate().toLocalTime());
                            }
						);
			}
		}
	}
}