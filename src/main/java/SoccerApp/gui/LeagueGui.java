package SoccerApp.gui;

import SoccerApp.controller.ClubController;
import SoccerApp.controller.LeagueController;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.combinedEntity.League;
import SoccerApp.model.ClubsListedModel;
import SoccerApp.model.LeagueModel;
import SoccerApp.utility.InputHandler;
import SoccerApp.utility.enums.Division;
import SoccerApp.utility.enums.Region;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeagueGui {
	private static LeagueGui instance;
	private LeagueController leagueController;
	private ClubController clubController;
	private List<League> listedLeagues;
	private Scanner scanner=new Scanner(System.in);
	public static LeagueGui getInstance() {
		if (instance == null) {
			instance = new LeagueGui();
		}
		return instance;
	}
	
	private LeagueGui() {
		leagueController= LeagueController.getInstance();
		clubController=ClubController.getInstance();
	}
	
	public int leagueGuiMainMenu() {
		int choice;
		do {
			System.out.println("""
					                    1. Olustur Lig
					                    2. Kulup Ekle Lige
					                    3. Goruntule Ligdeki Katilimci Kulupler
					                    4. Fikstur Olustur
					                    5. Goruntule Fikstur
					                    6. Goruntule Kulup Fikstur
					                    7. Goruntule Puan Tablosu
					                    0. Geri Don
					                   -1. Kapa programi
					                    """);
			choice = InputHandler.integerInput();
			if (choice==0){
				listedLeagues.clear();
				break;
			}
			choice = leagueGuiMainMenuOptions(choice);
		} while (choice != -1);
		return choice;
	}
	
	public int leagueGuiMainMenuOptions(int choice) {
		switch (choice) {
			case 1:
				createLeague();
				break;
			case 2:
				addClubToLeague();
				break;
			case 3:
				showParticipantClubsInTheLeague();
				break;
			case 4:
				createFixture();
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case -1:
				break;
			default:
				System.out.println("Girdi gecersiz x_x");
		}
		return choice;
	}
	
	private void createFixture() {
		Optional<League> optLeague = findLeague();
		if (optLeague.isEmpty()){
			System.out.println("No such league");
			return;
		}
		
		League league = optLeague.get();
		if(league.getClubs().size() < league.getTeamCount()){
			System.out.println("Not enough participant clubs");
			return;
		}
		
		
	}
	
	private Optional<League> findLeague() {
		showLeagues();
		int leagueIndex = InputHandler.integerInput("For which league do you want to create fixture> ");
		try{
			League league = listedLeagues.get(leagueIndex);
			return Optional.of(league);
		}
		catch (Exception e){
			System.out.println("Girdi gecersiz x_x" + e.getMessage());
			return Optional.empty();
		}
	}
	
	private void showLeagues() {
		if (listedLeagues.isEmpty()) {
			List<League> legos = leagueController.findAll();
			listedLeagues = legos;
		}
		LeagueModel.showLeagues(listedLeagues);
	}
	
	private void showParticipantClubsInTheLeague() {
		Optional<League> optLeague = findLeague();
		if (optLeague.isPresent()){
			League league = optLeague.get();
			Set<Club> clubList = league.getClubs();
			ClubsListedModel.listClubs(clubList.stream().toList());
		}
	}
	
	
	
	
	private void addClubToLeague(){
		Optional<League> optLeague = findLeague();
		League league;
		if (optLeague.isPresent()){
			league = optLeague.get();
		}
		else {
			System.out.println("League does not exist. Please create a league first");
			return;
		}
		List<Club> allClubs = clubController.findAll();
		ClubsListedModel.listClubs(allClubs);
		boolean isFull=league.getClubs().size()>= league.getTeamCount();
		if (!isFull){
			int clubIndex;
			do{
				clubIndex= InputHandler.integerInput("Enter the index of the club you want to add to the league >");
				try {
					Club club = allClubs.get(clubIndex - 1);
					if (league.getClubs().contains(club)) {
						league.getClubs().add(club);
					}
					else {
						System.out.println("The club is already in the league");
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}while (clubIndex!=0&&(!isFull));
		}
	}
	
	
	private void createLeague() {
		System.out.print("Please enter a league name : ");
		String leagueName = scanner.nextLine();
		System.out.print("Please enter a league season : ");
		String leagueSeason = scanner.nextLine();
		System.out.print("Please choose a league region");
		Region[] regions = Region.values();
		System.out.println(Arrays.toString(regions));
		String leagueRegion = scanner.nextLine();
		System.out.print("Please enter a league division");
		Division[] divisions = Division.values();
		System.out.println(Arrays.toString(divisions));
		String leagueDivision= scanner.nextLine();
		System.out.print("Please enter a max number of teams");
		byte leagueMaxTeamNumber = scanner.nextByte();
		System.out.print("Please enter a start date");
		LocalDate leagueStartDate = LocalDate.parse(scanner.nextLine());
		System.out.print("Please enter a end date");
		LocalDate leagueEndDate = LocalDate.parse(scanner.nextLine());
		League league=
				League.builder().name(leagueName).season(leagueSeason).region(Region.valueOf(leagueRegion)).division(Division.valueOf(leagueDivision)).teamCount(leagueMaxTeamNumber).startDate(leagueStartDate).endDate(leagueEndDate).build();
	}
}