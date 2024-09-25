package SoccerApp.gui;

import SoccerApp.controller.ManagerController;
import SoccerApp.controller.TransferController;
import SoccerApp.entity.Manager;
import SoccerApp.utility.InputHandler;

public class ManagerGui {
	private ManagerController managerController;
	private ClubGui clubGui;
	private TransferController transferController;
	private static ManagerGui instance;
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
	}
	
	public int managerGuiMainMenu(Manager manager) {
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
			choice = menuSecenekleri(choice, manager);
		} while (choice != -1);
		return choice;
	}
	
	private int menuSecenekleri(int choice, Manager manager) {
		switch (choice) {
			case 1:
				managerController.showMyClub(manager);
				break;
			case 2:
				clubGui.clubGuiMainMenu();
				break;
			case 3:
				transferController.makeTransferRequest(manager);
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
	
}