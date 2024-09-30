package SoccerApp.entity.mainEntity;

import SoccerApp.entity.abstractEntity.BaseEntity;
import SoccerApp.utility.enums.MatchType;
import SoccerApp.utility.enums.WeatherCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblmatch")
@Check(name = "home_away_check", constraints = "home_id != away_id")
public class Match extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "stadiumid")
	private Stadium stadium;
	private final int time = 90;
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
	private LocalDateTime matchDate;
	@ManyToOne
	private Club home;
	@ManyToOne
	private Club away;
	@Column(name = "seasonweek")
	private Byte currentWeekofSeason;
	@ManyToOne
	private League league;
}