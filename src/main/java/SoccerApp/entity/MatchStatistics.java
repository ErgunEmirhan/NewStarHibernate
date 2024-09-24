package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblmatchstatistics")
public class MatchStatistics extends BaseEntity{
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