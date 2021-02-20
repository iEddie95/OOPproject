//	Eddie Rudoy 313533341 and Odeia Busheri 206059156

package project;

import java.util.Scanner;

import project.model.Elections;

public class Program {

	public static void showMenu() {
		System.out.println("-------------------------------");
		System.out.println("Choose from menu\n" + "1- Add Ballot Box\n" + "2- Add Citizen\n" + "3- Add Party\n"
				+ "4- Add Candidate\n" + "5- Show all Ballot boxes\n" + "6- Show all Citizens\n"
				+ "7- Show all parties\n" + "8- Start the elections\n" + "9- Show results\n" + "10- EXIT");
		System.out.println("-------------------------------");
	}

	public static void startElections(Scanner scan, Elections elections) throws Exception {
		System.out.println("Election day is " + elections.dateToString());
		boolean fcontinue = false;
		while (fcontinue != true) {
			showMenu();
			System.out.print("YOUR CHOISE: ");
			int choice = Integer.parseInt(scan.nextLine());
			System.out.println();
			if ((elections.isDone()) && (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 8)) {
				System.out.println("ELECTIONS ARE OVER, can't preferom this action");
			} else {
				switch (choice) {
				case 1: { // Add new Ballot Box
					createBallotBox(scan, elections);
					break;
				}
				case 2: { // Add new Citizen- auto add to ballot box or auto create new ballot box
					createCitizen(scan, elections);
					break;
				}
				case 3: { // Create new party
					createParty(scan, elections);
					break;
				}
				case 4: { // Create new candidate
					createCandidate(scan, elections);
					break;
				}
				case 5: { // Print all BallotBoxes including the citizens
					System.out.println(elections.printBallotBoxList());
					break;
				}
				case 6: { // Print all citizens
					System.out.println(elections.printCitizensList());
					break;
				}
				case 7: { // Print all parties including candidates
					System.out.println(elections.printPartyList());
					break;
				}
				case 8: { // start voting
					startElectionsRound(scan, elections);
					break;
				}
				case 9: { // print results-per ballot box and total
					printResults(elections);
					break;
				}
				case 10: { // EXIT
					fcontinue = true;
					System.out.println(
							"THANK YOU FOR THE ELECTIONS, SEE YOU IN 3 MONTHS, MAY THE ODDS BE EVER IN YOUR FAVOR!");
					break;
				}
				default: {
					System.out.println("Invalid choice");
					break;
				}
				}
			}
		}
	}

	public static void createBallotBox(Scanner scan, Elections elections) {
		System.out.println("Creating new Ballot Box:");
		System.out.println("In which city you want to create ballot box?");
		System.out.println("Enter city: ");
		String city = scan.nextLine();
		System.out.println("Whic kind of Ballot box you want to create?\n" + "1- Regular\n"
				+ "2- For Corona sick citizens\n" + "3- For soldiers\n" + "4- For Corona sick soldiers");
		int choice = Integer.parseInt(scan.nextLine());
		switch (choice) {
		case 1: {
			elections.addRegularBallotBox(new BallotBox<RegularCitizen>(city, CitizenType.Regular));
			break;
		}
		case 2: {
			elections.addCoronaSickBallotBox(new BallotBox<CoronaSick>(city, CitizenType.Corona_Sick));
			break;
		}
		case 3: {
			elections.addSoldierBallotBox(new BallotBox<Soldier>(city, CitizenType.Soldiers));
			break;
		}
		case 4: {
			elections.addSoldierCoronaBallotBox(new BallotBox<SoldierCoronaSick>(city, CitizenType.Sick_Soldiers));
			break;
		}
		default:
			System.out.println("invaild choice, start over\n");
			break;
		}
		System.out.println("Ballot Box was created sucesfuly.");
	}

	public static void createCitizen(Scanner scan, Elections elections) throws Exception {
		try {
			Citizen temp = null;
			System.out.println("Adding new citizen to system: ");
			System.out.println("Enter name:");
			String name = scan.nextLine();
			System.out.println("Enter birthday date 'DD MM YYYY' (sapce seperated): ");
			int birthDay = Integer.parseInt(scan.next());
			int birthMonth = Integer.parseInt(scan.next());
			int birthYear = Integer.parseInt(scan.next());
			scan.nextLine();
			System.out.println("Enter ID:");
			int id = Integer.parseInt(scan.nextLine());
			System.out.println("Enter city: ");
			String city = scan.nextLine();
			System.out.println("Is in quarantie?(Enter 1 or 2)" + "\n1: No\n2: Yes");
			int choise = Integer.parseInt(scan.nextLine());
			switch (choise) {
			case 1: {
				if (elections.getDate().getYear() - birthYear <= 21)
					temp = new Soldier(name, birthYear, birthMonth, birthDay, id, city);
				else {
					temp = new RegularCitizen(name, birthYear, birthMonth, birthDay, id, city);
				}
				break;
			}
			case 2: {
				System.out.println("Please enter days of being sick:");
				int sickDays = Integer.parseInt(scan.nextLine());
				if (elections.getDate().getYear() - birthYear <= 21) {
					temp = new SoldierCoronaSick(name, birthYear, birthMonth, birthDay, id, city, sickDays);
				} else {
					temp = new CoronaSick(name, birthYear, birthMonth, birthDay, id, city, sickDays);
				}
				break;
			}
			default:
				break;
			}

			if (elections.addCitizenToList(temp)) {
				System.out.println("Citizen was created succesfully :)");
			} else
				System.out.println("Failed to add citizen-ID already exict");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createCandidate(Scanner scan, Elections elections) throws Exception {
		try {
			Citizen temp = null;
			System.out.println("Enter name:");
			String name = scan.nextLine();
			System.out.println("Enter birthday date 'DD MM YYYY' (sapce seperated): ");
			int birthDay = Integer.parseInt(scan.next());
			int birthMonth = Integer.parseInt(scan.next());
			int birthYear = Integer.parseInt(scan.next());
			scan.nextLine();
			System.out.println("Enter ID:");
			int id = Integer.parseInt(scan.nextLine());
			System.out.println("Enter city: ");
			String city = scan.nextLine();
			System.out.println("Is in quarantie?(Enter 1 or 2)" + "\n1: No\n2: Yes");
			int choise = Integer.parseInt(scan.nextLine());
			switch (choise) {
			case 1: {
				temp = new Candidate(name, birthYear, birthMonth, birthDay, id, city);
				break;
			}
			case 2: {
				System.out.println("Please enter days of being sick:");
				int sickDays = Integer.parseInt(scan.nextLine());
				temp = new CandidateSick(name, birthYear, birthMonth, birthDay, id, city, sickDays);
				break;
			}
			default:
				break;
			}

			if (elections.addCitizenToList(temp)) {
				System.out.println("Enter party: ");
				System.out.print(elections.printPartiesForElections());
				int party = Integer.parseInt(scan.nextLine());
				elections.addCandidateToParty(party, (Candidateable) temp);
				System.out.println("Candidate was created succesfully :)");
			} else
				System.out.println("Failed to add candidate-ID already exict");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createParty(Scanner scan, Elections elections) {
		Party temp = null;
		System.out.println("Adding new party to system: ");
		System.out.println("Enter name:");
		String name = scan.nextLine();
		System.out.println("Choose political View: ");
		PoliticalViews[] views = PoliticalViews.values();
		for (int i = 0; i < views.length; i++) {
			System.out.println(i + 1 + ": " + views[i].name());
		}
		int partyWing = Integer.parseInt(scan.nextLine());
		System.out.println("Enter party creation date 'DD MM YYYY' (sapce seperated): ");
		int day = Integer.parseInt(scan.next());
		int month = Integer.parseInt(scan.next());
		int year = Integer.parseInt(scan.next());
		scan.nextLine();
		switch (partyWing) {
		case 1: {
			temp = new Party(name, PoliticalViews.Left.toString(), year, month, day);
			break;
		}
		case 2: {
			temp = new Party(name, PoliticalViews.Middle.toString(), year, month, day);
			break;
		}
		case 3: {
			temp = new Party(name, PoliticalViews.Right.toString(), year, month, day);
			break;
		}
		default:
			break;
		}

		if (!(elections.addPartyToList(temp))) {
			System.out.println("This party is already exits");
		} else
			System.out.println("Party was created succesfully :)");
	}

	public static void startElectionsRound(Scanner scan, Elections elections) {
		elections.prepareBallotBox();
		System.out.println("The time has come, to vote for your life! Don't fail it up!\n"
				+ "My citizens, start your engines and may the best candidate win!\n");
		for (int i = 0; i < elections.getCitizensCounter(); i++) {
			Citizen temp = elections.getCitizensList().pop(i);
			System.out.println(temp.getName() + " would you like to vote? ");
			System.out.println("1: Yes\n2: No");
			int answer = Integer.parseInt(scan.nextLine());
			if (answer == 1) {
				if (temp instanceof CoronaSickable) {
					if (!((CoronaSickable) temp).isWearingProtection()) {
						System.out.println("Voter is Corona sick and not wearing protection, can't vote");
						continue;
					}
				}
				System.out.print(elections.printPartiesForElections());
				int choice = Integer.parseInt(scan.nextLine());
				temp.getMyBallotBox().setResult(choice);
			} else {
				System.out.println("Voter " + temp.getName() + " decided not to vote\n");
			}
		}
		elections.sortResultsMap();
		elections.setDone(true);
	}

	public static void printResults(Elections elections) {
		if (elections.isDone()) {
			System.out.println(elections.printBallotBoxResults());
			System.out.println(elections.printPartyResults());
		} else {
			System.out.println("Elections are not done yet, can't print results");
		}
	}

	public static void hardCodedData(Elections elections) throws Exception {
		elections.addRegularBallotBox(new BallotBox<RegularCitizen>("Jerusalem", CitizenType.Regular));
		elections.addCoronaSickBallotBox(new BallotBox<CoronaSick>("Rehovot", CitizenType.Corona_Sick));
		elections.addSoldierCoronaBallotBox(new BallotBox<SoldierCoronaSick>("Haifa", CitizenType.Sick_Soldiers));
		elections.addSoldierBallotBox(new BallotBox<Soldier>("Rishon LeZion", CitizenType.Soldiers));

		Party p1 = new Party("Fight CVOID19", "Left", 1990, 2, 12);
		Party p2 = new Party("Fight 5G", "Right", 1990, 2, 22);
		Party p3 = new Party("Peace and Love", "Left", 2000, 1, 11);
		elections.addPartyToList(p1);
		elections.addPartyToList(p2);
		elections.addPartyToList(p3);

		CandidateSick c1 = new CandidateSick("momo", 1990, 2, 14, 123456789, "Rehovot", 90);
		elections.addCitizenToList(c1);
		p1.addToParty(c1);
		Candidate c2 = new Candidate("gogo", 1991, 5, 15, 234567891, "Jerusalem");
		elections.addCitizenToList(c2);
		p1.addToParty(c2);
		Candidate c3 = new Candidate("fofo", 1980, 5, 17, 345678912, "Jerusalem");
		elections.addCitizenToList(c3);
		p2.addToParty(c3);
		CandidateSick c4 = new CandidateSick("toto", 1955, 3, 17, 456789123, "Rehovot", 22);
		elections.addCitizenToList(c4);
		p2.addToParty(c4);
		Candidate c5 = new Candidate("jojo", 1987, 3, 17, 567891234, "Jerusalem");
		elections.addCitizenToList(c5);
		p3.addToParty(c5);
		Candidate c6 = new Candidate("kuku", 1948, 4, 2, 678912345, "Jerusalem");
		elections.addCitizenToList(c6);
		p3.addToParty(c6);

		elections.addCitizenToList(new CoronaSick("Dani", 1997, 5, 14, 912345678, "Rehovot", 100));
		elections.addCitizenToList(new CoronaSick("Sara", 1997, 5, 14, 876543210, "Rehovot", 29));
		elections.addCitizenToList(new Soldier("Beni", 2000, 5, 14, 789123456, "Rishon LeZion"));
		elections.addCitizenToList(new Soldier("Dana", 2001, 5, 14, 123456780, "Rishon LeZion"));
		elections.addCitizenToList(new SoldierCoronaSick("Moshe", 2000, 5, 14, 987654321, "Haifa", 30));
	}

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("Let's preapre for the elections\nPlease enter Election day date: DD MM yyyy");
		int day = Integer.parseInt(scan.next());
		int month = Integer.parseInt(scan.next());
		int year = Integer.parseInt(scan.next());
		scan.nextLine();
		Elections elections = new Elections(year, month, day);

		hardCodedData(elections);

		startElections(scan, elections);

		scan.close();
	}
}
