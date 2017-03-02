package org.dddjava.collections;

public class SomethingUnComparable {
	String value ;

	public SomethingUnComparable(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
