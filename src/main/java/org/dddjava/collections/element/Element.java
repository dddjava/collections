package org.dddjava.collections.element;

import java.util.Objects;

/**
 * 集合の要素（実装例）
 */
public class Element implements Comparable<Element> , Numerical {
	String label;
	long value;

	public Element(String label, long value) {
		this.label = label;
		this.value = value;
	}

	@Override
	public int intValue() {
		return Math.toIntExact(value);
	}

	@Override
	public int compareTo(Element other) {
		return Long.compare(value, other.value);
	}

	@Override
	public boolean equals(Object other) {
		Element element = (Element) other; // Element以外の型との比較はキャスト例外を送出する
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
