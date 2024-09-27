package SoccerApp.repository;

import SoccerApp.entity.Manager;
import SoccerApp.entity.OfferWithManager;
import SoccerApp.utility.enums.ManagerOfferStatus;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class OfferWithManagerRepository extends BaseRepository<OfferWithManager, Long> {
	private static OfferWithManagerRepository instance;
	public static OfferWithManagerRepository getInstance() {
		if (instance == null) {
			instance = new OfferWithManagerRepository();
		}
		return instance;
	}
	private OfferWithManagerRepository() {
		super(OfferWithManager.class);
	}
	
	public List<OfferWithManager> findByReceivingManager(Manager manager) {
		CriteriaQuery<OfferWithManager> query = criteriaBuilder.createQuery(OfferWithManager.class);
		Root<OfferWithManager> root = query.from(OfferWithManager.class);
		
		Predicate ownerManagerRestriction = criteriaBuilder.equal(root.get("ownerManager"), manager);
		Predicate ownerStatusRestriction = criteriaBuilder.equal(root.get("offerStatus"),
		                                                         ManagerOfferStatus.BUYER_OFFER);
		
		Predicate buyerManagerRestriction = criteriaBuilder.equal(root.get("buyerManager"), manager);
		Predicate buyerStatusRestriction = criteriaBuilder.equal(root.get("offerStatus"),
		                                                         ManagerOfferStatus.OWNER_OFFER);
		
		query.select(root)
		     .where(criteriaBuilder.or(criteriaBuilder.and(ownerManagerRestriction,
		                                                   ownerStatusRestriction),
		                               criteriaBuilder.and(buyerManagerRestriction,
		                                                   buyerStatusRestriction)));
		return getEntityManager().createQuery(query).getResultList();
	}
}