package SoccerApp.controller;

import SoccerApp.dto.request.OfferWithManagerRequestDto;
import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.entity.combinedEntity.OfferWithManager;
import SoccerApp.service.OfferWithManagerService;

import java.util.ArrayList;
import java.util.List;

public class OfferWithManagerController extends BaseController<OfferWithManager, Long> {
	private static OfferWithManagerController controller;
	private OfferWithManagerService service;
	public static OfferWithManagerController getInstance(){
		if(controller == null){
			controller = new OfferWithManagerController();
		}
		return controller;
	}
	private OfferWithManagerController() {
		super(OfferWithManagerService.getInstance());
	}
	
	public void makeTransferRequest(OfferWithManagerRequestDto transferRequestDto) {
		try {
			service.makeTransferRequest(transferRequestDto);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<OfferWithManager> showOwmBox(Manager manager) {
		try {
			return service.findByReceivingManager(manager);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public void acceptOffer(OfferWithManager offer) {
		try {
			service.acceptOffer(offer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refuseOffer(OfferWithManager offer) {
		try {
			service.refuseOffer(offer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}