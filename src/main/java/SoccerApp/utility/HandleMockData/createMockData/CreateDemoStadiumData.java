package SoccerApp.utility.HandleMockData.createMockData;

import SoccerApp.controller.StadiumController;
import SoccerApp.entity.mainEntity.Stadium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateDemoStadiumData {
	Random rnd=new Random();
	StadiumController stadiumController=StadiumController.getInstance();
	List<Stadium> stadiumList=new ArrayList<>();
	public void createDemoStadiumData() {
		for (int i = 0; i < 100; i++) {
			String name="Stadium"+i;
			Integer capacity= rnd.nextInt(35000,70000);
			Stadium stadium= Stadium.builder().name(name).capacity(capacity).build();
			stadiumList.add(stadium);
		}
		stadiumController.saveAll(stadiumList);
	}
}