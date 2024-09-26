package SoccerApp.entity;

import SoccerApp.utility.enums.OfferStatus;
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
@Table(name = "tbltransfer")

public class Offer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	// TODO : transfer sezonları eklenecek.
	@ManyToOne
	Manager offeringManager;
	@ManyToOne
	Manager receivingManager;
	@ManyToOne
	Club buyerClub;
	@ManyToOne
	Club ownerClub;
	@ManyToOne
	Player player;
	
	Double transferFee;
	
	LocalDate offerDate;
	
	LocalDate transferDate;
	
	OfferStatus offerStatus;
}