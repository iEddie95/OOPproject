package project.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import project.BallotBox;
import project.BallotBoxIDComparator;
import project.Candidateable;
import project.Citizen;
import project.CitizenType;
import project.CoronaSick;
import project.Party;
import project.RegularCitizen;
import project.ResultsComparator;
import project.Set;
import project.Soldier;
import project.SoldierCoronaSick;
import project.listeners.ElectionsListenable;

public class Elections {

	private final int MIN = 2;
	private Set<Citizen> citizensList;
	private Vector<BallotBox<RegularCitizen>> regularBallotBoxList;
	private Vector<BallotBox<Soldier>> soldiersBallotBoxList;
	private Vector<BallotBox<CoronaSick>> coronaSickBallotBoxList;
	private Vector<BallotBox<SoldierCoronaSick>> sickSoldiersBallotBoxList;
	private Vector<BallotBox<?>> allBoxes;
	private Vector<Party> partyList;
	private Map<Party, Integer> results;
	private ArrayList<ElectionsListenable> allListeners;
	private int ballotBoxCounter = 0;
	private LocalDate date;
	private boolean isDone;

	public Elections(int year, int month, int day) {
		this.date = LocalDate.of(year, month, day);
		this.citizensList = new Set<Citizen>();
		this.regularBallotBoxList = new Vector<BallotBox<RegularCitizen>>(MIN);
		this.soldiersBallotBoxList = new Vector<BallotBox<Soldier>>(MIN);
		this.coronaSickBallotBoxList = new Vector<BallotBox<CoronaSick>>(MIN);
		this.sickSoldiersBallotBoxList = new Vector<BallotBox<SoldierCoronaSick>>(MIN);
		this.partyList = new Vector<Party>(MIN);
		this.results = new TreeMap<Party, Integer>(new ResultsComparator());
		this.allListeners = new ArrayList<ElectionsListenable>();
		this.isDone = false;
	}

	public void registerListener(ElectionsListenable l) {
		allListeners.add(l);
	}

	public void setElectionsDay(int day, int month, int year) {
		this.date = LocalDate.of(year, month, day);
		fireElectionsDate(getDate());
	}

	private void fireElectionsDate(LocalDate date) {
		for (ElectionsListenable l : allListeners) {
			l.updatedElectionsDate(date);
		}

	}

	public Vector<Party> getPartyList() {
		return partyList;
	}

	public LocalDate getDate() {
		return date;
	}

	public Set<Citizen> getCitizensList() {
		return citizensList;
	}

	public Vector<BallotBox<?>> getAllBoxes() {
		return allBoxes;
	}

	public String dateToString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(format);
	}

	public int getBallotBoxCounter() {
		return ballotBoxCounter;
	}

	public int getCitizensCounter() {
		return citizensList.topIndex();
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
		fireElectionsAreDone(isDone);
	}

	private void fireElectionsAreDone(boolean isDone) {

		for (ElectionsListenable l : allListeners) {
			l.updatedElectionsAreDone(isDone);
		}
	}

	public Vector<BallotBox<CoronaSick>> getCoronaSickBallotBoxList() {
		return coronaSickBallotBoxList;
	}

	public Vector<BallotBox<RegularCitizen>> getRegularBallotBoxList() {
		return regularBallotBoxList;
	}

	public Vector<BallotBox<Soldier>> getSoldierBallotBoxList() {
		return soldiersBallotBoxList;
	}

	public Vector<BallotBox<SoldierCoronaSick>> getSickSoldierskBallotBoxList() {
		return sickSoldiersBallotBoxList;
	}

	public boolean addRegularBallotBox(BallotBox<RegularCitizen> ballotBox) {
		if (regularBallotBoxList.contains(ballotBox))
			return false;
		else {
			this.regularBallotBoxList.add(ballotBox);
			ballotBox.setEllectionDay(date);
			ballotBoxCounter++;
			fireAddedBallotBox(ballotBox);
			return true;
		}
	}

	private <T extends Citizen> void fireAddedBallotBox(BallotBox<T> ballotBox) {
		for (ElectionsListenable l : allListeners) {
			l.addedBallotBoxToElections(ballotBox);
		}
	}

	public boolean addSoldierBallotBox(BallotBox<Soldier> ballotBox) {
		if (soldiersBallotBoxList.contains(ballotBox))
			return false;
		else {
			this.soldiersBallotBoxList.add(ballotBox);
			ballotBox.setEllectionDay(date);
			ballotBoxCounter++;
			fireAddedBallotBox(ballotBox);
			return true;
		}
	}

	public boolean addSoldierCoronaBallotBox(BallotBox<SoldierCoronaSick> ballotBox) {
		if (sickSoldiersBallotBoxList.contains(ballotBox))
			return false;
		else {
			this.sickSoldiersBallotBoxList.add(ballotBox);
			ballotBox.setEllectionDay(date);
			ballotBoxCounter++;
			fireAddedBallotBox(ballotBox);
			return true;
		}
	}

	public boolean addCoronaSickBallotBox(BallotBox<CoronaSick> ballotBox) {
		if (coronaSickBallotBoxList.contains(ballotBox))
			return false;
		else {
			this.coronaSickBallotBoxList.add(ballotBox);
			ballotBox.setEllectionDay(date);
			ballotBoxCounter++;
			fireAddedBallotBox(ballotBox);
			return true;
		}
	}

	public boolean addCitizenToList(Citizen citizen) throws Exception {
		if (date.getYear() - citizen.getBirthDate().getYear() < 18) {
			throw new Exception("Canno't add citizen, age must be at least 18, please try again");
		}

		if (citizen instanceof Candidateable && (date.getYear() - citizen.getBirthDate().getYear() < 21)) {
			throw new Exception("Candidate can't be under 21 years old, please try again");
		}

		else {
			if (this.citizensList.push(citizen)) {
				setBallotBoxForNewCitizen(citizen);
				if (citizen instanceof Candidateable)
					return true;
				else
					fireAddedCitizen(citizen);
				return true;
			}
		}
		fireAddedCitizen(null);
		return false;
	}

	private void fireAddedCitizen(Citizen citizen) {
		for (ElectionsListenable l : allListeners) {
			l.addedCitizenToElections(citizen);
		}
	}

	private void fireAddedandidate(Candidateable candidate) {
		for (ElectionsListenable l : allListeners) {
			l.addedCandidateToElections(candidate);
		}

	}

	public boolean addPartyToList(Party party) {
		if (!(partyList.contains(party))) {
			partyList.add(party);
			fireAddedParty(party);
			return true;
		}
		firePartyAlreadyAdded();
		return false;
	}


	private void fireAddedParty(Party party) {
		for (ElectionsListenable l : allListeners) {
			l.addedPartyToElections(party);
		}
	}
	private void firePartyAlreadyAdded() {
		for (ElectionsListenable l : allListeners) {
			l.addedPartyFailToElections();
		}		
	}

	public boolean addCandidateToParty(int party, Candidateable candidate) {
		if (partyList.contains(partyList.get(party))) {
			partyList.get(party).addToParty(candidate);
			fireAddedandidate(candidate);
			return true;
		}
		return false;
	}

	public boolean setBallotBoxForNewCitizen(Citizen citizen) {
		if (citizen instanceof SoldierCoronaSick) {
			if (addSickSoldierToBox((SoldierCoronaSick) citizen)) {
				return true;
			}

		} else if (citizen instanceof Soldier) {
			if (addSoldierToBox((Soldier) citizen)) {
				return true;
			}
		}

		else if (citizen instanceof CoronaSick) {
			if (addCoronaSickToBox((CoronaSick) citizen)) {
				return true;
			}
		}

		else if (citizen instanceof RegularCitizen) {
			if (addRegularCitizenToBox((RegularCitizen) citizen)) {
				return true;
			}
		}
		createIfNoBallotBox(citizen); // if no ballot box in the same city, create new ballot box and add citizen
		return true;
	}

	private boolean addSickSoldierToBox(SoldierCoronaSick citizen) {
		for (BallotBox<SoldierCoronaSick> ballotBox : sickSoldiersBallotBoxList) {
			if (ballotBox.getAddress().equalsIgnoreCase(citizen.getCity())) {
				ballotBox.add(citizen);
				return true;
			}
		}
		return false;
	}

	private boolean addSoldierToBox(Soldier citizen) {
		for (BallotBox<Soldier> ballotBox : soldiersBallotBoxList) {
			if (ballotBox.getAddress().equalsIgnoreCase(citizen.getCity())) {
				ballotBox.add((Soldier) citizen);
				return true;
			}
		}
		return false;
	}

	private boolean addCoronaSickToBox(CoronaSick citizen) {
		for (BallotBox<CoronaSick> ballotBox : coronaSickBallotBoxList) {
			if (ballotBox.getAddress().equalsIgnoreCase(citizen.getCity())) {
				ballotBox.add((CoronaSick) citizen);
				return true;
			}
		}
		return false;
	}

	private boolean addRegularCitizenToBox(RegularCitizen citizen) {
		for (BallotBox<RegularCitizen> ballotBox : regularBallotBoxList) {
			if (ballotBox.getAddress().equalsIgnoreCase(citizen.getCity())) {
				ballotBox.add(citizen);
				return true;
			}
		}
		return false;
	}

	public <T extends Citizen> void createIfNoBallotBox(T citizen) { // Creates new ballot box for new citizen
		if (citizen instanceof SoldierCoronaSick) {
			BallotBox<SoldierCoronaSick> temp = new BallotBox<SoldierCoronaSick>(citizen.getCity(),
					CitizenType.Sick_Soldiers);
			addSoldierCoronaBallotBox(temp);
			temp.add((SoldierCoronaSick) citizen);
		}

		else if (citizen instanceof Soldier) {
			BallotBox<Soldier> temp = new BallotBox<Soldier>(citizen.getCity(), CitizenType.Soldiers);
			addSoldierBallotBox(temp);
			temp.add((Soldier) citizen);
		}

		else if (citizen instanceof CoronaSick) {
			BallotBox<CoronaSick> temp = new BallotBox<CoronaSick>(citizen.getCity(), CitizenType.Corona_Sick);
			addCoronaSickBallotBox(temp);
			temp.add((CoronaSick) citizen);
		}

		else {
			BallotBox<RegularCitizen> temp = new BallotBox<RegularCitizen>(citizen.getCity(), CitizenType.Regular);
			addRegularBallotBox(temp);
			temp.add((RegularCitizen) citizen);
		}
	}

	private Vector<BallotBox<? extends Citizen>> mergeAllBoxes() { // merge ballot boxes before elections
		this.allBoxes = new Vector<BallotBox<? extends Citizen>>();
		allBoxes.addAll(regularBallotBoxList);
		allBoxes.addAll(soldiersBallotBoxList);
		allBoxes.addAll(coronaSickBallotBoxList);
		allBoxes.addAll(sickSoldiersBallotBoxList);
		allBoxes.sort(new BallotBoxIDComparator());
		return allBoxes;
	}

	public void prepareBallotBox() { // sets size of results vector in each ballot box
		mergeAllBoxes();
		for (BallotBox<?> ballotBox : getAllBoxes()) {
			ballotBox.startElection(partyList.size());
		}
	}

	public void calculteResultsForParty(Party party, int index) {
		int results = 0;
		for (int i = 0; i < getBallotBoxCounter(); i++) {
			results = allBoxes.get(i).getResult(index);
			party.setResults(results);
		}
	}

	public void sortResultsMap() {
		for (int i = 0; i < partyList.size(); i++) {
			calculteResultsForParty(partyList.get(i), i);
			results.put(partyList.get(i), partyList.get(i).getResults());
		}
	}

	public String printBallotBoxList() { // print before merge
		StringBuffer buff = new StringBuffer();
		buff.append("Ballot Boxes List:\n" + "There are " + getBallotBoxCounter() + " Ballot Boxes:\n");
		for (BallotBox<RegularCitizen> box : regularBallotBoxList) {
			buff.append(box.toString() + "\n");
		}
		for (BallotBox<CoronaSick> box : coronaSickBallotBoxList) {
			buff.append(box.toString() + "\n");
		}
		for (BallotBox<Soldier> box : soldiersBallotBoxList) {
			buff.append(box.toString() + "\n");
		}
		for (BallotBox<SoldierCoronaSick> box : sickSoldiersBallotBoxList) {
			buff.append(box.toString() + "\n");
		}
		return buff.toString();
	}

	public String printBallotBoxIDs() { // print after merge
		StringBuffer buff = new StringBuffer();
		buff.append("Ballot Boxes List:\n");
		for (BallotBox<?> ballotBox : allBoxes) {
			buff.append(ballotBox.toString() + "\n");
		}
		return buff.toString();
	}

	public String printCitizensList() {
		StringBuffer buff = new StringBuffer();
		buff.append("Citizens List:\nThere are " + citizensList.topIndex() + " Citizens\n");
		for (int i = 0; i < citizensList.topIndex(); i++) {
			buff.append((i + 1) + ") " + citizensList.pop(i).toString() + "\n");
		}
		return buff.toString();
	}

	public String printPartyList() {
		StringBuffer buff = new StringBuffer();
		buff.append("There are " + partyList.size() + " Parties\n");
		for (int i = 0; i < partyList.size(); i++) {
			buff.append((i + 1) + ") " + partyList.get(i).toString() + "\n");
		}
		return buff.toString();
	}

	public String printPartiesForElections() {
		String str = "The participating parties are:\n";
		for (int i = 0; i < partyList.size(); i++) {
			str += i + ": " + partyList.get(i).getName() + "\n";
		}
		return str;
	}

	public String printBallotBoxResults() {
		StringBuffer buff = new StringBuffer("Total Votes from Ballot Boxes:\n");
		for (int i = 0; i < ballotBoxCounter; i++) {
			buff.append("The results in " + allBoxes.get(i).getBoxType() + " "
					+ allBoxes.get(i).toStringWithoutCitizens() + " :\n");
			for (int j = 0; j < partyList.size(); j++) {
				buff.append(partyList.get(j).getName() + ": " + allBoxes.get(i).getResult(j) + " votes\n");
			}
			buff.append("\n");
		}
		return buff.toString();
	}

	public String printPartyResults() {
		StringBuffer buff = new StringBuffer();
		buff.append("Total votes per party are: \n");
		for (Map.Entry<Party, Integer> partyResults : results.entrySet()) {
			buff.append(partyResults.getKey().getName() + ": " + partyResults.getValue() + " votes\n");
		}
		return buff.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Elections)) {
			return false;
		}
		Elections e = (Elections) other;
		return date.isEqual(e.getDate());
	}

	@Override
	public String toString() {
		return "Ellection day is: " + dateToString() + "\n" + printBallotBoxList() + "\n" + printCitizensList() + "\n"
				+ printPartyList();
	}
}
