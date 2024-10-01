package SoccerApp.repository;

import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class MatchRepository extends BaseRepository<Match, Long> {
	private static MatchRepository instance;
	public static MatchRepository getInstance() {
		if (instance == null) {
			instance = new MatchRepository();
		}
		return instance;
	}
	
	private MatchRepository() {
		super(Match.class);
	}
	
	public List<Match> findByClubAndLeagueId(Club club, League league) {
		CriteriaQuery<Match> query = criteriaBuilder.createQuery(Match.class);
		Root<Match> root = query.from(Match.class);
		query.select(root).where(criteriaBuilder
             .and(criteriaBuilder.or(criteriaBuilder
                      .equal(root.get("home"), club), criteriaBuilder.equal(root.get("away"), club)),
                  criteriaBuilder.equal(root.get("league"), league)));
		return getEntityManager().createQuery(query).getResultList();
	}
}