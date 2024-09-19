package SoccerApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblclub")
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "foundationyear")
	private Integer foundationYear;
	@Column(name = "stadiumid")
	private Long stadiumId;
	@Column(name = "stadiumname")
	private String stadiumName;
	@Builder.Default
	private boolean varMiMenajer = false;
	private String chairman;
	private String budget;
	@Column(name = "wagebill")
	private String wageBill;
}