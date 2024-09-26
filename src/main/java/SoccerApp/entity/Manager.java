package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "tblmanager")
public class Manager extends Person{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.PERSIST,mappedBy = "manager")
	private Club club;
	@Column(name = "contractenddate")
	private LocalDate contractEndDate;
	@OneToOne(cascade = CascadeType.PERSIST)
	Account account;
	
}