package project.listeners;

import java.time.LocalDate;

import project.BallotBox;
import project.Candidateable;
import project.Citizen;
import project.Party;

public interface ElectionsListenable {
	void addedCitizenToElections(Citizen citizen);
	void addedBallotBoxToElections(BallotBox<?> ballotBox);
	void addedCandidateToElections(Candidateable candidate);
	void addedPartyToElections(Party party);
	void addedPartyFailToElections();
	void updatedElectionsAreDone(boolean isDone);
	void updatedElectionsDate(LocalDate date);
	String viewAskForElectionsDate();
}
