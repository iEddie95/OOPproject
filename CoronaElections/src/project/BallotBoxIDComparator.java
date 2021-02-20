package project;

import java.util.Comparator;

public class BallotBoxIDComparator implements Comparator<BallotBox<?>> {

	@Override
	public int compare(BallotBox<?> o1, BallotBox<?> o2) {
		if (o1.getId() < o2.getId())
			return -1;
		if (o1.getId() > o2.getId())
			return 1;
		return 0;
	}

}
