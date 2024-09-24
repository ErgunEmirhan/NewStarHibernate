package SoccerApp.gui;

import SoccerApp.utility.InputHandler;

public class LeagueGui {
	private static LeagueGui instance;

	public static LeagueGui getInstance() {
		if (instance == null) {
			instance = new LeagueGui();
		}
		return instance;
	}
	
	private LeagueGui() {
	}
	
	public int leagueGuiMainMenu() {
		int choice;
		do {
			System.out.println("""
					                    1. Olustur Lig
					                    2. Kulup Ekle Lige
					                    3. Goruntule Ligdeki Katilimci Kulupler
					                    4. Fikstur Olustur
					                    5. Goruntule Fikstur
					                    6. Goruntule Kulup Fikstur
					                    7. Goruntule Puan Tablosu
					                    0. Geri Don
					                   -1. Kapa programi
					                    """);
			choice = InputHandler.integerInput();
			if (choice==0){
				break;
			}
			choice = leagueGuiMainMenuOptions(choice);
		} while (choice != -1);
		return choice;
	}
	
	public int leagueGuiMainMenuOptions(int choice) {
		switch (choice) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case -1:
				break;
			default:
				System.out.println("Girdi gecersiz x_x");
		}
		return choice;
	}
}