package SoccerApp.model;

import SoccerApp.entity.OfferWithManager;

import java.util.List;

public class OwmBoxModel {
	public static void showOwmBox(List<OfferWithManager> owmBox){
		int i = 0;
		for (OfferWithManager owm : owmBox) {
			System.out.println("### " + (++i) + ". owm ###");
			showOwm(owm);
		}
	}
	
	public static void showOwm(OfferWithManager owm){
		System.out.println("owner club: " + owm.getOwnerClub().getName() +
				                   "\t\t\t\tbuyer club" + owm.getBuyerClub().getName());
		System.out.println("owner manager: " + owm.getOwnerManager().getFirstName() +
				                   " " + owm.getOwnerManager().getLastName() +
				                   "\t\t\t\tbuyer manager" + owm.getBuyerManager().getFirstName() +
				                   " " + owm.getBuyerManager().getLastName());
		System.out.println("player: " + owm.getPlayer().getFirstName() + " " +
				                   owm.getPlayer().getLastName() +
				                   "\t\t\t\ttransfer fee: " + owm.getTransferFee() );
		System.out.println("offer date: " + owm.getOfferDate() + "\t\t\t\toffer status:" + owm.getOfferStatus());
		
	}
}