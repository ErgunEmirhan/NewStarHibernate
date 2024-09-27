package SoccerApp.gui;

import SoccerApp.controller.ManagerController;
import SoccerApp.controller.OfferWithManagerController;
import SoccerApp.controller.OfferWithPlayerController;
import SoccerApp.controller.TransferController;
import SoccerApp.dto.request.OfferWithManagerRequestDto;
import SoccerApp.entity.Manager;
import SoccerApp.entity.OfferWithManager;
import SoccerApp.entity.OfferWithPlayer;
import SoccerApp.entity.Player;
import SoccerApp.model.OwmBoxModel;
import SoccerApp.model.OwpBoxModel;
import SoccerApp.utility.InputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerGui {
	private Manager manager;
	private ManagerController managerController;
	private ClubGui clubGui;
	private TransferController transferController;
	private static ManagerGui instance;
	private PlayerGui playerGui;
	private OfferWithManagerController owmController;
	private OfferWithPlayerController owpController;
	private OwmGui owmGui;
	private OwpGui owpGui;
	private List<OfferWithManager> owmBox = new ArrayList<>();
	private List<OfferWithPlayer> owpBox = new ArrayList<>();
	
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
		owpGui = OwpGui.getInstance();
		owmGui = OwmGui.getInstance();
		owmController = OfferWithManagerController.getInstance();
		owpController = OfferWithPlayerController.getInstance();
	}
	
	public int managerGuiMainMenu(Manager manager) {
		this.manager = manager;
		this.owmBox.clear();
		int choice;
		do {
			System.out.println("""
					                   #### NewStarSoccer Menajer Arayüzüne Hoşgeldiniz ####
					                           1. Kendi kulübümü görüntüle
					                           2. Diğer kulüpleri görüntüle
					                           3. Transfer Teklifi Yap
					                           4. Menajerden Gelen Transfer Tekliflerini Görüntüle
					                           5. Futbolcudan Gelen Transfer Tekliflerini Görüntüle
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
		this.owmBox.clear();
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
				showOwmBox();
				chooseFromOwmBox();
				break;
			case 5:
				showOwpBox();
				chooseFromOwpBox();
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
	
	private void chooseFromOwpBox() {
		int owpIndex = InputHandler.integerInput("which owp you want to take action on?");
		try {
			OfferWithPlayer owp = owpBox.get(owpIndex - 1);
			owpGui.owpMainMenu(owp);
		}
		catch (Exception e) {
			System.out.println("could not find the owm..." + e.getMessage());
		}
	}
	
	private void showOwpBox() {
		if (owpBox.isEmpty()){
			owpBox = owpController.showOwpBox(manager);
		}
		OwpBoxModel.showOwpBox(owpBox);
	}
	
	private void chooseFromOwmBox() {
		int owmIndex = InputHandler.integerInput("which owm you want to take action on?");
		try {
			OfferWithManager owm = owmBox.get(owmIndex - 1);
			owmGui.owmMainMenu(owm);
		}
		catch (Exception e) {
			System.out.println("could not find the owm..." + e.getMessage());
		}
	}
	
	
	private void showOwmBox() {
		if (owmBox.isEmpty()) {
			owmBox = owmController.showOwmBox(manager);
		}
		OwmBoxModel.showOwmBox(owmBox);
	}
	
	private void makeOfferRequest(Player player) {
		Double transferFee = InputHandler.doubleInput("Enter transfer fee");
		OfferWithManagerRequestDto
				transferRequest = new OfferWithManagerRequestDto(manager, player, transferFee);
		owmController.makeTransferRequest(transferRequest);
	}
	
}