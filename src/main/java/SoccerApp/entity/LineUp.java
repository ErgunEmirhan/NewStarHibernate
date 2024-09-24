package SoccerApp.entity;

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