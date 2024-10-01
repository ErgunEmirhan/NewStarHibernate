package SoccerApp.repository;

import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.utility.enums.MatchStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

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
	
	public Match findEarliestMatch() {
		CriteriaQuery<Match> query = criteriaBuilder.createQuery(Match.class);
		Root<Match> root = query.from(Match.class);
		query.select(root).where(criteriaBuilder
                 .equal(root.get("status"), MatchStatus.NOT_STARTED))
				.orderBy(criteriaBuilder.asc(root.get("matchDate")));
		return getEntityManager().createQuery(query).setMaxResults(1).getSingleResult();
	}
}