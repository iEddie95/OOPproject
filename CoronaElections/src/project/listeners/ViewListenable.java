package project.listeners;

import java.time.LocalDate;

import project.BallotBox;
import project.Citizen;
import project.CoronaSick;
import project.Party;
import project.RegularCitizen;
import project.Soldier;
import project.SoldierCoronaSick;

public interface ViewListenable {
	void viewUpdatedElectionsDate(String day, String month, String year);
	void viewAddedCitizen(Citizen citizen) throws Exception;
	void viewAddedRegularBallotBox(BallotBox<RegularCitizen> ballotBox);
	void viewAddedCoronaSickBallotBox(BallotBox<CoronaSick> ballotBox);
	void viewAddedSoldierBallotBox(BallotBox<Soldier> ballotBox);
	void viewAddedSoldierCoronaSickBallotBox(BallotBox<SoldierCoronaSick> ballotBox);
	void viewAddedCandidate(Citizen citizen, int party) throws Exception;
	void viewAddedParty(Party party);
	void viewAskPreapareForElections();
	int viewAskForCitizensCounter();
	int viewAskForBallotBoxCounter();
	Citizen viewAskForCitizen(int index);
	void viewSendVoteSelections(int citizenIndex,int index);
	void viewUpdateElectionsDone(Boolean isDone);
	LocalDate viewAskForElectionsLocalDate();
	String viewAskForBallotBoxList();
	String viewAskForCitizenList();
	String viewAskForPartyList();
	String viewAskForPartiesForElections();
	String viewAskForPartyName(int index);
	int viewAskForPartyCounter();
	boolean viewAskIfElctionsDone();
	void viewUpdateElectionsDate(int year, int month, int day);
	String viewAskForElectionsBallotBoxResults();
	String viewAskForElectionsPartyResults();

}
