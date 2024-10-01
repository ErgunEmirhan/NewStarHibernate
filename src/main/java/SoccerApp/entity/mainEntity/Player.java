package SoccerApp.entity.mainEntity;


import SoccerApp.entity.abstractEntity.Person;
import SoccerApp.utility.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(exclude = {"club"})
@Entity
@Table(name = "tblplayer")
public class Player extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "jerseynumber")
	private int jerseyNumber;
	@Column(name = "transferfee")
	private Double transferFee;
	@Enumerated(EnumType.STRING)
	private Position position;
	private int rating;
	@ManyToOne
	@JoinColumn(nullable = true)
	private Club club;
	@Column(name = "contractenddate")
	private LocalDate contractEndDate;
	
}