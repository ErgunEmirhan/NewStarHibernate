package SoccerApp.repository;

import SoccerApp.entity.combinedEntity.LineUp;

public class LineUpRepository extends BaseRepository<LineUp, Long> {
	private static LineUpRepository instance;
	
	public static LineUpRepository getInstance() {
		if (instance == null) {
			instance = new LineUpRepository();
		}
		return instance;
	}
	
	public LineUpRepository() {
		super(LineUp.class);
	}
}