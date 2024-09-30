package SoccerApp.controller;

import SoccerApp.entity.Player;
import SoccerApp.service.PlayerService;
import SoccerApp.utility.enums.Nationality;
import SoccerApp.utility.enums.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerController extends BaseController<Player,Long>{
	private static PlayerController instance;
	private PlayerService service;
	
	public static PlayerController getInstance() {
		if (instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}
	private PlayerController() {
		super(PlayerService.getInstance());
		service = PlayerService.getInstance();
	}
	
	public List<Player> findByNationality(Nationality nationality) {
		try {
			return service.findByNationality(nationality);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Player> findByPosition(Position position) {
		try {
			return service.findByPosition(position);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Player> findBetweenRatings(int minRating, int maxRating) {
		try {
			return service.findBetweenRatings(minRating, maxRating);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Player> findBetweenTransferFees(double minTransferFee, double maxTransferFee) {
		try {
			return service.findBetweenTransferFees(minTransferFee, maxTransferFee);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Player> findBetweenAges(int minAge, int maxAge) {
		try {
			return service.findBetweenAges(minAge, maxAge);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Player> findByName(String nameFilter) {
		try {
			return service.findByName(nameFilter);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}