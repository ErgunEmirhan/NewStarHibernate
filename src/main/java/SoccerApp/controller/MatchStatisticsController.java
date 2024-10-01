package SoccerApp.controller;

import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.service.MatchStatisticsService;

import java.util.ArrayList;
import java.util.List;

public class MatchStatisticsController extends BaseController<MatchStatistics,Long>{
	private static MatchStatisticsController instance;
	private MatchStatisticsService service;
	public static MatchStatisticsController getInstance() {
		if (instance == null) {
			instance = new MatchStatisticsController();
		}
		return instance;
	}
	
	private MatchStatisticsController() {
		super(MatchStatisticsService.getInstance());
		service = MatchStatisticsService.getInstance();
	}
	
	public List<MatchStatistics> findStandings(League league) {
		try{
			return service.findByFieldNameAndValue("league", league);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<>();
		}
	}
	
	public void createMatchStatistics(League league) {
		try{
			service.createMatchStatistics(league);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}