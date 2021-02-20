package project.view;

import java.time.LocalDate;

import project.BallotBox;
import project.Citizen;
import project.listeners.ViewListenable;

public interface ElectionsViewable {
	void registerListener(ViewListenable l);
	void addPartyFailToUI();
	void showBallotBoxList();
	void showCitizenList();
	void showPartyList();
	void showAddedBallotBox(BallotBox<?> ballotBox);
	void showAddedCitizen(Citizen citizen);
	void showAddedParty(String partyName);
	void showCitizenException(String msg);
	void showStartElctions();
	void showUpdateElectionsDate(LocalDate date);
	void showElectionsResults();
	void showAddedCandidate(Citizen candidate);
	void showElectionsAreDone();


}
