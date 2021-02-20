package project;

import java.util.Comparator;

public class ResultsComparator implements Comparator<Party> {

	@Override
	public int compare(Party o1, Party o2) {
		if (o1.getResults() >= o2.getResults())
			return -1;
		if (o1.getResults() <= o2.getResults())
			return 1;
		return 0;
	}

}
