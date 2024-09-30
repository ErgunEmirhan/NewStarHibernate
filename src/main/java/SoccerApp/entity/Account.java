package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblaccount")
public class Account{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
}