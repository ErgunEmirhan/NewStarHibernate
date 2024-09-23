package SoccerApp.controller;

import SoccerApp.entity.MatchStatistics;
import SoccerApp.services.MatchStatisticsService;
import SoccerApp.utility.ICRUDService;

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