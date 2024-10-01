package SoccerApp.model;

import SoccerApp.decorator.MatchStatisticsDecorator;
import SoccerApp.entity.combinedEntity.MatchStatistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StandingsModel {
	
	public static void showStandings(List<MatchStatistics> standings){
		var detailedStandings = statsToDecs(standings);
		System.out.println("Team Name Tot\t W\t D\t L\t GF\t  GA\t  Avg\tPt");
		System.out.println("---  --  ---  ---  ---  ---  ---  ---  ---");
		for (MatchStatisticsDecorator statDet: detailedStandings){
			MatchStatistics stat = statDet.getMatchStatistics();
			System.out.printf("%6s %4d %4d %4d %4d %4d %4d %4d %4d\n",
			                  stat.getClub().getName(),
			                  statDet.getMatchCount(),
			                  stat.getWinCount(),
			                  stat.getDrawCount(),
			                  stat.getLoseCount(),
			                  stat.getGoalsForCount(),
			                  stat.getGoalsAgainstCount(),
			                  statDet.getAverage(),
			                  statDet.getScore());
		}
	}
	
	private static List<MatchStatisticsDecorator> statsToDecs(List<MatchStatistics> standings) {
		var standingsExtended =
				standings.stream()
				         .map(matchStatistics ->
						              new MatchStatisticsDecorator(matchStatistics))
				         .toList();
		var finalExt = new ArrayList<MatchStatisticsDecorator>();
		finalExt.addAll(standingsExtended);
		Collections.sort(finalExt, new Comparator<MatchStatisticsDecorator>() {
			
			@Override
			public int compare(MatchStatisticsDecorator o1, MatchStatisticsDecorator o2) {
				return (o2.getScore() < o1.getScore()) ? -1 : (o2.getScore() > o1.getScore() ? 1
						: (o2.getAverage() < o1.getAverage()) ? -1 : (o2.getAverage() > o1.getAverage() ? 1 : 0));
			}
		});
		return standingsExtended;
	}
}