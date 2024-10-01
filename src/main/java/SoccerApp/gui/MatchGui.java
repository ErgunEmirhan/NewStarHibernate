package SoccerApp.gui;


import SoccerApp.controller.MatchController;
import SoccerApp.engine.MatchEngine;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.utility.InputHandler;
import SoccerApp.utility.LineupArrangeAlgorithm;

import java.util.Optional;

public class MatchGui {
	private static MatchGui instance;
	private MatchController controller;
	
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
			choice = InputHandler.integerInput();
			if (choice == 0) break;
			
			choice = matchGuiMainMenuOptions(choice);
		} while(choice != -1);
		
		return choice;
	}
	
	private int matchGuiMainMenuOptions(int choice) {
		switch (choice){
			case 1:
				nextMatch();
				break;
			default:
				System.out.println("what??");
		}
		return choice;
	}
	
	private void nextMatch() {
		Optional<Match> optMatch = findEarliestMatch();
		if(optMatch.isEmpty()){
			System.out.println("All matches in the database are already played or is playing.");
			return;
		}
		Match match = optMatch.get();
		
		assignLineups(match);
		
		MatchEngine.play(match);
	}
	
	private void assignLineups(Match match) {
		LineupArrangeAlgorithm.assignLineups(match);
	}
	
	private Optional<Match> findEarliestMatch() {
		return controller.findEarliestMatch();
	}
	
	
}