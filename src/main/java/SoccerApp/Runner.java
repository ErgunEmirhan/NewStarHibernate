package SoccerApp;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Runner {
	public static void main(String[] args) {
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("pu_hibernate");
		
		//Account account= Account.builder().username("harun").password("123123").build();
		//Manager manager=Manager.builder().account(account).build();

		//ManagerRepository managerRepository=ManagerRepository.getInstance();
		//managerRepository.save(manager);
		//Optional<Manager> harun = managerRepository.findManagerByUsernameAndPassword("harun", "123123");
		//System.out.println(harun);
	//	new AccountGui().register();
	}
}