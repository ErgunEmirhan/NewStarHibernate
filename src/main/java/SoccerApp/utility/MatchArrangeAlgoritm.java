package SoccerApp.utility;

import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.Match;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchArrangeAlgoritm {
	static List<Match> matchList = new ArrayList<>();
	public static List<Match> matchArrangeAlgorithm(List<Club> clubs, LocalDate startDate) {
		matchList.clear();
		
		int weekCount = clubs.size() - 1;
		int minMatchesPerDay = (clubs.size() -1) / 4;
		int totalExtraMatches = (clubs.size() - 1) % 4;
		int extraMatchesForSaturday = totalExtraMatches/2;
		int extraMatchesForSunday = totalExtraMatches - extraMatchesForSaturday;
		
		for (int weekIdx = 0; weekIdx < weekCount; weekIdx++) {
		
		}
		
	}
}