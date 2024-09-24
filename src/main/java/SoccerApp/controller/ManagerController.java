package SoccerApp.controller;

import SoccerApp.entity.Manager;
import SoccerApp.service.ManagerService;

public class ManagerController extends BaseController<Manager,Long> {
	private static ManagerController instance;

	public static ManagerController getInstance() {
		if (instance == null) {
			instance = new ManagerController();
		}
		return instance;
	}
	private ManagerController() {
		super(ManagerService.getInstance());
	}
}