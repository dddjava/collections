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
	Set<T> members;

	public Group(Collection<T> members) {
		this.members = new HashSet<>(members);
	}

	//検査
	public int size() {
		return members.size();
	}

	public boolean isEmpty() {
		return members.isEmpty();
	}

	@Deprecated
	public boolean includes(T member) {
		return members.contains(member);
	}

	public boolean includes(Group<T> other) {
		Group<T> intersect = this.intersect(other);
		return intersect.equals(other);
	}

	@Deprecated
	public boolean contains(Predicate<T> predicate) {
		return members.stream().anyMatch(predicate);
	}

	@Deprecated
	public int occurrencesOf(Predicate<T> predicate) {
		return ((int) members.stream().filter(predicate).count());
	}

	//集合演算

	public Group<T> union(Group<T> other) {
		Set<T> temporary = new HashSet<>(members);
		temporary.addAll(other.members);
		return new Group<>(temporary);
	}

	public Group<T> minus(Group<T> other) {
		Set<T> temporary = new HashSet<>(members);
		temporary.removeAll(other.members);
		return new Group<>(temporary);
	}

	public Group<T> intersect(Group<T> other) {
		Set<T> temporary = new HashSet<>(members);
		temporary.retainAll(other.members);
		return new Group<>(temporary);
	}

	//フィルタリングと検出
	@Deprecated
	public Group select(Predicate<T> predicate) {
		return new Group<>(members.stream().filter(predicate).collect(Collectors.toSet()));
	}

	@Deprecated
	public Group reject(Predicate<T> predicate) {
		return new Group<>(members.stream().filter(predicate.negate()).collect(Collectors.toSet()));
	}

	@Deprecated
	public T detect(Predicate<T> predicate) {
		return members.stream()
			.filter(predicate).findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	@Deprecated
	public T detectOrDefault(Predicate<T> predicate, T defaultElement) {
		return members.stream()
			.filter(predicate).findFirst()
			.orElse(defaultElement);
	}

	// 集約(畳み込み)

	@Deprecated
	public T reduce(T target, BinaryOperator<T> accumulator) {
		return members.stream().reduce(target,accumulator);
	}

	@Deprecated
	public T reduce(BinaryOperator<T> accumulator) {
		Optional<T> result = members.stream().reduce(accumulator);
		return result.orElseThrow(NoSuchElementException::new);
	}

	// 変換
	// Functionを引数にして、異なる型で、同じ要素数のGroupを返す
	@Deprecated
	public <R> Group<R> map(Function<T,R> function) {
		return new Group<>(members.stream().map(function).collect(Collectors.toSet()));
	}

	//ファクトリメソッド


	//お約束メソッド
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Group<?>) ) return false;

		Group<?> other = (Group<?>) o;
		return members.equals(other.members);
	}

	@Override
	public int hashCode() {
		return members.hashCode() ;
	}

	@Override
	public String toString() {
		return members.toString();
	}
}
