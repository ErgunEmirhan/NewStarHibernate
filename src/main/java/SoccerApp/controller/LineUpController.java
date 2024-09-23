package SoccerApp.controller;

import SoccerApp.entity.LineUp;
import SoccerApp.services.LineUpService;
import SoccerApp.utility.ICRUDService;

public class LineUpController extends BaseController<LineUp,Long>{
	private static LineUpController instance;

	public static LineUpController getInstance() {
		if (instance == null) {
			instance = new LineUpController();
		}
		return instance;
	}
	private LineUpController() {
		super(LineUpService.getInstance());
	}
}