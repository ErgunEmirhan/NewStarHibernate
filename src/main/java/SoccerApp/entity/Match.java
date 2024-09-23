package SoccerApp.entity;

import SoccerApp.utility.enums.MatchType;
import SoccerApp.utility.enums.WeatherCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblmatch")
@Check(name = "home_away_check", constraints = "home_id != away_id")
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
	@JoinTable(
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