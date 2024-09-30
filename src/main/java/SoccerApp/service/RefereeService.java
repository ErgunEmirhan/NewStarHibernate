package SoccerApp.service;

import SoccerApp.entity.mainEntity.Referee;
import SoccerApp.repository.RefereeRepository;

public class RefereeService extends BaseService<Referee,Long> {
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