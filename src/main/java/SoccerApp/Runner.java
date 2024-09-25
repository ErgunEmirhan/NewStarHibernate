package SoccerApp;

import SoccerApp.controller.ClubController;
import SoccerApp.entity.Account;
import SoccerApp.entity.Club;
import SoccerApp.entity.Manager;
import SoccerApp.entity.Player;
import SoccerApp.gui.AccountGui;
import SoccerApp.model.ClubSquadModel;
import SoccerApp.model.PlayerDetailedModel;
import SoccerApp.repository.ManagerRepository;
import SoccerApp.utility.enums.Position;

import java.util.List;
import java.util.Optional;

public class Runner {
	public static void main(String[] args) {
		
		Account account= Account.builder().username("harun").password("123123").build();
		Manager manager=Manager.builder().account(account).build();

		ManagerRepository managerRepository=ManagerRepository.getInstance();
		managerRepository.save(manager);
		//Optional<Manager> harun = managerRepository.findManagerByUsernameAndPassword("harun", "123123");
		//System.out.println(harun);
		new AccountGui().register();
	}
}