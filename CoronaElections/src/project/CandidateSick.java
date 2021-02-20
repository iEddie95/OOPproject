package project;

public class CandidateSick extends CoronaSick implements Candidateable {
	private boolean hasParty;
	private Party myParty;

	public CandidateSick(String name, int year, int month, int day, int id, String city, int sickDays)
			throws Exception {
		super(name, year, month, day, id, city, sickDays);
	}

	public boolean setParty(Party party) {
		if (myParty == null) {
			this.myParty = party;
			this.hasParty = true;
			return true;
		}
		return false;

	}

	public Party getParty() {
		return myParty;
	}

	public boolean haveParty() {
		return hasParty;
	}

	@Override
	public String toString() {
		return super.toString() + "\nCandidate at: " + myParty.getName();
	}
}
