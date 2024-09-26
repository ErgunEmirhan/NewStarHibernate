package SoccerApp.gui;

import SoccerApp.controller.PlayerController;
import SoccerApp.entity.Manager;
import SoccerApp.entity.Player;
import SoccerApp.utility.InputHandler;
import SoccerApp.utility.enums.Nationality;
import SoccerApp.utility.enums.Position;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class PlayerGui {
	private static PlayerGui instance;
	private PlayerController playerController;
	private Manager manager;
	private List<Player> listedPlayers = new ArrayList<>();
	private Integer pageLimit = 40; // TODO manager bu sayıyı değiştirebilsin
	private PlayerGui(){
		playerController = PlayerController.getInstance();
	}
	public static PlayerGui getInstance() {
		if(instance == null){
			instance = new PlayerGui();
		}
		return instance;
	}
	
	
	public Optional<Player> playerGuiMainMenu(Manager manager) {
		this.listedPlayers.clear();
		this.manager = manager;
		int choice;
		do {
			System.out.println("""
					                   #### NewStarSoccer Player Arayüzüne Hoşgeldiniz ####
					                           1. Görüntüle İsme Göre
											   2. Görüntüle Yaşa Göre
											   3. Görüntüle Pozisyona Göre
											   4. Görüntüle Uyruğa Göre
											   5. Görüntüle Reytinge Göre
											   6. Görüntüle Transfer Bedeline Göre
											   7. Filtreleri Temizle
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
		return null;
	}
	
	private int menuOptions(int choice, Manager manager) {
		switch (choice) {
			case 1:
				
				break;
			case 2:
				filterByAge();
				break;
			case 3:
				filterByPosition();
				break;
			case 4:
				filterByNationality();
				break;
			case 5:
				filterByRating();
				break;
			case 6:
				filterByTransferFee();
				break;
			case 7:
				listedPlayers.clear();
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
	
	private void filterByAge() {
		int minAge = InputHandler.integerInput("minimum age> ");
		int maxAge = InputHandler.integerInput("maximum age> ");
		if (listedPlayers.isEmpty())
			listedPlayers = playerController.findBetweenAges(minAge, maxAge);
		else listedPlayers = listedPlayers.stream()
		                                  .filter(player -> {
			                                  LocalDate birthDate = player.getBirthDate();
			                                  int age = Period.between(birthDate, LocalDate.now()).getYears();
			                                  return age >= minAge && age <= maxAge;
		                                  }).toList();
	}
	
	private void filterByTransferFee() {
		double minTransferFee = InputHandler.doubleInput("Enter minimum fee> ");
		double maxTransferFee = InputHandler.doubleInput("Enter maximum fee> ");
		if (listedPlayers.isEmpty())
			listedPlayers = playerController.findBetweenTransferFees(minTransferFee, maxTransferFee);
		else listedPlayers = listedPlayers.stream()
		                                  .filter(player -> {
			                                  double transferFee = player.getTransferFee();
			                                  return transferFee >= minTransferFee && transferFee <= maxTransferFee;
		                                  }).toList();
	}
	
	private void filterByRating() {
		int minRating = getRatingValue("asgari limit> ");
		int maxRating = getRatingValue("azami limit> ");
		if (listedPlayers.isEmpty())
			listedPlayers = playerController.findBetweenRatings(minRating, maxRating);
		else listedPlayers = listedPlayers.stream()
		                                  .filter(player -> {
											  int rating = player.getRating();
											  return rating >= minRating && rating <= maxRating;
		                                  }).toList();
	}
	
	private int getRatingValue(String message){
		while(true) {
			int value = InputHandler.integerInput(message);
			if (value < 0 || value > 100) return value;
			else System.out.println("Rating value must be between 0 and 100");
		}
	}
	
	private void filterByPosition() {
		Position[] values = Position.values();
		Position position;
		while(true) {
			for (int i = 0; i < values.length; i++) {
				System.out.println((++i) + ". " + values[i]);
			}
			
			int choiceIndex = InputHandler.integerInput("Choose a position> ") - 1;
			if(choiceIndex >= 0 && choiceIndex < values.length) {
				position = values[choiceIndex];
				break;
			}
			else System.out.println("Invalid position index");
			
		}
		if (listedPlayers.isEmpty())
			listedPlayers = playerController.findByPosition(position);
		else {
			listedPlayers = listedPlayers.stream()
			                             .filter(player -> player.getPosition().equals(position))
			                             .toList();
		}
	}
	
	private void filterByNationality() {
		Nationality[] values = Nationality.values();
		Nationality nationality;
		while(true) {
			for (int i = 0; i < values.length; i++) {
				System.out.println((++i) + ". " + values[i]);
			}
			
			int choiceIndex = InputHandler.integerInput("Choose a nationality> ") - 1;
			if(choiceIndex >= 0 && choiceIndex < values.length) {
				nationality = values[choiceIndex];
				break;
			}
			else System.out.println("Invalid nationality index");
			
		}
		if (listedPlayers.isEmpty())
			listedPlayers = playerController.findByNationality(nationality);
		else {
			listedPlayers = listedPlayers.stream()
			                             .filter(player -> player.getNationality().equals(nationality))
			                             .toList();
		}
	}
}