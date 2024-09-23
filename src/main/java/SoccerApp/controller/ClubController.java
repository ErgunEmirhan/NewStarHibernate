package SoccerApp.controller;

import SoccerApp.entity.Club;
import SoccerApp.services.ClubService;

import java.util.List;
import java.util.Optional;

public class ClubController extends BaseController<Club,Long>{
	private static ClubController instance;

	public static ClubController getInstance() {
		if (instance == null) {
			instance = new ClubController();
		}
		return instance;
	}
	private ClubController() {
		super(ClubService.getInstance());
	}
	
	public List<Club> findByName(String name){
	
	}
}