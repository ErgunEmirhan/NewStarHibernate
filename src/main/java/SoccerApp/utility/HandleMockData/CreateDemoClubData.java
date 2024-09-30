package SoccerApp.utility.HandleMockData;

import SoccerApp.controller.ClubController;
import SoccerApp.entity.Club;
import SoccerApp.entity.Stadium;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateDemoClubData {
	List<Club> clubList=new ArrayList<>();
	Random rnd=new Random();
	ClubController clubController=ClubController.getInstance();
	public void createDemoClubData() {
		for (int i = 0; i < 100; i++) {
			String name="Club"+i;
			Integer foundationYear = rnd.nextInt(1900,1950);
			String chairman="Chairman"+i;
			Double budget=rnd.nextDouble(10000,10000000);
			Double wageBill=rnd.nextDouble(10000,100000);
			Club club=Club.builder().name(name).foundationYear(foundationYear).chairman(chairman).budget(budget).wageBill(wageBill).build();
			clubList.add(club);
		}
		clubController.saveAll(clubList);
	}
}