package project.view;

import java.util.ArrayList;

import project.listeners.ViewListenable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class StackView extends StackPane {
	private ArrayList<ViewListenable> allListeners;
	private ShowCitizenListView citizetList;
	private AddCitizenView addCitizenView;
	private AddBallotBox addBallotView;
	private AddCandidateView addCandidateView;
	private AddPartyView addPartyView;
	private ElectionRoundView electionsRoundView;
	private ShowBallotBoxList ballotListView;
	private ShowPartyView partyListView;
	private ShowResults restulsView;
	private ShowElectionsDate electionsDate;

	public StackView(ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		setPadding(new Insets(10));
		citizetList = new ShowCitizenListView(this, allListeners);
		addCitizenView = new AddCitizenView(this, allListeners);
		addBallotView = new AddBallotBox(this, allListeners);
		addCandidateView = new AddCandidateView(this, allListeners);
		addPartyView = new AddPartyView(this, allListeners);
		electionsRoundView = new ElectionRoundView(this, allListeners);
		ballotListView = new ShowBallotBoxList(this, allListeners);
		partyListView = new ShowPartyView(this, allListeners);
		restulsView = new ShowResults(this, allListeners);
		electionsDate = new ShowElectionsDate(this, allListeners);
		
	}

	public ShowResults getRestulsView() {
		return restulsView;
	}

	public ShowCitizenListView getCitizetList() {
		return citizetList;
	}

	public AddCitizenView getAddCitizenView() {
		return addCitizenView;
	}

	public AddBallotBox getAddBallotView() {
		return addBallotView;
	}

	public void setVisibaleLastNode(Node lastNode) {
		lastNode.setVisible(false);
	}

	public AddCandidateView getAddCandidateView() {
		return addCandidateView;
	}

	public AddPartyView getAddPartyView() {
		return addPartyView;
	}

	public ElectionRoundView getElectionsRoundView() {
		return electionsRoundView;
	}

	public ShowBallotBoxList getBallotListView() {
		return ballotListView;
	}

	public ShowPartyView getPartyListView() {
		return partyListView;
	}

	public ArrayList<ViewListenable> getAllListeners() {
		return allListeners;
	}

	public ShowElectionsDate getElectionsDate() {
		return electionsDate;
	}


}
