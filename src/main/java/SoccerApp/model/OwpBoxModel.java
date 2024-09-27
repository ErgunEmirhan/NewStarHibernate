package SoccerApp.model;

import SoccerApp.entity.OfferWithPlayer;

import java.util.List;

public class OwpBoxModel {
	public static void showOwpBox(List<OfferWithPlayer> owpBox){
		int i = 0;
		for (OfferWithPlayer owp: owpBox){
			System.out.println("### " + (++i) + ". offer with player");
			OwmBoxModel.showOwm(owp.getOfferWithManager());
			System.out.println("contract end date: " + owp.getContractEndDate());
			System.out.println("offer date: " + owp.getOfferDate());
			System.out.println("salary: " + owp.getOfferedSalary());
		}
	}
}