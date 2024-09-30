package SoccerApp.gui;

import SoccerApp.controller.OfferWithPlayerController;
import SoccerApp.entity.combinedEntity.OfferWithPlayer;
import SoccerApp.utility.InputHandler;
import SoccerApp.utility.PlayerOfferHandler;
import SoccerApp.utility.enums.PlayerOfferStatus;

public class OwpGui {
	private static OwpGui instance;
	private OfferWithPlayer offer;
	private OfferWithPlayerController controller;
	private OwpGui() {
		this.controller = OfferWithPlayerController.getInstance();
	}
	public static OwpGui getInstance() {
		if (instance == null) {
			instance = new OwpGui();
		}
		return instance;
	}
	
	public void owpMainMenu(OfferWithPlayer owp) {
		int choice;
		this.offer = owp;
		do {
			System.out.println("### Owp Main Menu");
			System.out.println("Which action would you like to take> ");
			System.out.println("1. Accept");
			System.out.println("2. Refuse");
			System.out.println("3. Offer/ Counter-offer");
			System.out.println("0. Go back");
			choice = InputHandler.integerInput();
			owpMainMenuOptions(choice);
			PlayerOfferHandler.handleOwp(owp);
		}while(choice != 0);
	}
	
	private void owpMainMenuOptions(int choice) {
		switch(choice){
			case 1:
				acceptOffer();
				break;
			case 2:
				refuseOffer();
				break;
			case 3:
				counterOffer();
				break;
			case 0:
				break;
			default:
				System.out.println("Invalid option");
		}
	}
	
	private void counterOffer() {
		System.out.println("Current salary is: " + offer.getOfferedSalary());
		double newSalary = InputHandler.doubleInput("To what amount would you like to set the salary> ");
		offer.setOfferedSalary(newSalary);
		controller.update(offer);
	}
	
	private void refuseOffer() {
		offer.setStatus(PlayerOfferStatus.REFUSED);
		controller.update(offer);
	}
	
	private void acceptOffer() {
		if (offer.getOfferedSalary() == 0){
			System.out.println("You have to first decide to a salary to accept an offer");
		}
		else{
			offer.setStatus(PlayerOfferStatus.ACCEPTED);
			controller.update(offer);
		}
	}
}