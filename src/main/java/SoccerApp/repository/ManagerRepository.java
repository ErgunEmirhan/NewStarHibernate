package SoccerApp.repository;

import SoccerApp.entity.Manager;

public class ManagerRepository extends BaseRepository<Manager, Long> {
	private static ManagerRepository instance;
	public static ManagerRepository getInstance() {
		if (instance == null) {
			instance = new ManagerRepository();
		}
		return instance;
	}
	private ManagerRepository() {
		super(Manager.class);
	}
}