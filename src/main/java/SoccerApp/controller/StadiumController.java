package SoccerApp.controller;

import SoccerApp.entity.mainEntity.Stadium;
import SoccerApp.service.StadiumService;

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