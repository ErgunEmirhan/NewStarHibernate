package SoccerApp.gui;

import SoccerApp.controller.ClubController;
import eski.entities.Kulup;
import SoccerApp.entity.Club;
import SoccerApp.utility.InputHandler;

import java.util.List;
import java.util.Scanner;

public class ClubGui {
	private static ClubGui instance;
	private ClubController clubController = ClubController.getInstance();
	private Scanner scanner = new Scanner(System.in);
	private List<Club> listedClubs;
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
					// kulup seç metodu
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
	
	private void findAllClubs() {
		List<Club> optListedClubs = clubController.findAll();
		if (optListedClubs.isEmpty()) {
			System.out.println("Kritere uyan kulup bulunamadi.");
			return;
		}
		else {
			listedClubs = optListedClubs;
			// listerClubs'ı model ile yazdır
		}
	}
	
	private List<Club> findClubByName() {
		System.out.print("Giriniz isim filtrenizi> ");
		String clubNameFilter = scanner.nextLine();
		return clubController.findClubByName(clubNameFilter);
	}
	//TODO : club seçme metodu oluşturulacak.
	
	private int clubMenu(Club club) {
		int choice;
		do {
			System.out.println(club.getName()+ "\n 1. Detayları görüntüle \n 2. Kadroyu görüntüle\n 0. Geri Dön\n-1. " + "Çıkış");
			choice = InputHandler.integerInput();
			if (choice == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			choice = menuKulupSecenekleri(club, choice);
		} while (choice != -1);
		return choice;
	}
	
	private int menuKulupSecenekleri(Club club, int choice) {
		switch (choice) {
			case 1:
				
				break;
			case 2:
				
				break;
			case -1:
				System.out.println("Çıkış yapılıyor....");
				break;
			default:
				System.out.println("Gecersiz girdi :(");
		}
		return choice;
	}
	
}