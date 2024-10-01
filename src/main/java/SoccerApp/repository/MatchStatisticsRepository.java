package SoccerApp.repository;

import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class MatchStatisticsRepository extends BaseRepository<MatchStatistics, Long> {
	private static MatchStatisticsRepository instance;

	public static MatchStatisticsRepository getInstance() {
		if (instance == null) {
			instance = new MatchStatisticsRepository();
		}
		return instance;
	}
	
	public MatchStatisticsRepository() {
		super(MatchStatistics.class);
	}
	
	public MatchStatistics findByClubAndLeague(Club club, League league) {
		CriteriaQuery<MatchStatistics> query = criteriaBuilder.createQuery(MatchStatistics.class);
		Root<MatchStatistics> root = query.from(MatchStatistics.class);
		query.select(root).where(criteriaBuilder.equal(root.get("club"), club),
		                         criteriaBuilder.equal(root.get("league"), league));
		return getEntityManager().createQuery(query).getSingleResult();
	}
}