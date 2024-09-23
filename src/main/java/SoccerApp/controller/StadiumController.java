package SoccerApp.controller;

import SoccerApp.entity.Stadium;
import SoccerApp.services.StadiumService;

public class StadiumController extends BaseController<Stadium,Long>{
	private static StadiumController instance;

	public static StadiumController getInstance() {
		if (instance == null) {
			instance = new StadiumController();
		}
		return instance;
	}
	private StadiumController() {
		super(StadiumService.getInstance());
	}
}