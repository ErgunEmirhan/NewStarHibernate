package SoccerApp.service;

import SoccerApp.entity.LineUp;
import SoccerApp.repository.LineUpRepository;

public class LineUpService extends BaseServices<LineUp,Long> {
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