package gui;

import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.entity.Club;
import SoccerApp.utility.InputHandler;

import java.util.List;

public class ClubGui {
	private static ClubGui instance;
	
	
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
			System.out.println("""
					                   #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
					                           1. İsme Göre Kulüp Ara
					                           2. Kulüpleri Listele
					                           0. Geri Dön
					                          -1. Çıkış
					                           """);
			
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
				
				break;
			case 2:
				
				break;
			case -1:
				System.out.println("Çıkış yapılıyor...");
				return choice;
			default:
				System.out.println("Geçersiz girdi.... x_x");
				return choice;
		}
		return choice;
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