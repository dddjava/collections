package org.dddjava.collections.group;

import java.util.*;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合（重複なし）
 * @param <T>
 */
public class Group<T> {
	Set<T> elements;

	Group(Set<T> elements) {
		this.elements = elements;
	}

	//検査

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public boolean includes(Group<T> other) {
		Group<T> intersect = this.intersect(other);
		return intersect.equals(other);
	}

	public boolean contains(Predicate<T> predicate) {
		return elements.stream().anyMatch(predicate);
	}

	public int countIf(Predicate<T> predicate) {
		return ((int) elements.stream().filter(predicate).count());
	}

	//集合演算

	public Group<T> union(Group<T> other) {
		Set<T> mutableSet = new HashSet<>(elements);
		mutableSet.addAll(other.elements);
		return new Group<>(mutableSet);
	}

	public Group<T> minus(Group<T> other) {
		Set<T> mutableSet = new HashSet<>(elements);
		mutableSet.removeAll(other.elements);
		return new Group<>(mutableSet);
	}

	public Group<T> intersect(Group<T> other) {
		Set<T> mutableSet = new HashSet<>(elements);
		mutableSet.retainAll(other.elements);
		return new Group<>(mutableSet);
	}

	// フィルタ

	@Deprecated
	public Group<T> select(Predicate<T> predicate) {
		return new Group<>(elements.stream().filter(predicate).collect(Collectors.toSet()));
	}

	public Group<T> reject(Predicate<T> predicate) {
		return new Group<>(elements.stream().filter(predicate.negate()).collect(Collectors.toSet()));
	}

	public Group<T> selectOne(Predicate<T> predicate) {
		T result = elements.stream()
				.filter(predicate).findFirst()
				.orElseThrow(NoSuchElementException::new);
		return new Group(Set.of(result));
	}

	public Group<T> selectOneOrDefault(Predicate<T> predicate, T defaultElement) {
		T result = elements.stream()
			.filter(predicate).findFirst()
			.orElse(defaultElement);
		return new Group(Set.of(result));
	}

	// 集約(畳み込み)

	@Deprecated
	public T reduce(T target, BinaryOperator<T> accumulator) {
		return elements.stream().reduce(target, accumulator);
	}

	@Deprecated
	public T reduce(BinaryOperator<T> accumulator) {
		Optional<T> result = elements.stream().reduce(accumulator);
		return result.orElseThrow(NoSuchElementException::new);
	}

	// 写像

	@Deprecated
	public <R> Group<R> map(Function<T,R> function) {
		return new Group<>(elements.stream().map(function).collect(Collectors.toSet()));
	}

	// 取り出し
	public T toElement() {
		Optional<T> result =  elements.stream().findFirst();
		return result.orElseThrow(IllegalStateException::new);
	}

	// Objectメソッド
	@Override
	public boolean equals(Object other) {
		if (! (other instanceof Group<?>) ) return false;

		Group<?> otherGroup = (Group<?>) other;
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
