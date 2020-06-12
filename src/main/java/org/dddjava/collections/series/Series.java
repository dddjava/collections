package org.dddjava.collections.series;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

//ToDo Comparator の指定と保存
//ToDo 仕様の過不足の検討

/**
 * 順序付きの集合 （重複なし）
 * @param <T>
 */
public class Series<T extends Comparable<T>> {
	SortedSet<T> elements;

	public Series(Collection<T> elements) {
		this.elements = new TreeSet<>(elements);
	}

	/**
	 * 要素の数
	 * @return
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * 空集合の検査
	 * @return
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * 他の集合を包含しているか検査
	 * @param other
	 * @return
	 */
	public boolean includes(Series<T> other) {
		Series<T> intersect = this.intersect(other);
		return intersect.equals(other);
	}

	/**
	 * 条件を満たす要素があるか検査
	 * @param predicate
	 * @return
	 */
	public boolean contains(Predicate<T> predicate) {
		return elements.stream().anyMatch(predicate);
	}

	/**
	 * 条件を満たす要素の数を検査
	 * @param predicate
	 * @return
	 */
	public int countIf(Predicate<T> predicate) {
		return ((int) elements.stream().filter(predicate).count());
	}

	/**
	 * 集合演算：積集合
	 * @param other
	 * @return
	 */
	public Series<T> intersect(Series<T> other) {
		Set<T> temporary = new TreeSet<>(elements);
		temporary.retainAll(other.elements);
		return new Series<>(temporary);
	}

	/**
	 * 条件に合う要素の集合を抽出
	 * @param predicate
	 * @return
	 */
	public Series<T> select(Predicate<T> predicate) {
		Set<T> temporary = elements.stream().filter(predicate).collect(Collectors.toSet());
		return new Series(temporary);
	}

	public Series reject(Predicate<T> predicate) {
		return new Series<>(elements.stream().filter(predicate.negate()).collect(Collectors.toSet()));
	}

	public T detect(Predicate<T> predicate) {
		return elements.stream()
			.filter(predicate).findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	public T detectOrDefault(Predicate<T> predicate, T defaultElement) {
		return elements.stream()
			.filter(predicate).findFirst()
			.orElse(defaultElement);
	}

	// 集約(畳み込み)

	public T reduce(T target, BinaryOperator<T> accumulator) {
		return elements.stream().reduce(target,accumulator);
	}

	// 変換
	// Functionを引数にして、異なる型で、同じ要素数のGroupを返す
	public <R extends Comparable<R>> Series<R> map(Function<T,R> function) {
		return new Series<>(elements.stream().map(function).collect(Collectors.toSet()));
	}

	//Objectメソッドのオーバーライド

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Series<?>)) return false;

		Series<T> other = (Series<T>) o;
		List<T> source = new ArrayList<>(elements);
		List<T> target = new ArrayList<>(other.elements);
		return source.equals(target);
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
