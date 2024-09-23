package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbllineup")
public class LineUp extends BaseEntity{
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