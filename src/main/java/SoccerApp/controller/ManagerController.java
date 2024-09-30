package SoccerApp.controller;

import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.service.ManagerService;
import jakarta.persistence.NoResultException;

import java.util.Optional;

public class ManagerController extends BaseController<Manager,Long> {
	private static ManagerController instance;
	private ManagerService service=ManagerService.getInstance();
	public static ManagerController getInstance() {
		if (instance == null) {
			instance = new ManagerController();
		}
		return instance;
	}
	private ManagerController() {
		super(ManagerService.getInstance());
	}
	
	public Optional<Manager> findManagerByUsernameAndPassword(String username, String pw) {
		return service.findManagerByUsernameAndPassword(username,pw);
	}
	
	public boolean isUsernameTaken(String username) {
		try {
			return service.isUsernameTaken(username);
		}
		catch (NoResultException e) {
			return false;
		}
		catch (Exception e){
			System.out.println("ManagerController "+e.getMessage());
			return true;
		}
	}
	public boolean isEmailTaken(String email) {
		try {
			return service.isEmailTaken(email);
		}
		catch (NoResultException e) {
			return false;
		}
		catch (Exception e){
			System.out.println("ManagerController "+e.getMessage());
			return true;
		}
	}
	public boolean isPhoneTaken(String phoneNo) {
		try {
			return service.isPhoneTaken(phoneNo);
		}
		catch (NoResultException e) {
			return false;
		}
		catch (Exception e){
			System.out.println("ManagerController "+e.getMessage());
			return true;
		}
	}
	
	public void showMyClub(Manager manager) {
		service.showMyClub(manager);
	}
	
	
}