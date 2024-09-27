package SoccerApp.service;

import SoccerApp.entity.Club;
import SoccerApp.repository.ClubRepository;

import java.util.List;

public class ClubService extends BaseService<Club,Long> {
	private static ClubService instance;
	private static ClubRepository repository = ClubRepository.getInstance();
	
	private ClubService() {
		super(ClubRepository.getInstance());
	}
	
	public static ClubService getInstance() {
		if (instance == null) {
			instance = new ClubService();
		}
		return instance;
	}
	
	public List<Club> findByName(String name){
		return  repository.findByName(name);
	}
	
	
	
}