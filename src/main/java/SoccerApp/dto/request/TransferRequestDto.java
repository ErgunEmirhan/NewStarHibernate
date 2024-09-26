package SoccerApp.dto.request;

import SoccerApp.entity.Manager;
import SoccerApp.entity.Player;

import java.time.LocalDate;

public class TransferRequestDto {
	private Manager manager;
	private Player player;
	private Double transferFee;
	private LocalDate transferDate;
	
	public TransferRequestDto(Manager manager, Player player, Double transferFee, LocalDate transferDate) {
		this.manager = manager;
		this.player = player;
		this.transferFee = transferFee;
		this.transferDate = transferDate;
	}
	
	public LocalDate getTransferDate() {
		return transferDate;
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