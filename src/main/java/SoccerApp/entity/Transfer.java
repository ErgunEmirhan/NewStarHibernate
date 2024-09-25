package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

public class Transfer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	// TODO : transfer sezonları eklenecek.
	@ManyToOne
	Manager offeringManager;
	@ManyToOne
	Manager receivingManager;
	@ManyToOne
	Club offeringClub;
	@ManyToOne
	Club receivingClub;
	@ManyToOne
	Player offeringPlayer;
	
	Double transferFee;
	
	LocalDate offerDate;
	
	LocalDate transferDate;
	
	
	
}