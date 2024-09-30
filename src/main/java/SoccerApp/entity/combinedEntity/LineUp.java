package SoccerApp.entity.combinedEntity;

import SoccerApp.entity.abstractEntity.BaseEntity;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.entity.mainEntity.Player;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "tbllineup")
public class LineUp extends BaseEntity {
	@Id
	@ManyToOne
	private Club club;
	@Id
	@OneToOne
	private Match match;
	@ManyToMany
	@JoinTable(inverseJoinColumns = {@JoinColumn(name = "playerid")})
	private Set<Player> lineUp;
	private Integer skor;
	
}