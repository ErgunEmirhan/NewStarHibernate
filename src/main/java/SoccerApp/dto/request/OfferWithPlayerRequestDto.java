package SoccerApp.dto.request;

import SoccerApp.entity.combinedEntity.OfferWithPlayer;

public class OfferWithPlayerRequestDto {
	private OfferWithPlayer owp;
	private Double newSalary;
	
	public OfferWithPlayerRequestDto(OfferWithPlayer owp, Double newSalary) {
		this.owp = owp;
		this.newSalary = newSalary;
	}
	
	public OfferWithPlayer getOwp() {
		return owp;
	}
	
	public Double getNewSalary() {
		return newSalary;
	}
}