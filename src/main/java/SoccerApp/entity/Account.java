package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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