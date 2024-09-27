package SoccerApp.gui;

import SoccerApp.controller.ManagerController;
import SoccerApp.controller.TransferController;
import SoccerApp.dto.request.TransferRequestDto;
import SoccerApp.entity.Manager;
import SoccerApp.entity.Player;
import SoccerApp.utility.InputHandler;

import java.time.LocalDate;
import java.util.Optional;

public class ManagerGui {
	private Manager manager;
	private ManagerController managerController;
	private ClubGui clubGui;
	private TransferController transferController;
	private static ManagerGui instance;
	private PlayerGui playerGui;
	
	public static ManagerGui getInstance() {
		if (instance == null) {
			instance = new ManagerGui();
		}
		return instance;
	}
	
	private ManagerGui() {
		managerController = ManagerController.getInstance();
		clubGui=ClubGui.getInstance();
		transferController=TransferController.getInstance();
		playerGui=PlayerGui.getInstance();
	}
	
	public int managerGuiMainMenu(Manager manager) {
		this.manager = manager;
		int choice;
		do {
			System.out.println("""
					                   #### NewStarSoccer Menajer Arayüzüne Hoşgeldiniz ####
					                           1. Kendi kulübümü görüntüle
					                           2. Diğer kulüpleri görüntüle
					                           3. Transfer Teklifi Yap
					                           4. Transfer Tekliflerini Görüntüle
					                           0. Hesaptan Çık
					                          -1. Çıkış
					                           """);
			
			choice = InputHandler.integerInput();
			if (choice == 0) {
				System.out.println("Hesaptan çıkış yapılıyor");
				break;
			}
			choice = menuOptions(choice, manager);
		} while (choice != -1);
		return choice;
	}
	
	private int menuOptions(int choice, Manager manager) {
		switch (choice) {
			case 1:
				managerController.showMyClub(manager);
				break;
			case 2:
				clubGui.clubGuiMainMenu();
				break;
			case 3:
				Optional<Player> optPlayer = playerGui.playerGuiMainMenu(manager);
				optPlayer.ifPresent(this::makeOfferRequest);
				break;
			case 4:
				break;
			case -1:
				System.out.println("Çıkış Yapılıyor...");
				return choice;
			case 0:
				System.out.println("Hesaptan çıkış yapılıyor...");
				return choice;
			default:
				System.out.println("Geçerli bir seçim yapınız... x_x");
		}
		return choice;
	}
	
	private void makeOfferRequest(Player player) {
		LocalDate transferDate = InputHandler.localDateInput("Transfer tarihi giriniz");
		Double transferFee = InputHandler.doubleInput("Enter transfer fee");
		TransferRequestDto transferRequest = new TransferRequestDto(manager, player, transferFee, transferDate);
		transferController.makeTransferRequest(transferRequest);
	}
	
}