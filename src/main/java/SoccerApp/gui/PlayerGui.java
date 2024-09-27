package SoccerApp.gui;

import SoccerApp.controller.PlayerController;
import SoccerApp.entity.Manager;
import SoccerApp.entity.Player;
import SoccerApp.model.PlayerDetailedModel;
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
	private Integer pageEntityLimit; // TODO manager bu sayıyı değiştirebilsin
	private Integer currentPageIndex;
	private Optional<Player> chosenPlayer;
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
		currentPageIndex = 1;
		pageEntityLimit = 40;
		listedPlayers.clear();
		int choice;
		do {
			this.listedPlayers.clear();
			this.manager = manager;
			
			System.out.println("### NSS PlayerGui Main Menu ###");
			System.out.println(" 1. Önceki Sayfa");
			System.out.println(" 2. Sonraki Sayfa");
			System.out.println(" 3. Filtre Uygula");
			System.out.println(" 4. Filtre Temizle");
			System.out.println(" 5. Listeden Oyuncu Seç");
			System.out.println(" 0. Geri Dön");
			choice = InputHandler.integerInput();
			choice = menuOptions(choice);
			if (choice == 1 || choice == 2 || choice == 3) printListedPlayers();
		} while(choice != 0 && chosenPlayer.isEmpty());
		
		return chosenPlayer;
	}
	
	private void printListedPlayers() {
		for (int i = pageEntityLimit*(currentPageIndex-1); i < pageEntityLimit*currentPageIndex; i++) {
			System.out.println("## " + (++i) + ". Player ##");
			PlayerDetailedModel.showDetails(listedPlayers.get(i));
		}
	}
	
	private int menuOptions(int choice) {
		switch(choice){
			case 3:
				if (playerGuiFilterMenu() == 0) return 0;
				break;
			case 4:
				listedPlayers.clear();
				break;
			case 1:
				previousPage();
				break;
			case 2:
				nextPage();
				break;
			case 5:
				choosePlayerFromListedPlayers();
				break;
			default:
				System.out.println("Geçerli bir seçim yapınız... x_x");
		}
		return choice;
	}
	
	private void choosePlayerFromListedPlayers() {
		int playerIndex = InputHandler
				.integerInput("Enter the number of the player on the page which you want to choose") - 1;
		if (playerIndex < pageEntityLimit*(currentPageIndex-1) ||
				playerIndex >= pageEntityLimit*(currentPageIndex)){
			System.out.println("Out of the range of the page...");
		}
		else {
			chosenPlayer = Optional.of(listedPlayers.get(playerIndex));
		}
	}
	
	private void previousPage() {
		if (listedPlayers.isEmpty()){
			System.out.println("You have to apply at least one filter");
		}
		else if(currentPageIndex == 1){
			System.out.println("You are already at the first page");
		}
		else{
			currentPageIndex--;
		}
	}
	
	private void nextPage() {
		int nextPageMinEntityIndex = (currentPageIndex -1)*pageEntityLimit;
		boolean hasNextPage = (nextPageMinEntityIndex < listedPlayers.size());
		
		if (listedPlayers.isEmpty()){
			System.out.println("You have to apply at least one filter");
		}
		else if(!hasNextPage){
			System.out.println("You are already at the last page");
		}
		else{
			currentPageIndex++;
		}
	}
	
	private int playerGuiFilterMenu(){
		int choice;
	
		System.out.println("""
				                   #### NewStarSoccer Player Filter Menu Hoşgeldiniz ####
				                           1. Görüntüle İsme Göre
										   2. Görüntüle Yaşa Göre
										   3. Görüntüle Pozisyona Göre
										   4. Görüntüle Uyruğa Göre
										   5. Görüntüle Reytinge Göre
										   6. Görüntüle Transfer Bedeline Göre
				                           0. Menajer Menüye Geri Dön
				                           """);
		
		choice = InputHandler.integerInput();
		if (choice == 0) {
			System.out.println("Returning to the manager menu");
			return 0;
		}
		choice = filterMenuOptions(choice);
		
		return choice;
	}
	
	
	private int filterMenuOptions(int choice) {
		switch (choice) {
			case 1:
				filterByName();
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
	
	private void filterByName() {
		System.out.print("Enter your name filter here> ");
		String nameFilter = new Scanner(System.in).nextLine();
		if (listedPlayers.isEmpty())
			listedPlayers = playerController.findByName(nameFilter);
		else listedPlayers = listedPlayers.stream()
		                                  .filter(player -> {
			String fullName = player.getFirstName() + " " + player.getLastName();
			return fullName.contains(nameFilter);
		}).toList();
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