package SoccerApp;

import SoccerApp.gui.MainGui;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.concurrent.ConcurrentHashMap;

public class Runner {
	public static void main(String[] args) {
		MainGui.getInstance().mainGuiMainMenu();
	}
}