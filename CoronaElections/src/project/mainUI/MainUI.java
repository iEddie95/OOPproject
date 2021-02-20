package project.mainUI;

import project.BallotBox;
import project.Candidate;
import project.CandidateSick;
import project.CitizenType;
import project.CoronaSick;
import project.Party;
import project.RegularCitizen;
import project.Soldier;
import project.SoldierCoronaSick;
import project.controller.Controller;
import project.model.Elections;
import project.view.ViewElections;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainUI extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Elections elections = new Elections(2020, 6, 20);

		ViewElections view = new ViewElections(primaryStage);
		
		Controller controller = new Controller(elections, view);

		hardCodedData(elections);

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
		elections.addCandidateToParty(0, c1);

		Candidate c2 = new Candidate("gogo", 1991, 5, 15, 234567891, "Jerusalem");
		elections.addCitizenToList(c2);
		elections.addCandidateToParty(0, c2);

		Candidate c3 = new Candidate("fofo", 1980, 5, 17, 345678912, "Jerusalem");
		elections.addCitizenToList(c3);
		elections.addCandidateToParty(1, c3);

		CandidateSick c4 = new CandidateSick("toto", 1955, 3, 17, 456789123, "Rehovot", 22);
		elections.addCitizenToList(c4);
		elections.addCandidateToParty(1, c4);

		Candidate c5 = new Candidate("jojo", 1987, 3, 17, 567891234, "Jerusalem");
		elections.addCitizenToList(c5);
		elections.addCandidateToParty(2, c5);

		Candidate c6 = new Candidate("kuku", 1948, 4, 2, 678912345, "Jerusalem");
		elections.addCitizenToList(c6);
		elections.addCandidateToParty(2, c6);

		elections.addCitizenToList(new CoronaSick("Dani", 1997, 5, 14, 912345678, "Rehovot", 100));
		elections.addCitizenToList(new CoronaSick("Sara", 1997, 5, 14, 876543210, "Rehovot", 29));
		elections.addCitizenToList(new Soldier("Beni", 2000, 5, 14, 789123456, "Rishon LeZion"));
		elections.addCitizenToList(new Soldier("Dana", 2001, 5, 14, 123456780, "Rishon LeZion"));
		elections.addCitizenToList(new SoldierCoronaSick("Moshe", 2000, 5, 14, 987654321, "Haifa", 30));
	}
}
