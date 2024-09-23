package SoccerApp.controller;

import SoccerApp.entity.Match;
import SoccerApp.services.MatchService;

public class MatchController extends BaseController<Match,Long>{
	private static MatchController instance;

	public static MatchController getInstance() {
		if (instance == null) {
			instance = new MatchController();
		}
		return instance;
	}
	private MatchController() {
		super(MatchService.getInstance());
	}
}