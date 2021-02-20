package project;

import java.util.Vector;

public class Set<T> {

	private Vector<T> list;
	private final int MIN_LIST = 2;

	public Set() {
		this.list = new Vector<T>(MIN_LIST);
	}

	public boolean push(T value) { // Add to list
		if (!(list.contains(value))) {
			list.add(value);
			return true;
		}
		return false;
	}

	public T pop(int index) { // return by index
		if (index < 0) {
			return null;
		}
		return list.get(index);
	}

	public T top() { // Get last object from the list
		return list.lastElement();
	}

	public int topIndex() {
		return list.size();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Set<?>)) {
			return false;
		}
		Set<?> temp = (Set<?>) other;
		return list.equals(temp.list);
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			buff.append(list.get(i).toString() + "\n");
		}
		return buff.toString();
	}

}
