package org.dddjava.collections;

public class SomethingComparable implements Comparable<SomethingComparable> {
	String value ;

	public SomethingComparable(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(SomethingComparable other) {
		return value.compareTo(other.value);
	}

	@Override
	public boolean equals(Object o) {

		if (!(o instanceof SomethingComparable )) return false;

		SomethingComparable other = (SomethingComparable) o;

		return value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
