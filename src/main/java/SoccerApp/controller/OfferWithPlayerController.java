package SoccerApp.controller;

import SoccerApp.dto.request.OfferWithPlayerRequestDto;
import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.entity.combinedEntity.OfferWithPlayer;
import SoccerApp.service.OfferWithPlayerService;

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
	
	public void acceptOwp(OfferWithPlayer owp) {
		try{
			service.acceptOwp(owp);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void rejectOwp(OfferWithPlayer owp) {
		try{
			service.rejectOwp(owp);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void counterOffer(OfferWithPlayerRequestDto owpReqDto) {
		try{
			service.counterOffer(owpReqDto);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}