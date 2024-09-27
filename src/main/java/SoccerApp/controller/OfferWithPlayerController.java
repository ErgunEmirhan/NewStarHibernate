package SoccerApp.controller;

import SoccerApp.entity.Manager;
import SoccerApp.entity.OfferWithPlayer;
import SoccerApp.repository.OfferWithPlayerRepository;
import SoccerApp.service.BaseService;
import SoccerApp.service.OfferWithManagerService;
import SoccerApp.service.OfferWithPlayerService;
import SoccerApp.utility.ICRUDService;

import java.util.ArrayList;
import java.util.List;

public class OfferWithPlayerController extends BaseController<OfferWithPlayer, Long> {
	private static OfferWithPlayerController instance;
	private OfferWithPlayerService service;
	public static OfferWithPlayerController getInstance() {
		if (instance == null) {
			instance = new OfferWithPlayerController();
		}
		return instance;
	}
	private OfferWithPlayerController() {
		super(OfferWithPlayerService.getInstance());
		this.service = OfferWithPlayerService.getInstance();
	}
	
	public List<OfferWithPlayer> showOwpBox(Manager manager) {
		try{
			return service.showOwpBox(manager);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<>();
		}
	}
}