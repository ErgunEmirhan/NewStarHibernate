package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblmanager")
public class Manager extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "manager")
	@JoinColumn(name = "clubid")
	private Club club;
	@Column(name = "contractenddate")
	private LocalDate contractEndDate;
	private String password;
	
	@Override
	public String toString() {
		return "Manager{" + "id=" + getId() + ", club=" + getClub().getName() + ", contractEndDate=" + getContractEndDate() + ", password='" + getPassword() + '\'' + '}';
	}
}