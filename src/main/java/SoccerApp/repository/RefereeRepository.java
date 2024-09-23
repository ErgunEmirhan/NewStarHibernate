package SoccerApp.repository;

import SoccerApp.entity.Referee;

public class RefereeRepository extends BaseRepository<Referee, Long> {
	private static RefereeRepository instance;
	public static RefereeRepository getInstance() {
		if (instance == null) {
			instance = new RefereeRepository();
		}
		return instance;
	}
	private RefereeRepository() {
		super(Referee.class);
	}
}