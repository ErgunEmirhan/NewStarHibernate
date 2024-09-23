package SoccerApp.services;

import SoccerApp.entity.Referee;
import SoccerApp.repository.RefereeRepository;

public class RefereeService extends BaseServices<Referee,Long> {
	private static RefereeService instance;

	public static RefereeService getInstance() {
		if (instance == null) {
			instance = new RefereeService();
		}
		return instance;
	}
	private RefereeService() {
		super(RefereeRepository.getInstance());
	}
}