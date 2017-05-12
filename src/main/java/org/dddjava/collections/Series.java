package org.dddjava.collections;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

//ToDo Comparator の指定と保存
//ToDo 仕様の過不足の検討

public class Series<T> {
	SortedSet<T> members;

	public Series(Collection<T> members) {
		this.members = new TreeSet<>(members);
	}

	public Series(Collection<T> members,Comparator<T> comparator) {
		TreeSet<T> temporary = new TreeSet<T>(comparator);
		temporary.addAll(members);
		this.members = new TreeSet<>(temporary);
	}


	//検査
	public int size() {
		return members.size();
	}

	public boolean isEmpty() {
		return members.isEmpty();
	}

	public boolean includes(T member) {
		return members.contains(member);
	}

	public boolean includes(Series<T> other) {
		Series<T> intersect = this.intersect(other);
		return intersect.equals(other);
	}

	public boolean contains(Predicate<T> predicate) {
		return members.stream().anyMatch(predicate);
	}

	public int occurrencesOf(Predicate<T> predicate) {
		return ((int) members.stream().filter(predicate).count());
	}


	//集合演算 （暫定的にプライベート）
	public Series<T> intersect(Series<T> other) {
		Set<T> temporary = new TreeSet<>(members);
		temporary.retainAll(other.members);
		return new Series<>(temporary);
	}

	//追加と削除

	public Series<T> add(T member) {
		SortedSet<T> temporary = new TreeSet<>(members);
		temporary.add(member);
		return new Series<>(temporary);
	}

	public Series<T> remove(T member) {
		SortedSet<T> temporary = new TreeSet<>(members);
		temporary.remove(member);
		return new Series<>(temporary);
	}

	//フィルタリングと検出

	public Series select(Predicate<T> predicate) {
		Set<T> temporary = members.stream().filter(predicate).collect(Collectors.toSet());
		return new Series(temporary, members.comparator());
	}

	public Series reject(Predicate<T> predicate) {
		return new Series<>(members.stream().filter(predicate.negate()).collect(Collectors.toSet()));
	}

	public T detect(Predicate<T> predicate) {
		return members.stream()
			.filter(predicate).findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	public T detectOrDefault(Predicate<T> predicate, T defaultElement) {
		return members.stream()
			.filter(predicate).findFirst()
			.orElse(defaultElement);
	}

	// 集約(畳み込み)

	public T reduce(T target, BinaryOperator<T> accumulator) {
		return members.stream().reduce(target,accumulator);
	}

	// 変換
	// Functionを引数にして、異なる型で、同じ要素数のGroupを返す
	public <R> Series<R> map(Function<T,R> function) {
		return new Series<>(members.stream().map(function).collect(Collectors.toSet()));
	}

	//ファクトリメソッド

	public static <T> Series<T> of(T... args){
		return new Series<>(Arrays.asList(args));
	}

	public static <T> Series<T> of(Comparator<T> comparator,T ... args){
		return new Series<>(Arrays.asList(args),comparator);
	}

	//お約束メソッド
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Series<?>)) return false;

		Series<T> other = (Series<T>) o;
		List<T> source = new ArrayList<>(members);
		List<T> target = new ArrayList<>(other.members);
		return source.equals(target);
	}

	@Override
	public int hashCode() {
		return members.hashCode() ;
	}

	@Override
	public String toString() {
		return members.toString();
	}

	public static <T> Series withStringBaseComparator(Collection<T> members) {
		return new Series<T>(members, (each,other) -> each.toString().compareTo(other.toString()));
	}
}
