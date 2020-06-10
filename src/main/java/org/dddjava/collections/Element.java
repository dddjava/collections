package org.dddjava.collections;

import java.util.Objects;

/**
 * 集合の要素（ひな形）
 */
public class Element implements Comparable<Element> {
	String label;
	int value;

	public Element(String label, int value) {
		this.label = label;
		this.value = value;
	}

	@Override
	public int compareTo(Element other) {
		return Integer.compare(value, other.value);
	}

	@Override
	public boolean equals(Object other) {
		Element element = (Element) other;
		return value == element.value &&
				Objects.equals(label, element.label);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, value);
	}

	@Override
	public String toString() {
		return "Element{" +
				"label='" + label + '\'' +
				", value=" + value +
				'}';
	}
}
