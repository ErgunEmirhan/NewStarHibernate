package SoccerApp.gui;

import SoccerApp.controller.ClubController;
import SoccerApp.controller.LeagueController;
import SoccerApp.controller.MatchController;
import SoccerApp.controller.MatchStatisticsController;
import SoccerApp.dto.request.MatchByClubAndLeagueDtoRequest;
import SoccerApp.entity.combinedEntity.MatchStatistics;
import SoccerApp.entity.mainEntity.Club;
import SoccerApp.entity.mainEntity.League;
import SoccerApp.entity.mainEntity.Match;
import SoccerApp.model.ClubsListedModel;
import SoccerApp.model.FixtureModel;
import SoccerApp.model.LeagueModel;
import SoccerApp.model.StandingsModel;
import SoccerApp.utility.InputHandler;
import SoccerApp.utility.MatchArrangeAlgoritm;
import SoccerApp.utility.enums.Division;
import SoccerApp.utility.enums.Region;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LeagueGui {
	private static LeagueGui instance;
	private LeagueController leagueController;
	private ClubController clubController;
	private List<League> listedLeagues = new ArrayList<>();
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
				showFixture();
				break;
			case 6:
				showClubFixture();
				break;
			case 7:
				showStandings();
				break;
			case -1:
				break;
			default:
				System.out.println("Girdi gecersiz x_x");
		}
		return choice;
	}
	
	private void createMatchStatistics(League league) {
		MatchStatisticsController.getInstance().createMatchStatistics(league);
	}
	
	private void showStandings() {
		Optional<League> optLeague = findLeague();
		if (optLeague.isEmpty()){
			System.out.println("No such league");
			return;
		}
		var standings = MatchStatisticsController
				.getInstance().findStandings(optLeague.get());
		StandingsModel.showStandings(standings);
	}
	
	private void showClubFixture() {
		Optional<League> optLeague = findLeague();
		League league = null;
		List<Club> clubList = null;
		if (optLeague.isPresent()){
			league = optLeague.get();
			clubList = league.getClubs();
			if (clubList.isEmpty()){
				System.out.println("No clubs in the league yet :(");
				return;
			}
			else ClubsListedModel.listClubs(clubList.stream().toList());
		}
		else return;
		
		int clubIndex = InputHandler.integerInput("Choose a club");
		try{
			Club club = clubList.get(clubIndex - 1);
			MatchByClubAndLeagueDtoRequest request =
					new MatchByClubAndLeagueDtoRequest(club, league);
			List<Match> clubFixture = MatchController.getInstance()
                                        .findMatchByClubAndLeague(request);
			FixtureModel.showFixture(clubFixture);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void showFixture() {
		Optional<League> optLeague = findLeague();
		if (optLeague.isEmpty()){
			System.out.println("No such league");
			return;
		}
		FixtureModel.showFixture(optLeague.get().getFixture());
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
		
		arrangeMatches(league);
		if(league.getFixture().isEmpty()) {
			System.out.println("No fixture >:(");
			return;
		}
		
		System.out.println("Successfully created fixture");
		MatchController.getInstance().saveAll(league.getFixture());
		createMatchStatistics(league);
	}
	
	private void arrangeMatches(League league) {
		MatchArrangeAlgoritm.matchArrangeAlgorithm(league);
		
	}
	
	private Optional<League> findLeague() {
		showLeagues();
		if (listedLeagues.isEmpty()) return Optional.empty();
		int leagueIndex = InputHandler.integerInput("Choose a league> ");
		try{
			League league = listedLeagues.get(leagueIndex - 1);
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
	
	private List<Club> showParticipantClubsInTheLeague() {
		Optional<League> optLeague = findLeague();
		if (optLeague.isPresent()){
			League league = optLeague.get();
			List<Club> clubList = league.getClubs();
			if (clubList.isEmpty()){
				System.out.println("No clubs in the league yet :(");
			}
			else ClubsListedModel.listClubs(clubList.stream().toList());
			
			return clubList;
		}
		else return new ArrayList<>();
	}
	
	
	
	
	private void addClubToLeague(){
		Optional<League> optLeague = findLeague();
		League league;
		if (optLeague.isPresent()){
			league = optLeague.get();
		}
		else {
			System.out
					.println("League does not exist. Please create a league first");
			return;
		}
		List<Club> allClubs = clubController.findAll();
		ClubsListedModel.listClubs(allClubs);
		boolean isFull=league.getClubs().size()>= league.getTeamCount();
		if (isFull) {
			System.out.println("League is already full");
			return;
		}
		
		int clubIndex;
		do{
			clubIndex= InputHandler
					.integerInput("Enter the index of the club you want to add to the league >");
			try {
				Club club = allClubs.get(clubIndex - 1);
				if (!league.getClubs().contains(club)) {
					league.getClubs().add(club);
				}
				else {
					System.out.println("The club is already in the league");
				}
			}
			catch (Exception e) {
				System.out
						.println("Something went wrong adding clubs to the league..." + e.getMessage());
			}
			isFull=league.getClubs().size()>= league.getTeamCount();
		}while (clubIndex!=0&&(!isFull));
		leagueController.update(league);
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
		byte leagueMaxTeamNumber = scanner.nextByte(); scanner.nextLine();
		LocalDate leagueStartDate = InputHandler.localDateInput("Please enter a start date");
		LocalDate leagueEndDate = InputHandler.localDateInput("Please enter a end date");
		League league=
				League.builder().name(leagueName)
				      .season(leagueSeason).region(Region.valueOf(leagueRegion))
				      .division(Division.valueOf(leagueDivision))
				      .teamCount(leagueMaxTeamNumber).startDate(leagueStartDate)
				      .endDate(leagueEndDate).build();
		leagueController.save(league);
	}
}