package SoccerApp.gui;

import SoccerApp.controller.ClubController;
import SoccerApp.entity.Player;
import SoccerApp.model.ClubDetailsModel;
import SoccerApp.model.ClubSquadModel;
import SoccerApp.model.ClubsListedModel;
import SoccerApp.model.PlayerDetailedModel;
import eski.entities.Kulup;
import SoccerApp.entity.Club;
import SoccerApp.utility.InputHandler;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ClubGui {
	private static ClubGui instance;
	private ClubController clubController = ClubController.getInstance();
	private Scanner scanner = new Scanner(System.in);
	private List<Club> listedClubs;
	private List<Player> listedPlayers;
	public static ClubGui getInstance() {
		if(instance==null){
			instance=new ClubGui();
		}
		return instance;
	}
	
	private ClubGui() {
	}
	
	public int clubGuiMainMenu() {
		int secim;
		do {
			System.out.println("#### NewStarSoccer Uygulamasına Hoşgeldiniz ####");
			System.out.println("1. İsme Göre Kulüp Ara");
			System.out.println("2. Kulüpleri Listele");
			if (!listedClubs.isEmpty()) System.out.println("3. Seç Kulüp");
			System.out.println("0. Geri Dön");
			System.out.println("-1. Çıkış");
			
			secim = InputHandler.integerInput();
			if (secim == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			secim = clubGuiMainMenuOptions(secim);
		} while (secim != -1);
		return secim;
	}
	
	private int clubGuiMainMenuOptions(int choice) {
		List<Kulup> kulupler = null;
		switch (choice) {
			case 1:
				findClubByName();
				break;
			case 2:
				findAllClubs();
				break;
			case 3:
				if (listedClubs.isEmpty()) System.out.println("Geçersiz girdi.... x_x");
				else {
					return chooseClub();
				}
			case -1:
				System.out.println("Çıkış yapılıyor...");
				return choice;
			default:
				System.out.println("Geçersiz girdi.... x_x");
				return choice;
		}
		return choice;
	}
	
	private int chooseClub() {
		int clubIndex = InputHandler.integerInput("Giriniz numarasını seçmek istediğiniz kulübün> ");
		try{
			Club club = listedClubs.get(clubIndex + 1);
			return clubMenu(club);
		}
		catch (Exception e) {
			System.out.println("Girdiğiniz index'te bir kulüp bulunamadı... " + e.getMessage());
			return 3;
		}
	}
	
	private void findAllClubs() {
		List<Club> optListedClubs = clubController.findAll();
		listedClubs = optListedClubs;
		ClubsListedModel.listClubs(listedClubs);
		if (listedClubs.isEmpty()) {
			System.out.println("Veritabanı hatası, sayfayı yenileyiniz");
		}
	}
	
	private void findClubByName() {
		System.out.print("Giriniz isim filtrenizi> ");
		String clubNameFilter = scanner.nextLine();
		
		List<Club> optListedClubs = clubController.findClubByName(clubNameFilter);
		if (optListedClubs.isEmpty()) {
			System.out.println("Kritere uyan kulup bulunamadi.");
			return;
		}
		else {
			listedClubs = optListedClubs;
			ClubsListedModel.listClubs(listedClubs);
		}
	}
	
	
	private int clubMenu(Club club) {
		int choice;
		do {
			System.out.println("###  Club Menu  ###");
			System.out.println(" 1. Detayları görüntüle");
			System.out.println(" 2. Kadroyu görüntüle");
			if (!listedPlayers.isEmpty()) System.out.println(" 3. Görüntüle oyuncu");
			System.out.println(" 0. Geri Dön");
			System.out.println("-1. Çıkış");
			choice = InputHandler.integerInput();
			if (choice == 0) {
				System.out.println("Geri dönülüyor");
				listedPlayers.clear();
				break;
			}
			choice = menuKulupSecenekleri(club, choice);
		} while (choice != -1);
		return choice;
	}
	
	private int menuKulupSecenekleri(Club club, int choice) {
		switch (choice) {
			case 1:
				showClubDetails(club);
				break;
			case 2:
				showClubSquad(club);
				break;
			case 3:
				if (listedPlayers.isEmpty()) {
					System.out.println("Geçersiz girdi :(");
				}
				else
					showClubPlayer(club);
			case -1:
				System.out.println("Çıkış yapılıyor....");
				listedPlayers.clear();
				break;
			default:
				System.out.println("Gecersiz girdi :(");
		}
		return choice;
	}
	
	private void showClubPlayer(Club club) {
		int clubPlayerIndex = InputHandler.integerInput("Görüntülemek istediğiniz oyuncunun indeksini giriniz> ");
		try{
			Player player = listedPlayers.get(clubPlayerIndex + 1);
			PlayerDetailedModel.showDetails(player);
		}
		catch (Exception e) {
			System.out.println("No player with the given index");
		}
	}
	
	private void showClubSquad(Club club) {
		List<Player> clubSquad = club.getSquad().stream().toList();
		ClubSquadModel.showSquad(clubSquad);
		listedPlayers = clubSquad;
	}
	
	private void showClubDetails(Club club) {
		ClubDetailsModel.showDetails(club);
	}
	
}