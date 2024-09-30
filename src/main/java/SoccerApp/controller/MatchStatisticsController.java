package SoccerApp.controller;

import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.service.MatchStatisticsService;

public class MatchStatisticsController extends BaseController<MatchStatistics,Long>{
	private static MatchStatisticsController instance;
	public static MatchStatisticsController getInstance() {
		if (instance == null) {
			instance = new MatchStatisticsController();
		}
		return instance;
	}
	
	private MatchStatisticsController() {
		super(MatchStatisticsService.getInstance());
	}
}