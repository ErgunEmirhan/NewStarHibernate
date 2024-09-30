package SoccerApp.utility;

import SoccerApp.controller.OfferWithPlayerController;
import SoccerApp.dto.request.OfferWithPlayerRequestDto;
import SoccerApp.entity.combinedEntity.OfferWithPlayer;
import SoccerApp.entity.mainEntity.Player;
import SoccerApp.utility.enums.PlayerOfferStatus;

import java.util.Random;

public class PlayerOfferHandler {
	private static OfferWithPlayerController controller = OfferWithPlayerController.getInstance();
	public static void handleOwp(OfferWithPlayer owp) {
		boolean isSalaryOffered = owp.getOfferedSalary() != 0;
		boolean isOfferedByBuyer = owp.getStatus() == PlayerOfferStatus.BUYER_OFFER;
		if (isOfferedByBuyer && isSalaryOffered){
			Double expectedMinSalary = calculateExpectedSalary(owp.getOfferWithManager()
			                                                      .getPlayer());
			Double rejectionSalaryThreshold = calculateRejectionThreshold(expectedMinSalary, owp.getOfferWithManager().getPlayer());
			if (expectedMinSalary <= owp.getOfferedSalary()){
				controller.acceptOwp(owp);
			}
			else if(owp.getOfferedSalary() < rejectionSalaryThreshold){
				controller.rejectOwp(owp);
			}
			else counterOffer(owp, expectedMinSalary);
		}
	}
	
	private static void counterOffer(OfferWithPlayer owp, Double expectedMinSalary) {
		Random random = new Random();
		Double offeredSalary = owp.getOfferedSalary();
		Double ratio = (expectedMinSalary - offeredSalary) / offeredSalary;
		Double demandSalary = offeredSalary *(2 + random.nextDouble(ratio, 3*ratio))/2;
		var owpReqDto = new OfferWithPlayerRequestDto(owp, demandSalary);
		controller.counterOffer(owpReqDto);
	}
	
	private static Double calculateRejectionThreshold(Double expectedMinSalary, Player player) {
		int currentSalary = player.getSalary();
		Random random = new Random();
		return expectedMinSalary - (expectedMinSalary - currentSalary)*
				random.nextDouble(0.6, 1);
	}
	
	private static Double calculateExpectedSalary(Player player){
		int currentSalary = player.getSalary();
		Random random = new Random();
		double minSalaryRatio = random.nextDouble(1, 2);
		return currentSalary*minSalaryRatio;
	}
}