package SoccerApp.entity;

import SoccerApp.utilities.enums.Nationality;
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
@MappedSuperclass
public abstract class Person {
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "birthDate")
	private LocalDate birthDate;
	@Enumerated(EnumType.STRING)
	private Nationality nationality;
	private Integer salary;
}