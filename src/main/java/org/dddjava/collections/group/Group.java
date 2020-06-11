package org.dddjava.collections.group;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
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

	/**
	 * 要素の数を調べる
	 * @return 要素の数
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * 空集合かを検査する
	 * @return 空集合であればtrue
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * この集合が指定された集合を包含しているか検査する
	 * @param other 比較対象
	 * @return 包含していればtrue
	 */
	public boolean includes(Group<T> other) {
		Group<T> intersect = this.intersect(other);
		return intersect.equals(other);
	}

	/**
	 * 条件を満たす要素が存在するか検査する
	 * @param predicate 条件判定のラムダ式またはメソッド参照
	 * @return 条件を満たす要素が存在すればtrue
	 */
	public boolean contains(Predicate<T> predicate) {
		return elements.stream().anyMatch(predicate);
	}

	/**
	 * 条件を満たす要素の数を調べる
	 * @param predicate 条件判定のラムダ式またはメソッド参照
	 * @return 条件を満たす要素の数
	 */
	public int countIf(Predicate<T> predicate) {
		return ((int) elements.stream().filter(predicate).count());
	}

	/**
	 * 集合演算：和集合
	 * @param other
	 * @return
	 */
	public Group<T> union(Group<T> other) {
		Set<T> mutableSet = new HashSet<>(elements);
		mutableSet.addAll(other.elements);
		return new Group<>(mutableSet);
	}

	/**
	 * 集合演算：差集合
	 * @param other
	 * @return
	 */
	public Group<T> difference(Group<T> other) {
		Set<T> mutableSet = new HashSet<>(elements);
		mutableSet.removeAll(other.elements);
		return new Group<>(mutableSet);
	}

	/**
	 * 集合演算：
	 * @param other
	 * @return
	 */
	public Group<T> intersect(Group<T> other) {
		Set<T> mutableSet = new HashSet<>(elements);
		mutableSet.retainAll(other.elements);
		return new Group<>(mutableSet);
	}

	/**
	 * 条件に合う要素の集合を抽出する
	 * @param predicate
	 * @return
	 */
	@Deprecated
	public Group<T> select(Predicate<T> predicate) {
		return new Group<>(elements.stream().filter(predicate).collect(Collectors.toSet()));
	}

	/**
	 * 条件に合わない要素の集合を抽出する
	 * @param predicate
	 * @return
	 */
	public Group<T> reject(Predicate<T> predicate) {
		return new Group<>(elements.stream().filter(predicate.negate()).collect(Collectors.toSet()));
	}

	/**
	 * 条件に合う要素を一つだけ持つ集合を抽出する（存在しない場合、例外を送出）
	 * @param predicate
	 * @return
	 */
	public Group<T> selectOne(Predicate<T> predicate) {
		T result = elements.stream()
				.filter(predicate).findFirst()
				.orElseThrow(NoSuchElementException::new);
		return new Group(Set.of(result));
	}

	/**
	 * 条件に合う要素を一つだけ持つ集合を抽出する（存在しない場合、既定の要素を使う）
	 * @param predicate
	 * @param defaultElement
	 * @return
	 */
	public Group<T> selectOneOrDefault(Predicate<T> predicate, T defaultElement) {
		T result = elements.stream()
			.filter(predicate).findFirst()
			.orElse(defaultElement);
		return new Group(Set.of(result));
	}

	/**
	 * 集約（畳み込む）
	 * @param identity 単位元
	 * @param accumulator　集約を行うラムダ式またはメソッド参照
	 * @return
	 */
	public Group<T> reduce(T identity, BinaryOperator<T> accumulator) {
		T result = elements.stream().reduce(identity, accumulator);
		return new Group(Set.of(result));
	}

	@Deprecated
	public Group<T> reduce(BinaryOperator<T> accumulator) {
		T result = elements.stream().reduce(accumulator).orElseThrow(NoSuchElementException::new);
		return new Group(Set.of(result));
	}

	// 写像
	@Deprecated
	public <R> Group<R> map(Function<T,R> function) {
		return new Group<>(elements.stream().map(function).collect(Collectors.toSet()));
	}

	/**
	 * 要素一つの集合から要素を取り出す
	 * @return 要素
	 */
	public T toElement() {
		Optional<T> result =  elements.stream().findFirst();
		return result.orElseThrow(IllegalStateException::new);
	}

	// Objectメソッドのオーバーライド

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
