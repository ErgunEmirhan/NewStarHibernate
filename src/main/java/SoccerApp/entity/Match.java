package SoccerApp.entity;

import SoccerApp.entities.Takim;
import SoccerApp.utilities.enums.MatchType;
import SoccerApp.utilities.enums.WeatherCondition;
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
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "referees",
	joinColumns = {@JoinColumn(name = "refereeid")},
	inverseJoinColumns = {@JoinColumn(name = "matchid")})
	private Set<Referee> referees;
	@Enumerated(EnumType.STRING)
	@Column(name = "matchtype")
	private MatchType matchType;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime matchDate;
	@ManyToOne
	private Club home;
	@ManyToOne
	private Club away;
	@Column(name = "seasonweek")
	private Byte currentWeekofSeason;
}