package SoccerApp;

import SoccerApp.gui.MainGui;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Runner {
	public static void main(String[] args) {
		MainGui.getInstance().mainGuiMainMenu();
		
	}
}