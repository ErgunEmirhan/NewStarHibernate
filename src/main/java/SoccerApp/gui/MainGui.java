package SoccerApp.gui;

import SoccerApp.utility.InputHandler;

public class MainGui {
	private static MainGui instance;
	private  ClubGui clubGui;
	private LeagueGui leagueGui;
	private MatchGui matchGui;
	private ManagerGui managerGui;
	private AccountGui accountGui;
	public static MainGui getInstance() {
		if (instance == null) {
			instance = new MainGui();
		}
		return instance;
	}
	
	private MainGui() {
		this.clubGui=ClubGui.getInstance();
		this.leagueGui=LeagueGui.getInstance();
		this.matchGui=MatchGui.getInstance();
		this.managerGui=ManagerGui.getInstance();
		this.accountGui=AccountGui.getInstance();
	}
	
	//TODO : zaman ilerleten thread yapılacak.
	private int mainGuiMainMenu() {
		int choice;
		System.out.println("""
				                    _   _                 ____  _               ____                         \s
				                   | \\ | | _____      __ / ___|| |_ __ _ _ __  / ___|  ___   ___ ___ ___ _ __\s
				                   |  \\| |/ _ \\ \\ /\\ / / \\___ \\| __/ _` | '__| \\___ \\ / _ \\ / __/ __/ _ \\ '__|
				                   | |\\  |  __/\\ V  V /   ___) | || (_| | |     ___) | (_) | (_| (_|  __/ |  \s
				                   |_| \\_|\\___| \\_/\\_/   |____/ \\__\\__,_|_|    |____/ \\___/ \\___\\___\\___|_|  \s
				                      / \\   _ __  _ __                                                       \s
				                     / _ \\ | '_ \\| '_ \\                                                      \s
				                    / ___ \\| |_) | |_) |                                                     \s
				                   /_/   \\_\\ .__/| .__/                                                      \s
				                           |_|   |_|                                                         \s""");
		do {
			System.out.println("""
					                   #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
					                           1. Kulüp Modül
					                           2. Menajer Modül
					                           3. Lig Modül
					                           4. Musabaka Modül
					                           0. Geri Dön
					                          -1. Çıkış
					                           """);
			choice = InputHandler.integerInput();
			if (choice == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			choice = this.mainGuiMainMenuOptions(choice);
		} while (choice != -1);
		System.out.println("Uygulama sonlandırılıyor....");
		return choice;
	}
	
	
	private int mainGuiMainMenuOptions(int choice) {
		switch (choice) {
			case 1:
				return clubGui.clubGuiMainMenu();
			case 2:
				 accountGui.logIn();
			break;
			case 3:
				return leagueGui.leagueGuiMainMenu();
			case 4:
				return matchGui.matchGuiMainMenu();
			case -1:
				return choice;
		}
		return choice;
	}
	
	
	
}