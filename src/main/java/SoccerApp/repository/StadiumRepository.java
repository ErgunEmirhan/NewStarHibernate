package SoccerApp.repository;

import SoccerApp.entity.Stadium;

public class StadiumRepository extends BaseRepository<Stadium, Long> {
	private static StadiumRepository instance;
	public static StadiumRepository getInstance() {
		if (instance == null) {
			instance = new StadiumRepository();
		}
		return instance;
	}
	private StadiumRepository() {
		super(Stadium.class);
	}
}