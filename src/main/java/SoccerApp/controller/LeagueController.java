package SoccerApp.controller;

import SoccerApp.entity.mainEntity.League;
import SoccerApp.service.LeagueService;

public class LeagueController extends BaseController<League,Long> {
	private static LeagueController instance;

	public static LeagueController getInstance() {
		if (instance == null) {
			instance = new LeagueController();
		}
		return instance;
	}
	private LeagueController() {
		super(LeagueService.getInstance());
	}
}