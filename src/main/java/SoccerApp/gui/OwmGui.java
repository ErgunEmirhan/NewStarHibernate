package SoccerApp.gui;

import SoccerApp.controller.OfferWithManagerController;
import SoccerApp.entity.combinedEntity.OfferWithManager;
import SoccerApp.utility.InputHandler;
import SoccerApp.utility.enums.ManagerOfferStatus;

import java.time.LocalDate;

public class OwmGui {
	private OfferWithManager offer;
	private static OwmGui owmGui;
	private OfferWithManagerController controller;
	private OwmGui(){}
	public static OwmGui getInstance(){
		if(owmGui == null){
			owmGui = new OwmGui();
		}
		return owmGui;
	}
	
	public void owmMainMenu(OfferWithManager owm){
		this.offer = owm;
		int choice;
		do {
			System.out.println("what do you want to do with the owm");
			System.out.println("1. Accept");
			System.out.println("2. Refuse");
			System.out.println("3. Counter offer");
			System.out.println("0. Go back");
			choice = InputHandler.integerInput();
			owmMainMenuOptions(choice);
		}while(choice != 0);
		
	}
	
	private void owmMainMenuOptions(int choice) {
		switch (choice){
			case 1:
				acceptOffer();
				break;
			case 2:
				refuseOffer();
				break;
			case 3:
				counterOffer();
				break;
			case 0: break;
			default:
				System.out.println("Invalid choice");
		}
	}
	
	private void counterOffer() {
		System.out.println("The other side set the transfer fee as: " + offer.getTransferFee());
		double transferFee = InputHandler.doubleInput("Enter the amount you would like to set> ");
		
		var counterOffer = OfferWithManager.builder()
				.player(offer.getPlayer())
				.offerDate(LocalDate.now())
				.ownerManager(offer.getOwnerManager())
				.buyerManager(offer.getBuyerManager())
				.ownerClub(offer.getOwnerClub())
				.buyerClub(offer.getBuyerClub())
				.transferFee(transferFee)
				.build();
		if (offer.getOfferStatus() == ManagerOfferStatus.BUYER_OFFER) {
			counterOffer.setOfferStatus(ManagerOfferStatus.OWNER_OFFER);
			offer.setOfferStatus(ManagerOfferStatus.COUNTERED_BUYER_OFFER);
		}
		else {
			counterOffer.setOfferStatus(ManagerOfferStatus.BUYER_OFFER);
			offer.setOfferStatus(ManagerOfferStatus.COUNTERED_OWNER_OFFER);
		}
		controller.update(offer);
		controller.save(counterOffer);
	}
	
	private void refuseOffer() {
		controller.refuseOffer(offer);
	}
	
	private void acceptOffer() {
		controller.acceptOffer(offer);
	}
}