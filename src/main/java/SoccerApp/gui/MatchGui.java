package SoccerApp.gui;

import eski.NewStarSoccerApp;
import eski.entities.*;
import eski.util.enums.EMacStatu;
import eski.util.enums.EMevki;

import java.util.*;

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
				play();
				break;
			default:
				System.out.println("what??");
		}
		return choice;
	}
	
	private void play(Map<EMevki, List<Futbolcu>> evSahibiKadroIlkOnBir, Map<EMevki, List<Futbolcu>> deplasmanIlkOnBir, Musabaka musabaka, Lig lig) {
		Random random = new Random();
		var evSahibiMevkiPuanlari = new HashMap<EMevki, Integer>();
		var deplasmanMevkiPuanlari = new HashMap<EMevki, Integer>();
		
		
		deplasmanIst.artirBeraberlik();
		evSahibiIst.artirBeraberlik();
		
		evSahibiKadroIlkOnBir.forEach((k, v) -> evSahibiMevkiPuanlari.put(k, v.stream().map(fut -> fut.getYetenekPuani()).reduce(0, (p1, p2) -> p1 + p2)));
		deplasmanIlkOnBir.forEach((k, v) -> deplasmanMevkiPuanlari.put(k, v.stream().map(fut -> fut.getYetenekPuani()).reduce(0, (p1, p2) -> p1 + p2)));
		
		kadroYazdir(evSahibiKulupAdi, deplasmanKulupAdi, evSahibiKadroIlkOnBir, deplasmanIlkOnBir);
		
		int maxSure = musabaka.getSure();
		int sure;
		EMacStatu status; // ilkStatus
		for (int i = 0; i < 2; i++) {
			sure = 0;
			status = EMacStatu.values()[((random.nextInt(2) + i) % 2) + 4];
			while (sure < maxSure/2){
				try {
					Thread.sleep(100);
					sure += random.nextInt(3, 6);
					status = nextStatus(status, evSahibiMevkiPuanlari, deplasmanMevkiPuanlari, evSahibiKadroIlkOnBir, deplasmanIlkOnBir, musabaka, lig);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}