package SoccerApp.entity.mainEntity;

import SoccerApp.entity.abstractEntity.BaseEntity;
import SoccerApp.utility.enums.MatchStatus;
import SoccerApp.utility.enums.MatchType;
import SoccerApp.utility.enums.WeatherCondition;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.List;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stadiumid")
	private Stadium stadium;
	private final int time = 90;
	@Enumerated(EnumType.STRING)
	@Column(name = "weathercondition")
	private WeatherCondition weatherCondition;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
	joinColumns = {@JoinColumn(name = "refereeid")},
	inverseJoinColumns = {@JoinColumn(name = "matchid")})
	private Set<Referee> referees;
	@Enumerated(EnumType.STRING)
	@Column(name = "matchtype")
	private MatchType matchType;
	private LocalDateTime matchDate;
	@ManyToOne(fetch = FetchType.LAZY)
	private Club home;
	@ManyToOne(fetch = FetchType.LAZY)
	private Club away;
	@Column(name = "seasonweek")
	private Byte currentWeekofSeason;
	@ManyToOne
	private League league;
	@ManyToMany
	private List<Player> homeLineup;
	@ManyToMany
	private List<Player> awayLineup;
	@Builder.Default
	private int homeScore = 0;
	@Builder.Default
	private int awayScore = 0;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private MatchStatus status = MatchStatus.NOT_STARTED;
}