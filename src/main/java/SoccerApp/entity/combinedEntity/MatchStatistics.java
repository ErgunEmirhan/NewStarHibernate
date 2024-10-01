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
@EqualsAndHashCode(exclude = {"club", "league"})
@Entity
@Table(name = "tblmatchstatistics")
public class MatchStatistics extends BaseEntity {
	@Id
	@ManyToOne
	private Club club;
	@Id
	@ManyToOne
	private League league;
	@Builder.Default
	private int winCount = 0;
	@Builder.Default
	private int drawCount=0;
	@Builder.Default
	private int loseCount=0;
	@Builder.Default
	private int goalsForCount=0;
	@Builder.Default
	private int goalsAgainstCount=0;
	
	@Override
	public String toString() {
		return "MatchStatistics{" + "club=" + getClub().getName() +
				", league=" + getLeague().getName() + ", winCount=" +
				getWinCount() + ", drawCount=" + getDrawCount() + ", loseCount=" +
				getLoseCount() + ", goalsForCount=" + getGoalsForCount() +
				", goalsAgainstCount=" + getGoalsAgainstCount() + '}';
	}
}