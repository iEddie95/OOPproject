package project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Party {
	private final int MIN_CANDIDATES = 2;
	private String name;
	private PoliticalViews politicalView;
	private LocalDate dateOfCreation;
	private Vector<Candidateable> partyCandidates;
	private int results = 0;

	public Party(String name, String politicalView, int year, int month, int day) {
		this.name = name;
		this.dateOfCreation = LocalDate.of(year, month, day);
		this.politicalView = PoliticalViews.valueOf(politicalView);
		this.partyCandidates = new Vector<Candidateable>(MIN_CANDIDATES);
	}

	public String getName() {
		return name;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results += results;
	}

	public boolean addToParty(Candidateable candidate) {
		if (!(partyCandidates.contains(candidate))) {
			this.partyCandidates.add(candidate);
			candidate.setParty(this);
			return true;

		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		buff.append("The Party: " + name + ", is a: " + politicalView + ", created on: "
				+ dateOfCreation.format(format).toString() + ", has: " + partyCandidates.size() + " Candidates.\nCandidate List:\n");
		for (int i = 0; i < partyCandidates.size(); i++) {
			buff.append(partyCandidates.get(i).toString() + "\n");
		}
		return buff.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Party)) {
			return false;
		}
		Party temp = (Party) other;
		return (temp.getName().equals(name));

	}
}
