package SoccerApp.repository;

import SoccerApp.entity.Manager;
import SoccerApp.entity.OfferWithPlayer;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class OfferWithPlayerRepository extends BaseRepository<OfferWithPlayer, Long> {
	private static OfferWithPlayerRepository instance;
	public static OfferWithPlayerRepository getInstance() {
		if (instance == null) {
			instance = new OfferWithPlayerRepository();
		}
		return instance;
	}
	private OfferWithPlayerRepository() {
		super(OfferWithPlayer.class);
	}
	
	public List<OfferWithPlayer> findOwpByBuyingManager(Manager manager) {
		CriteriaQuery<OfferWithPlayer> query = criteriaBuilder.createQuery(OfferWithPlayer.class);
		Root<OfferWithPlayer> root = query.from(OfferWithPlayer.class);
		Path<Object> offerWithManager = root.get("offerWithManager");
		Path<Object> buyerManager = offerWithManager.get("buyerManager");
		query.select(root).where(criteriaBuilder.equal(buyerManager, manager));
		return getEntityManager().createQuery(query).getResultList();
	}
}