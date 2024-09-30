package SoccerApp.dto.request;

import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.entity.mainEntity.Player;

public class OfferWithManagerRequestDto {
	private Manager manager;
	private Player player;
	private Double transferFee;
	public OfferWithManagerRequestDto(Manager manager, Player player, Double transferFee) {
		this.manager = manager;
		this.player = player;
		this.transferFee = transferFee;
	}
	
	public Manager getManager() {
		return manager;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Double getTransferFee() {
		return transferFee;
	}
}