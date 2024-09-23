package SoccerApp.entity;

import SoccerApp.utility.enums.Division;
import SoccerApp.utility.enums.Region;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblleague")
public class League extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@Column(name = "clubidlist")
	private Set<Club> clubIDList;
	private String season;
	@Enumerated(EnumType.STRING)
	private Region region;
	@Enumerated(EnumType.STRING)
	private Division division;
	@Column(name = "leaguemaxteam")
	private Byte MAX_LEAGUE_TEAM_NUMBER;
	@Column(name = "startdate")
	private LocalDate startDate;
	@Column(name = "enddate")
	private LocalDate endDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn
	private Set<Match> fixture;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,mappedBy = "league")
	private Set<MatchStatistics> standings;
}