package SoccerApp.utility.HandleMockData;

import SoccerApp.controller.ClubController;
import SoccerApp.controller.ManagerController;
import SoccerApp.controller.PlayerController;
import SoccerApp.controller.StadiumController;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.entity.mainEntity.Player;
import SoccerApp.entity.mainEntity.Stadium;

import java.util.List;

public class ForeignKeyOrganizer {
	public static void clubsAndManagers() {
		List<Club> clubs = ClubController.getInstance().findAll();
		List<Manager> managers = ManagerController.getInstance().findAll();
		for (int i = 0; i < clubs.size(); i++) {
			clubs.get(i).setManager(managers.get(i));
		}
		ClubController.getInstance().updateAll(clubs);
	}
	
	public static void clubsAndStadiums() {
		List<Club> clubs = ClubController.getInstance().findAll();
		List<Stadium> stads = StadiumController.getInstance().findAll();
		for (int i = 0; i < clubs.size(); i++) {
			clubs.get(i).setStadium(stads.get(i));
			clubs.get(i).setStadiumName(clubs.get(i).getStadium().getName());
		}
		ClubController.getInstance().updateAll(clubs);
	}
	
	public static void clubsAndPlayers(){
		List<Club> clubs = ClubController.getInstance().findAll();
		List<Player> players = PlayerController.getInstance().findAll();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setClub(clubs.get(i/30));
		}
		PlayerController.getInstance().updateAll(players);
	}
}