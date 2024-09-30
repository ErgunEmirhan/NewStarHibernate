package SoccerApp.entity.mainEntity;

import SoccerApp.entity.combinedEntity.Account;
import SoccerApp.entity.abstractEntity.Person;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(exclude = {"club"})
@Entity
@Table(name = "tblmanager")
public class Manager extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.PERSIST,mappedBy = "manager")
	private Club club;
	@Column(name = "contractenddate")
	private LocalDate contractEndDate;
	@OneToOne(cascade = CascadeType.MERGE)
	Account account;
	
	@Override
	public String toString() {
		return "Manager{" + "id=" + getId()  + ", contractEndDate=" + getContractEndDate() + ", account=" + getAccount().getUsername() + '}';
	}
}