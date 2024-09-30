package SoccerApp.entity.combinedEntity;

import SoccerApp.entity.abstractEntity.BaseEntity;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.Manager;
import SoccerApp.entity.mainEntity.Player;
import SoccerApp.utility.enums.ManagerOfferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblofferwithmanager")

public class OfferWithManager extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	// TODO : transfer sezonları eklenecek.
	@ManyToOne
	Manager buyerManager;
	@ManyToOne
	Manager ownerManager;
	@ManyToOne
	Club buyerClub;
	@ManyToOne
	Club ownerClub;
	@ManyToOne
	Player player;
	
	Double transferFee;
	
	LocalDate offerDate;
	
	@Enumerated(EnumType.STRING)
	ManagerOfferStatus offerStatus;
}