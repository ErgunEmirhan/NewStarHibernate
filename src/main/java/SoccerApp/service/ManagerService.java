package SoccerApp.service;

import SoccerApp.entity.Manager;
import SoccerApp.repository.ManagerRepository;

public class ManagerService extends BaseServices<Manager,Long> {
	private static ManagerService instance;

	public static ManagerService getInstance() {
		if (instance == null) {
			instance = new ManagerService();
		}
		return instance;
	}
	private ManagerService() {
		super(ManagerRepository.getInstance());
	}
}