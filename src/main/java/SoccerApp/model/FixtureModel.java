package SoccerApp.model;

import SoccerApp.entity.mainEntity.Match;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FixtureModel {
	public static void showFixture(List<Match> fixture){
		Map<Byte, List<Match>> collect = new HashMap<>();
		
		for (int i = 0; i < fixture.size(); i++) {
			Match match = fixture.get(i);
			Byte weekIndex = match.getCurrentWeekofSeason();
			collect.putIfAbsent(weekIndex, new ArrayList<>());
			collect.get(weekIndex).add(match);
		}

		for (Map.Entry<Byte, List<Match>> entryByWeek : collect.entrySet()) {
			System.out.println("###########################");
			System.out.println("### Week " + (entryByWeek.getKey()) + " ###");
			Map<LocalDate, List<Match>> matchesByDay = entryByWeek
					.getValue().stream()
                    .collect(Collectors.groupingBy(match -> match.getMatchDate().toLocalDate()));
			Map<LocalDate, List<Match>> matchesByDate = new TreeMap<>();
			matchesByDate.putAll(matchesByDay);
			
			for (Map.Entry<LocalDate, List<Match>> entryByDayOfWeek : matchesByDate.entrySet()) {
				System.out.println("---- Day " + entryByDayOfWeek.getKey().getDayOfWeek() + " ----");
				entryByDayOfWeek.getValue().forEach(match -> {
                                System.out.println("HOME: " + match.getHome().getName() +
		                                                   "\t\t\tAWAY: " + match.getAway().getName());
                                System.out.println("STADIUM: " + match.getStadium().getName() +
		                                "\t\t\tTIME: " + match.getMatchDate().toLocalTime() + "\n");
                            }
						);
			}
			System.out.println();
		}
	}
}