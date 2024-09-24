package SoccerApp.controller;

import SoccerApp.entity.Referee;
import SoccerApp.service.RefereeService;

public class RefereeController extends BaseController<Referee,Long> {
	private static RefereeController instance;

	public static RefereeController getInstance() {
		if (instance == null) {
			instance = new RefereeController();
		}
		return instance;
	}
	private RefereeController() {
		super(RefereeService.getInstance());
	}
}