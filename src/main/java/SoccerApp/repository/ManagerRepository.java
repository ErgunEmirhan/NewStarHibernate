package SoccerApp.repository;

import SoccerApp.entity.Account;
import SoccerApp.entity.Manager;
import SoccerApp.model.ClubDetailsModel;
import jakarta.persistence.criteria.*;

import java.util.Optional;

public class ManagerRepository extends BaseRepository<Manager, Long> {
	private static ManagerRepository instance;
	public static ManagerRepository getInstance() {
		if (instance == null) {
			instance = new ManagerRepository();
		}
		return instance;
	}
	private ManagerRepository() {
		super(Manager.class);
	}
	
	public Optional<Manager> findManagerByUsernameAndPassword(String username, String pw) {
		CriteriaQuery<Manager> criteriaQuery = criteriaBuilder.createQuery(Manager.class);
		Root<Manager> root = criteriaQuery.from(Manager.class);
		Join<Manager, Account> accountJoin= root.join("account", JoinType.INNER);
		criteriaQuery.select(root).where(criteriaBuilder.equal(accountJoin.get("username"), username),
		                              criteriaBuilder.equal(accountJoin.get("password"), pw));
		Manager manager = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		return Optional.of(manager);
	}
	
	public boolean isUsernameTaken(String username) {
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
		Root<Account> root = criteriaQuery.from(Account.class);
		//Join<Manager, Account> accountJoin= root.join("account", JoinType.INNER);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
		Account managerUsername = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		if (managerUsername==null){
			return false;
		}
		return true;
	}
	public boolean isEmailTaken(String email) {
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
		Root<Account> root = criteriaQuery.from(Account.class);
		//Join<Manager, Account> accountJoin= root.join("account", JoinType.INNER);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
		Account managerEmail = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		if (managerEmail==null){
			return false;
		}
		return true;
	}
	public boolean isPhoneTaken(String phoneNo) {
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
		Root<Account> root = criteriaQuery.from(Account.class);
		//Join<Manager, Account> accountJoin= root.join("account", JoinType.INNER);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("phoneNumber"), phoneNo));
		Account managerPhoneNo = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		if (managerPhoneNo==null){
			return false;
		}
		return true;
	}
	
	public void showMyClub(Manager manager) {
		ClubDetailsModel.showDetails(manager.getClub());
	}
	
	
}