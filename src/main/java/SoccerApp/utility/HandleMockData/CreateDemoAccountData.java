package SoccerApp.utility.HandleMockData;

import SoccerApp.controller.ManagerController;
import SoccerApp.entity.Account;
import SoccerApp.entity.Manager;

import java.util.ArrayList;
import java.util.List;

public class CreateDemoAccountData {
	public static void createDemoAccountDataForManagers() {
		List<Manager> managers = ManagerController.getInstance().findAll();
		for (int i = 0; i < 100; i++) {
			String email = "menajer" + i + "@mail.com";
			String password = "password" + i;
			String phoneNumber = "55544433" + i%10 + i%10;
			String username = "username" + i;
			Account account =
					Account.builder().email(email).password(password).phoneNumber(phoneNumber).username(username)
					       .build();
			managers.get(i).setAccount(account);
		}
		ManagerController.getInstance().updateAll(managers);
		// bu düzgün çalışmazsa update'i çağır :D
	}
	
	public static void updateDemoAccountDataForManagers() {
		List<Manager> managers = ManagerController.getInstance().findAll();
		for (int i = 0; i < 100; i++) {
			String email = "menajer" + i + "@mail.com";
			String password = "password" + i;
			String phoneNumber = "55544433" + i%10 + i%10;
			String username = "username" + i;
			
			Account account = managers.get(i).getAccount();
			
			account.setEmail(email);
			account.setPassword(password);
			account.setPhoneNumber(phoneNumber);
			account.setUsername(username);
		}
		ManagerController.getInstance().updateAll(managers);
	}
}