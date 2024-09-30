package SoccerApp.utility.HandleMockData;

import SoccerApp.controller.ManagerController;
import SoccerApp.entity.Manager;
import SoccerApp.utility.enums.Nationality;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateDemoManagerData {
	Random rnd=new Random();
	List<Manager> managerList=new ArrayList<>();
	ManagerController managerController=ManagerController.getInstance();
	public void createDemoManagerData() {
		for (int i = 0; i < 100; i++) {
			String name="Manager"+i;
			String surname="Surname"+i;
			LocalDate birthDate=LocalDate.of(rnd.nextInt(1955,1985), rnd.nextInt(1,13), rnd.nextInt(1,28));
			String nationality= Nationality.values()[rnd.nextInt(Nationality.values().length)].toString();
			Integer salary=rnd.nextInt(10000,100000);
			LocalDate contractEndDate=LocalDate.of(rnd.nextInt(2024,2030), rnd.nextInt(1,13), rnd.nextInt(1,28));
			Manager manager=Manager.builder().firstName(name).lastName(surname).birthDate(birthDate).nationality(Nationality.valueOf(nationality)).salary(salary).contractEndDate(contractEndDate).build();
			managerList.add(manager);
		}
		managerController.saveAll(managerList);
	}
}