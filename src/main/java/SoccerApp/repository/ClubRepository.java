package SoccerApp.repository;

import SoccerApp.entity.mainEntity.Club;

import java.util.List;

public class ClubRepository extends BaseRepository<Club, Long> {
	private static ClubRepository instance;
	
	public static ClubRepository getInstance() {
		if (instance == null) {
			instance = new ClubRepository();
		}
		return instance;
	}
	
	private ClubRepository() {
		super(Club.class);
	}
	
	public List<Club> findByName(String name){
		return getEntityManager().createNativeQuery("SELECT * FROM tblclub WHERE name ILIKE '%" + name + "%'",Club.class).getResultList();
	}
}