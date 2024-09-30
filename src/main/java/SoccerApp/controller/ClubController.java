package SoccerApp.controller;

import SoccerApp.entity.mainEntity.Club;
import SoccerApp.service.ClubService;

import java.util.ArrayList;
import java.util.List;

public class ClubController extends BaseController<Club,Long>{
	private static ClubController instance;
	private ClubService clubService = ClubService.getInstance();
	public static ClubController getInstance() {
		if (instance == null) {
			instance = new ClubController();
		}
		return instance;
	}
	private ClubController() {
		super(ClubService.getInstance());
	}
	
	public List<Club> findClubByName(String nameFilter) {
		try {
			return clubService.findByName(nameFilter);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}