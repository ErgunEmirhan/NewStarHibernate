package SoccerApp.utility;

import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.utility.enums.MatchType;
import SoccerApp.utility.enums.WeatherCondition;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class MatchArrangeAlgoritm {
	private static List<Match> matchList = new ArrayList<>();
	private static int weekCount;
	private static int minMatchesPerDay;
	private static int extraMatchesForSaturday;
	private static int extraMatchesForSunday;
	private static League league;
	private static LocalDate startDate;
	
	public static void matchArrangeAlgorithm(League leagueWithoutFixture) {
		league = leagueWithoutFixture;
		List<Club> clubs = new ArrayList<>(league.getClubs());
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
			assignSecondHalf(matchesOfTheWeek);
			matchList.addAll(matchesOfTheWeek);
		}
		
		
		league.setFixture(matchList);
	}
	
	private static void assignSecondHalf(List<Match> matchesOfTheWeek) {
		List<Match> secondHalfMatches = matchesOfTheWeek.stream().map(match -> {
			Match sidesReversedMatch = new Match();
			sidesReversedMatch.setMatchType(match.getMatchType());
			sidesReversedMatch.setMatchDate(match.getMatchDate().plusWeeks(2 + weekCount));
			sidesReversedMatch.setCurrentWeekofSeason((byte) (match.getCurrentWeekofSeason() + weekCount));
			sidesReversedMatch.setHome(match.getAway());
			sidesReversedMatch.setAway(match.getHome());
			sidesReversedMatch.setLeague(league);
			sidesReversedMatch.setStadium(sidesReversedMatch.getHome().getStadium());
			sidesReversedMatch.setWeatherCondition(WeatherCondition.values()[new Random().nextInt(WeatherCondition.values().length)]);
			return sidesReversedMatch;
		}).toList();
		matchesOfTheWeek.addAll(secondHalfMatches);
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
			match.setAway(clubs.get((2*clubSize - i - weekCount - 1) % clubSize));
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