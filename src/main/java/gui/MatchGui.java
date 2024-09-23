package gui;

import SoccerApp.NewStarSoccerApp;

public class MatchGui {
	private static MatchGui instance;
	
	public static MatchGui getInstance() {
		if (instance == null) {
			instance = new MatchGui();
		}
		return instance;
	}
	private MatchGui() {
	}
	public int matchGuiMainMenu(){
		int choice = -1;
		do {
			System.out.println("""
				                  ### Musabaka Mod ###
				                  1. Mac Oynat
				                  0. Geri
				                  -1. Cikis
				                  """);
			choice = NewStarSoccerApp.yapSecim("Seciminiz: ");
			if (choice == 0) break;
			
			choice = matchGuiMainMenuOptions(choice);
		} while(choice != -1);
		
		return choice;
	}
	
	private int matchGuiMainMenuOptions(int choice) {
		switch (choice){
			case 1:
			
		}
		return choice;
	}
}