package SoccerApp.entity;


import SoccerApp.utility.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblplayer",
uniqueConstraints = @UniqueConstraint(columnNames = {"jerseynumber","club_id"}))
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
	@JoinColumn
	private Club club;
	@Column(name = "contractenddate")
	private LocalDate contractEndDate;
	
}