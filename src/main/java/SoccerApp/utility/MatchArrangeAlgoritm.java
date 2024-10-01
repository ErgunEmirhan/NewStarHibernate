package SoccerApp.utility;

import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.utility.enums.MatchType;
import SoccerApp.utility.enums.WeatherCondition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatchArrangeAlgoritm {
	private static List<Match> matchList = new ArrayList<>();
	private static int weekCount;
	private static int minMatchesPerDay;
	private static int extraMatchesForSaturday;
	private static int extraMatchesForSunday;
	private static League league;
	private static LocalDate startDate;
	
	public static List<Match> matchArrangeAlgorithm(League leagueWithoutFixture) {
		league = leagueWithoutFixture;
		List<Club> clubs = league.getClubs().stream().toList();
		startDate = league.getStartDate();
		
		matchList.clear();
		weekCount = clubs.size() - 1;
		int matchCountPerWeek = clubs.size()/2;
		minMatchesPerDay = (matchCountPerWeek) / 4;
		int totalExtraMatches = (matchCountPerWeek) % 4;
		extraMatchesForSaturday = totalExtraMatches/2;
		extraMatchesForSunday = totalExtraMatches - extraMatchesForSaturday;
		
		for (int weekIdx = 0; weekIdx < weekCount; weekIdx++) {
			List<Match> matchesOfTheWeek = createEmptyMatches(clubs.size());
			assignClubsToMatches(matchesOfTheWeek, clubs, weekIdx);
			assignMatchDateToMatches(matchesOfTheWeek, weekIdx);
			matchList.addAll(matchesOfTheWeek);
		}
		
		
		return matchList;
	}
	
	private static List<Match> createEmptyMatches(int teamCount){
		List<Match> matches = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < teamCount/2; i++) {
			matches.add(Match.builder()
			                 .league(league)
			                 .matchType(MatchType.LEAGUE)
			                 .weatherCondition(WeatherCondition
					                                   .values()
					                                   [random.nextInt(WeatherCondition.values().length)])
			                 .build());
		}
		return matches;
	}
	
	private static void assignClubsToMatches(List<Match> matchesOfTheWeek, List<Club> clubs, int weekCount){
		int clubSize = clubs.size();
		Collections.shuffle(clubs);
		
		for (int i = 0; i < matchesOfTheWeek.size(); i++) {
			Match match = matchesOfTheWeek.get(i);
			Club home = clubs.get((clubSize + i - weekCount) % clubSize);
			match.setHome(home);
			match.setAway(clubs.get(clubSize - i - weekCount));
			match.setStadium(home.getStadium());
			match.setCurrentWeekofSeason((byte)weekCount);
		}
	}
	
	private static void assignMatchDateToMatches(List<Match> matchesOfTheWeek, int weekCount){
		
		int matchCount = matchesOfTheWeek.size();
		List<LocalDateTime> matchDates = getRespectiveLocalDateTimes(weekCount);
		for (int i = 0; i < matchCount; i++) {
			matchesOfTheWeek.get(i).setMatchDate(matchDates.get(i));
		}
	}
	
	private static List<LocalDateTime> getRespectiveLocalDateTimes(int weekCount) {
		List<LocalDateTime> matchDates = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < minMatchesPerDay; i++) {
			LocalDate localDate = startDate.plusWeeks(weekCount - 1);
			LocalTime localTime = LocalTime.of(random.nextInt(18, 22), 0);
			LocalDateTime date = LocalDateTime.of(localDate, localTime);
			matchDates.add(date);
		}
		for (int i = 0; i < minMatchesPerDay + extraMatchesForSaturday; i++) {
			LocalDate localDate = startDate.plusWeeks(weekCount - 1).plusDays(1);
			LocalTime localTime = LocalTime.of(random.nextInt(18, 22), 0);
			LocalDateTime date = LocalDateTime.of(localDate, localTime);
			matchDates.add(date);
		}
		for (int i = 0; i < minMatchesPerDay + extraMatchesForSunday; i++) {
			LocalDate localDate = startDate.plusWeeks(weekCount - 1).plusDays(2);
			LocalTime localTime = LocalTime.of(random.nextInt(18, 22), 0);
			LocalDateTime date = LocalDateTime.of(localDate, localTime);
			matchDates.add(date);
		}
		for (int i = 0; i < minMatchesPerDay; i++) {
			LocalDate localDate = startDate.plusWeeks(weekCount - 1).plusDays(3);
			LocalTime localTime = LocalTime.of(random.nextInt(18, 22), 0);
			LocalDateTime date = LocalDateTime.of(localDate, localTime);
			matchDates.add(date);
		}
		return matchDates;
	}
	
}