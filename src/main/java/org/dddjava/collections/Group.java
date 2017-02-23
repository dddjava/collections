package org.dddjava.collections;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Group<T> {
	Set<T> members;

	public Group(Collection<T> members) {
		this.members = new HashSet<>(members);
	}

	//基本アクセス
	public int size() {
		return members.size();
	}

	//検査

	//boolean isEmpty
	//boolean includes(T member)
	//boolean contains(Predicate<T> predicate)
	//int occurrencesOf(Predicate<T> predicate)

	//追加と削除
	public Group<T> add(T member) {
		Set<T> temporary = new HashSet<>(members);
		temporary.add(member);
		return new Group<>(temporary);
	}

	public Group<T> remove(T member) {
		Set<T> temporary = new HashSet<>(members);
		temporary.remove(member);
		return new Group<>(temporary);
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

	public Group select(Predicate<T> predicate) {
		return new Group<>(members.stream().filter(predicate).collect(Collectors.toSet()));
	}

	public Group reject(Predicate<T> predicate) {
		return new Group<>(members.stream().filter(predicate.negate()).collect(Collectors.toSet()));
	}

	public T detect(Predicate<T> predicate) {
		//ToDo 検出
		//なければ、RuntimeException; NoSuchElement?
		return null;
	}

	public T detectOrDefault(Predicate<T> predicate, T defaultElement) {
		//ToDo 検出
		//なければ、defaultElementを返す
		return defaultElement;
	}

	//ToDo 集約演算
	// max
	// min

	// ToDo map
	// Functionを引数にして、異なる型で、同じ要素数のGroupを返す

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

	//ファクトリーメソッド

	public static <T> Group<T> of(T... args){
		return new Group<>(Arrays.asList(args));
	}

	@Override
	public String toString() {
		return members.toString();
	}
}
