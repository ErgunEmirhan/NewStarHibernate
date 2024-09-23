package SoccerApp.controller;

import SoccerApp.entity.Player;
import SoccerApp.services.PlayerService;
import SoccerApp.utility.ICRUDService;

public class PlayerController extends BaseController<Player,Long>{
	private static PlayerController instance;

	public static PlayerController getInstance() {
		if (instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}
	private PlayerController() {
		super(PlayerService.getInstance());
	}
}