package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(exclude = {"manager"})
@Entity
@Table(name = "tblclub")
public class Club extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "club")
	private Set<Player> squad;
	@Column(name = "foundationyear")
	private Integer foundationYear;
	
	@ManyToOne
	private Stadium stadium;
	
	@Column(name = "stadiumname")
	private String stadiumName;
	
	private String chairman;
	
	private Double budget;
	
	@Column(name = "wagebill")
	private Double wageBill;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Manager manager;
	
	@Override
	public String toString() {
		return "Club{" + "id=" + getId() + ", name='" + getName() + '\'' + ", foundationYear=" + getFoundationYear() + ", stadiumId=" + getStadium() + ", stadiumName='" + getStadiumName() + '\'' + ", chairman='" + getChairman() + '\'' + ", budget='" + getBudget() + '\'' + ", wageBill='" + getWageBill() + '\'' + ", state=" + getState() + '}';
	}
}