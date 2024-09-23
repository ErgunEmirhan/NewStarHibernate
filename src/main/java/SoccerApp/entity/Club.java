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
@Table(name = "tblclub")
public class Club extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,mappedBy = "club")
	private Set<Player> playerList;
	@Column(name = "foundationyear")
	private Integer foundationYear;
	
	@Column(name = "stadiumid")
	private Long stadiumId;
	
	@Column(name = "stadiumname")
	private String stadiumName;
	
	private String chairman;
	
	private String budget;
	
	@Column(name = "wagebill")
	private String wageBill;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Manager manager;
	
	@Override
	public String toString() {
		return "Club{" + "id=" + getId() + ", name='" + getName() + '\'' + ", foundationYear=" + getFoundationYear() + ", stadiumId=" + getStadiumId() + ", stadiumName='" + getStadiumName() + '\'' + ", chairman='" + getChairman() + '\'' + ", budget='" + getBudget() + '\'' + ", wageBill='" + getWageBill() + '\'' + ", manager=" + getManager() + ", state=" + getState() + '}';
	}
}