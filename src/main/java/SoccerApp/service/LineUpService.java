package SoccerApp.service;

import SoccerApp.entity.combinedEntity.LineUp;
import SoccerApp.repository.LineUpRepository;

public class LineUpService extends BaseService<LineUp,Long> {
	private static LineUpService instance;
	public static LineUpService getInstance() {
		if (instance == null) {
			instance = new LineUpService();
		}
		return instance;
	}
	private LineUpService() {
		super(LineUpRepository.getInstance());
	}
}