package SoccerApp.service;

import SoccerApp.entity.Manager;
import SoccerApp.repository.ManagerRepository;

import java.util.Optional;

public class ManagerService extends BaseServices<Manager,Long> {
	private static ManagerService instance;
	private ManagerRepository repository=ManagerRepository.getInstance();
	public static ManagerService getInstance() {
		if (instance == null) {
			instance = new ManagerService();
		}
		return instance;
	}
	private ManagerService() {
		super(ManagerRepository.getInstance());
	}
	
	public Optional<Manager> findManagerByUsernameAndPassword(String username, String pw) {
		return repository.findManagerByUsernameAndPassword(username,pw);
	}
	
	public boolean isUsernameTaken(String username) {
		return repository.isUsernameTaken(username);
	}
	public boolean isEmailTaken(String email) {
		return repository.isEmailTaken(email);
	}
	public boolean isPhoneTaken(String phoneNo) {
		return repository.isPhoneTaken(phoneNo);
	}
	
	public void showMyClub(Manager manager) {
		repository.showMyClub(manager);
	}
	
	
}