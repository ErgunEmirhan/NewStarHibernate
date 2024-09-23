package gui;

import SoccerApp.entity.Manager;
import SoccerApp.utility.InputHandler;

public class ManagerGui {
	private static ManagerGui instance;
	public static ManagerGui getInstance() {
		if (instance == null) {
			instance = new ManagerGui();
		}
		return instance;
	}
	
	private ManagerGui() {
	}
	
	public int managerGuiMainMenu(Manager manager) {
		int choice;
		do {
			System.out.println("""
					                   #### NewStarSoccer Menajer Arayüzüne Hoşgeldiniz ####
					                           1. Kendi kulübümü görüntüle
					                           2. Diğer kulüpleri görüntüle
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
			
			case 2:
			
		}
		
		return choice;
	}
}