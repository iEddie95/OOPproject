package project.controller;

import java.time.LocalDate;

import project.BallotBox;
import project.Candidateable;
import project.Citizen;
import project.CoronaSick;
import project.Party;
import project.RegularCitizen;
import project.Soldier;
import project.SoldierCoronaSick;
import project.listeners.ElectionsListenable;
import project.listeners.ViewListenable;
import project.model.Elections;
import project.view.ElectionsViewable;

public class Controller implements ElectionsListenable, ViewListenable {
	private Elections elections;
	private ElectionsViewable view;

	public Controller(Elections elections, ElectionsViewable view) {
		this.elections = elections;
		this.view = view;

		view.registerListener(this);
		elections.registerListener(this);

	}

	@Override
	public void viewAddedCitizen(Citizen citizen) throws Exception {
		elections.addCitizenToList(citizen);

	}

	@Override
	public void viewAddedRegularBallotBox(BallotBox<RegularCitizen> ballotBox) {
		elections.addRegularBallotBox((BallotBox<RegularCitizen>) ballotBox);

	}

	@Override
	public void viewAddedCoronaSickBallotBox(BallotBox<CoronaSick> ballotBox) {
		elections.addCoronaSickBallotBox(ballotBox);

	}

	@Override
	public void viewAddedSoldierBallotBox(BallotBox<Soldier> ballotBox) {
		elections.addSoldierBallotBox(ballotBox);

	}

	@Override
	public void viewAddedSoldierCoronaSickBallotBox(BallotBox<SoldierCoronaSick> ballotBox) {
		elections.addSoldierCoronaBallotBox(ballotBox);
	}

	@Override
	public void viewAddedParty(Party party) {
		elections.addPartyToList(party);

	}

	@Override
	public void addedCitizenToElections(Citizen citizen) {
		if (citizen != null) {
			view.showAddedCitizen(citizen);

		} else {
			view.showCitizenException("ID already exict");
		}
	}

	@Override
	public void addedPartyToElections(Party party) {
		view.showAddedParty(party.getName());

	}

	@Override
	public void addedBallotBoxToElections(BallotBox<?> ballotBox) {
		view.showAddedBallotBox(ballotBox);
	}

	@Override
	public void viewUpdatedElectionsDate(String day, String month, String year) {
		int newDay = Integer.parseInt(day);
		int newMonth = Integer.parseInt(month);
		int newYear = Integer.parseInt(year);
		elections.setElectionsDay(newDay, newMonth, newYear);

	}

	@Override
	public String viewAskForElectionsDate() {
		return elections.dateToString();

	}

	@Override
	public String viewAskForBallotBoxList() {

		return elections.printBallotBoxList();
	}

	@Override
	public void viewAddedCandidate(Citizen citizen, int party) throws Exception {
		if (elections.addCitizenToList(citizen)) {
			elections.addCandidateToParty(party, (Candidateable) citizen);
		}
	}

	@Override
	public LocalDate viewAskForElectionsLocalDate() {

		return elections.getDate();
	}

	@Override
	public String viewAskForCitizenList() {

		return elections.printCitizensList();
	}

	@Override
	public String viewAskForPartyList() {

		return elections.printPartyList();
	}

	@Override
	public void viewAskPreapareForElections() {
		elections.prepareBallotBox();
	}

	@Override
	public int viewAskForCitizensCounter() {
		return elections.getCitizensCounter();
	}

	@Override
	public Citizen viewAskForCitizen(int index) {
		if (index < elections.getCitizensList().topIndex()) {
			return elections.getCitizensList().pop(index);
		}
		return null;
	}

	@Override
	public String viewAskForPartiesForElections() {
		return elections.printPartiesForElections();
	}

	@Override
	public void viewSendVoteSelections(int citizenIndex, int index) {
		Citizen temp = elections.getCitizensList().pop(citizenIndex);
		temp.getMyBallotBox().setResult(index);
	}

	@Override
	public void viewUpdateElectionsDone(Boolean isDone) {
		elections.sortResultsMap();
		elections.setDone(isDone);

	}

	@Override
	public String viewAskForPartyName(int index) {
		return elections.getPartyList().get(index).getName();
	}

	@Override
	public int viewAskForPartyCounter() {
		return elections.getPartyList().size();
	}

	@Override
	public boolean viewAskIfElctionsDone() {
		return elections.isDone();
	}

	@Override
	public void viewUpdateElectionsDate(int year, int month, int day) {
		elections.setElectionsDay(day, month, year);

	}

	@Override
	public String viewAskForElectionsBallotBoxResults() {
		return elections.printBallotBoxResults();
	}

	@Override
	public String viewAskForElectionsPartyResults() {
		return elections.printPartyResults();
	}

	@Override
	public int viewAskForBallotBoxCounter() {
		return elections.getBallotBoxCounter();
	}

	@Override
	public void addedCandidateToElections(Candidateable candidate) {
		view.showAddedCandidate((Citizen) candidate);

	}

	@Override
	public void updatedElectionsAreDone(boolean isDone) {
		view.showElectionsAreDone();

	}

	@Override
	public void updatedElectionsDate(LocalDate date) {
		view.showUpdateElectionsDate(date);

	}

	@Override
	public void addedPartyFailToElections() {
		view.addPartyFailToUI();

	}

}
