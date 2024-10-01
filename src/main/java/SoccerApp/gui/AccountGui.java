package SoccerApp.gui;

import SoccerApp.controller.ManagerController;
import SoccerApp.entity.combinedEntity.Account;
import SoccerApp.entity.mainEntity.Manager;

import java.util.Optional;
import java.util.Scanner;

public class AccountGui {
	private static AccountGui instance;
	public static AccountGui getInstance() {
		if (instance == null) {
			instance = new AccountGui();
		}
		return instance;
	}
	private Scanner scanner = new Scanner(System.in);
	ManagerController managerController;
	ManagerGui managerGui;
	
	private AccountGui() {
		managerController = ManagerController.getInstance();
		managerGui = ManagerGui.getInstance();
	}
	
	public void logIn() {
		System.out.println("#### LOGIN MENU ####");
		System.out.println("Username : ");
		String username = scanner.nextLine();
		System.out.println("Password : ");
		String pw = scanner.nextLine();
		Optional<Manager> optManager = findManagerByUsernameAndPassword(username, pw);
		if (optManager.isPresent()) {
			managerGui.managerGuiMainMenu(optManager.get());
		}
		else {
			System.out.println("Hatalı kullanıcı adı veya sifre");
		}
	}
	
	public void register() {
		System.out.println("#### REGISTER MENU ####");
		String username=takeUsername();
		String email=takeEmail();
		String phoneNo=takePhoneNo();
		System.out.println("Password : ");
		String pw = scanner.nextLine();
		Manager manager = Manager.builder()
		                         .account(Account.builder().username(username).password(pw).email(email)
		                                         .phoneNumber(phoneNo).build()).build();
		managerController.save(manager);
	}
	
	
	public String takeUsername() {
		while (true) {
			System.out.println("Username : ");
			String username = scanner.nextLine();
			if (!managerController.isUsernameTaken(username)) {
				return username;
			}
			System.out.println("Bu kullanıcı adı zaten kullanılıyor");
		}
	}
	
	public String takeEmail() {
		while (true) {
			System.out.println("Email : ");
			String email = scanner.nextLine();
			if (!managerController.isEmailTaken(email)) {
				return email;
			}
			System.out.println("Bu mail adresi zaten kullanılıyor");
		}
	}
	
	public String takePhoneNo() {
		while (true) {
			System.out.println("Telefon : ");
			String phoneNo = scanner.nextLine();
			if (!managerController.isPhoneTaken(phoneNo)) {
				return phoneNo;
			}
			System.out.println("Bu telefon numarası zaten kullanılıyor");
		}
	}
	
	private Optional<Manager> findManagerByUsernameAndPassword(String username, String pw) {
		return managerController.findManagerByUsernameAndPassword(username, pw);
	}
}