package org.dddjava.collections;

/**
 * 集合の要素（ひな形）
 */
public class ElementUncomparable {
	String label ;
	int value;

	public ElementUncomparable(String label, int value) {
		this.label = label;
		this.value = value;
	}

	@Override
	public String toString() {
		return "ElementUmcomparable{" +
				"label='" + label + '\'' +
				", value=" + value +
				'}';
	}
}
