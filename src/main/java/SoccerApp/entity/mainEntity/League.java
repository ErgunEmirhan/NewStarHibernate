package SoccerApp.entity.mainEntity;

import SoccerApp.entity.abstractEntity.BaseEntity;
import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.utility.enums.Division;
import SoccerApp.utility.enums.Region;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.collection.spi.PersistentSet;

import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "tblleague")
public class League extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Club> clubs;
	private String season;
	@Enumerated(EnumType.STRING)
	private Region region;
	@Enumerated(EnumType.STRING)
	private Division division;
	@Column(name = "teamcount")
	private Byte teamCount;
	@Column(name = "startdate")
	private LocalDate startDate;
	@Column(name = "enddate")
	private LocalDate endDate;
	@OneToMany(mappedBy = "league", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Match> fixture;
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy = "league")
	private List<MatchStatistics> standings;
	
}