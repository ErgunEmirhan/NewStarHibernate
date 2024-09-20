package SoccerApp.entity;

import SoccerApp.entities.Takim;
import SoccerApp.utility.enumeng.MatchType;
import SoccerApp.utility.enums.EMusabakaTuru;
import SoccerApp.utility.enumeng.WeatherCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblmatch")
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "stadiumid")
	private Stadium stadium;
	private int time = 90;
	@Enumerated(EnumType.STRING)
	@Column(name = "weathercondition")
	private WeatherCondition weatherCondition;
	@ManyToMany(targetEntity = Referee.class, cascade = CascadeType.ALL)
	@JoinTable(name = "referees",
	joinColumns = {@JoinColumn(name = "refereeid")},
	inverseJoinColumns = {@JoinColumn(name = "matchid")})
	private Set<Referee> referees;
	@Enumerated(EnumType.STRING)
	@Column(name = "matchtype")
	private MatchType matchType;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime matchDate;
	private Takim evSahibi;
	private Takim deplasman;
}