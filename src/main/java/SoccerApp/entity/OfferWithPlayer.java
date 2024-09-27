package SoccerApp.entity;

import SoccerApp.utility.enums.PlayerOfferStatus;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblofferwithplayer")
public class OfferWithPlayer extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private OfferWithManager offerWithManager;
	private Double offeredSalary;
	private LocalDate contractEndDate;
	@Enumerated(EnumType.STRING)
	private PlayerOfferStatus status;
	private LocalDate offerDate;
	private LocalDate transferDate;
}