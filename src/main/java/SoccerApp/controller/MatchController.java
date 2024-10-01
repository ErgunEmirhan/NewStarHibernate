package SoccerApp.controller;

import SoccerApp.dto.request.MatchByClubAndLeagueDtoRequest;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.service.MatchService;

import java.util.ArrayList;
import java.util.List;

public class MatchController extends BaseController<Match,Long>{
	private static MatchController instance;
	private MatchService service;
	public static MatchController getInstance() {
		if (instance == null) {
			instance = new MatchController();
		}
		return instance;
	}
	private MatchController() {
		super(MatchService.getInstance());
		service = MatchService.getInstance();
	}
	
	public List<Match> findMatchByClubAndLeague(MatchByClubAndLeagueDtoRequest request) {
		try{
			return service.findByClubAndLeague(request);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<>();
		}
	}
}