package SoccerApp.controller;

import SoccerApp.entity.combinedEntity.Match;
import SoccerApp.service.MatchService;

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