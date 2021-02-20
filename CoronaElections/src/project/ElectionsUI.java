package project;

import java.util.Vector;

public interface ElectionsUI {
	void showMessage(String msg);
	void addCitizenToUI(Citizen citizen);
	void addBallotBoxToUI(BallotBox<?> BallotBox);
	void addPartyToUI(Party party);
	String showList(Vector<?> list);

}
