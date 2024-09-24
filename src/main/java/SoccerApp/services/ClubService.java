package SoccerApp.services;

import SoccerApp.entity.Club;
import SoccerApp.repository.ClubRepository;
import SoccerApp.utility.ICRUD;

import java.util.List;

public class ClubService extends BaseServices<Club,Long> {
	private static ClubService instance;
	
	public ClubService() {
		super(ClubRepository.getInstance());
	}
	
	public static ClubService getInstance() {
		if (instance == null) {
			instance = new ClubService();
		}
		return instance;
	}
	
//	public List<Club> findByName(String name){
//		return  clubRepository.findByName(name);
//	}
	
}