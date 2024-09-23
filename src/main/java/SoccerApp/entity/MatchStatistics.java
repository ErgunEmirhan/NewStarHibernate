package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblmatchstatistics")


public class MatchStatistics {
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