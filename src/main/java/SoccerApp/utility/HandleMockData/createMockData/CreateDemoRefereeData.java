package SoccerApp.utility.HandleMockData.createMockData;

import SoccerApp.controller.RefereeController;
import SoccerApp.entity.mainEntity.Referee;
import SoccerApp.utility.enums.Kokart;
import SoccerApp.utility.enums.Nationality;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateDemoRefereeData {
	Random rnd=new Random();
	List<Referee> refereeList=new ArrayList<>();
	RefereeController refereeController=RefereeController.getInstance();
	public void createDemoRefereeData() {
		for (int i = 0; i < 100; i++) {
			String name="Referee"+i;
			String surname="Surname"+i;
			LocalDate birthDate=LocalDate.of(rnd.nextInt(1970,1990),rnd.nextInt(1,13), rnd.nextInt(1,28));
			String nationality= Nationality.values()[rnd.nextInt(Nationality.values().length)].toString();
			Integer salary= rnd.nextInt(10000,50000);
			Kokart kokart=giveKokart(i);
			Referee referee=Referee.builder().firstName(name).lastName(surname).birthDate(birthDate).nationality(Nationality.valueOf(nationality)).salary(salary).kokart(kokart).build();
			refereeList.add(referee);
		}
		refereeController.saveAll(refereeList);
	}
	public Kokart giveKokart(int i){
		return switch (i%4){
			case 1->Kokart.A_CLASS;
			case 2,3->Kokart.B_CLASS;
			case 0->Kokart.C_CLASS;
			default -> Kokart.C_CLASS;
		};
	}
}