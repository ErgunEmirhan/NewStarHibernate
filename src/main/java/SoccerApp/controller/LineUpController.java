package SoccerApp.controller;

import SoccerApp.entity.combinedEntity.LineUp;
import SoccerApp.service.LineUpService;

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