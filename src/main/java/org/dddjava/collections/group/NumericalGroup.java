package org.dddjava.collections.group;

import org.dddjava.collections.element.Numerical;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 数値の集合 合計・平均・最大・最小
 * @param <T>
 */
public class NumericalGroup<T extends Numerical> {
	Set<T> elements;

	NumericalGroup(Set<T> elements) {
		this.elements = elements;
	}

	/**
	 * 合計
	 * @return
	 */
	public int sum() {
		return intStream().sum();
	}

	/**
	 * 平均（小数点以下を四捨五入）
	 * @return
	 */
	public int average() {
		double result = intStream().average().orElseThrow();
		long longResult = Math.round(result);
		return Math.toIntExact(longResult);
	}

	/**
	 * 最大値
	 * @return
	 */
	public int max() {
		return intStream().max().orElseThrow();
	}

	/**
	 * 最小値
	 * @return
	 */
	public int min() {
		return intStream().min().orElseThrow();
	}

	private IntStream intStream() {
		return elements.stream().mapToInt(element -> element.intValue());
	}

	// Objectメソッドのオーバーライド

	@Override
	public boolean equals(Object other) {
		if (! (other instanceof NumericalGroup<?>) ) return false;

		NumericalGroup<?> otherGroup = (NumericalGroup<?>) other;
		return elements.equals(otherGroup.elements);
	}

	@Override
	public int hashCode() {
		return elements.hashCode() ;
	}

	@Override
	public String toString() {
		return elements.toString();
	}
}
