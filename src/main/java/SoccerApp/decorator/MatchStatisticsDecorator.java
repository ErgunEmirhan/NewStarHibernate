package SoccerApp.decorator;

import SoccerApp.entity.combinedEntity.MatchStatistics;

public class MatchStatisticsDecorator {
	private MatchStatistics matchStatistics;
	private int matchCount;
	private int score;
	private int average;
	
	public MatchStatisticsDecorator(MatchStatistics matchStatistics) {
		this.matchStatistics = matchStatistics;
		this.matchCount = matchStatistics.getDrawCount() +
				matchStatistics.getWinCount() +
				matchStatistics.getLoseCount();
		scoreCalculator();
		averageCalculator();
	}
	
	
	public static int scoreCalculator(MatchStatistics matchStatistics) {
		return matchStatistics.getDrawCount() + matchStatistics.getWinCount()*3;
	}
	
	public static int averageCalculator(MatchStatistics matchStatistics) {
		return matchStatistics.getGoalsForCount() - matchStatistics.getGoalsAgainstCount();
	}
	
	private void scoreCalculator(){
		this.score = scoreCalculator(matchStatistics);
	}
	
	private void averageCalculator() {
		this.average = averageCalculator(matchStatistics);
	}
	
	
	public MatchStatistics getMatchStatistics() {
		return matchStatistics;
	}
	
	public int getMatchCount() {
		return matchCount;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getAverage() {
		return average;
	}
}