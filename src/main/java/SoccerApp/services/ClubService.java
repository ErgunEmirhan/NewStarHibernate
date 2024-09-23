package SoccerApp.services;

import SoccerApp.entity.Club;
import SoccerApp.repository.ClubRepository;

import java.util.List;

public class ClubService extends BaseServices<Club,Long> {
	private static ClubService instance;
	private ClubRepository clubRepository;
	
	public static ClubService getInstance() {
		if (instance == null) {
			instance = new ClubService();
		}
		return instance;
	}
	private ClubService() {
		clubRepository = ClubRepository.getInstance();
	}
	public List<Club> findByName(String name){
		return  clubRepository.findByName(name);
	}
	
}