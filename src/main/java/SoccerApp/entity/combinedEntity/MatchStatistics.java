package SoccerApp.entity.combinedEntity;

import SoccerApp.entity.abstractEntity.BaseEntity;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblmatchstatistics")
public class MatchStatistics extends BaseEntity {
	@Id
	@ManyToOne
	private Club club;
	@Id
	@ManyToOne
	private League league;
	private int winCount;
	private int drawCount;
	private int loseCount;
	private int goalsForCount;
	private int goalsAgainstCount;

}