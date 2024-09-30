package SoccerApp.repository;

import SoccerApp.entity.mainEntity.Player;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.List;

public class PlayerRepository extends BaseRepository<Player, Long> {
	private static PlayerRepository instance;
	public static PlayerRepository getInstance() {
		if (instance == null) {
			instance = new PlayerRepository();
		}
		return instance;
	}
	public PlayerRepository() {
		super(Player.class);
	}
	
	public List<Player> findBetweeenRatings(int minRating, int maxRating) {
		CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
		Root<Player> root = query.from(Player.class);
		CriteriaQuery<Player> finalQuery =
				query.select(root).where(criteriaBuilder.between(root.get("rating"), minRating, maxRating));
		return getEntityManager().createQuery(finalQuery).getResultList();
	}
	
	public List<Player> findBetweenTransferFees(double minTransferFee, double maxTransferFee) {
		CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
		Root<Player> root = query.from(Player.class);
		CriteriaQuery<Player> finalQuery =
				query.select(root)
				     .where(criteriaBuilder.between(root.get("transferfee"), minTransferFee, maxTransferFee));
		return getEntityManager().createQuery(finalQuery).getResultList();
	}
	
	public List<Player> findBetweenAges(int minAge, int maxAge) {
		CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
		Root<Player> root = query.from(Player.class);
		Path<LocalDate> birthDate = root.get("birthDate");
		LocalDate earliestBirthDate = LocalDate.now().minusYears(maxAge);
		LocalDate latestBirthDate = LocalDate.now().minusYears(minAge);
		CriteriaQuery<Player> finalQuery =
				query.select(root).where(criteriaBuilder.between(birthDate, earliestBirthDate, latestBirthDate));
		return getEntityManager().createQuery(finalQuery).getResultList();
	}
	
	public List<Player> findByName(String nameFilter) {
		CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
		Root<Player> root = query.from(Player.class);
		Expression<String> concat1 = criteriaBuilder.concat(root.get("firstName"), " ");
		Expression<String> finalConcat = criteriaBuilder.concat(concat1, root.get("lastName"));
		query.select(root)
		     .where(criteriaBuilder.like(criteriaBuilder.lower(finalConcat),
		                                 "%" + nameFilter.toLowerCase() + "%"));
		return getEntityManager().createQuery(query).getResultList();
	}
}