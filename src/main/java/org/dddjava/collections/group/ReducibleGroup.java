package org.dddjava.collections.group;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * 単集合に畳み込める集合
 * @param <T>
 */
public class ReducibleGroup<T> {
	Set<T> elements;

	private ReducibleGroup(Set<T> elements) {
		this.elements = elements;
	}

	public static <T> ReducibleGroup<T> from(Group<T> group) {
		return new ReducibleGroup<>(group.elements);
	}

	/**
	 * 条件に合う要素を一つ持つ単集合に縮約する（存在しない場合、例外を送出する）
	 * @param predicate
	 * @return
	 */
	public ReducibleGroup<T> reduce(Predicate<T> predicate) {
		T result = elements.stream()
				.filter(predicate).findFirst()
				.orElseThrow(NoSuchElementException::new);
		return new ReducibleGroup(Set.of(result));
	}

	/**
	 * 条件に合う要素を一つ持つ単集合に縮約する（存在しない場合、既定の要素を使う）
	 * @param predicate
	 * @param defaultElement
	 * @return
	 */
	public ReducibleGroup<T> reduceOrDefault(Predicate<T> predicate, T defaultElement) {
		T result = elements.stream()
			.filter(predicate).findFirst()
			.orElse(defaultElement);
		return new ReducibleGroup(Set.of(result));
	}

	/**
	 * 単集合に畳み込む
	 * @param identity 単位元
	 * @param accumulator　集約を行うラムダ式またはメソッド参照
	 * @return
	 */
	public ReducibleGroup<T> reduce(T identity, BinaryOperator<T> accumulator) {
		T result = elements.stream().reduce(identity, accumulator);
		return new ReducibleGroup(Set.of(result));
	}

	/**
	 * 単集合に畳み込む
	 * @param accumulator
	 * @return
	 */
	public ReducibleGroup<T> reduce(BinaryOperator<T> accumulator) {
		T result = elements.stream().reduce(accumulator).orElseThrow(NoSuchElementException::new);
		return new ReducibleGroup(Set.of(result));
	}

	/**
	 * 単集合から要素を取り出す
	 * @return 要素
	 */
	public T toElement() {
		Optional<T> result =  elements.stream().findFirst();
		return result.orElseThrow(IllegalStateException::new);
	}

	// Objectメソッドのオーバーライド

	@Override
	public boolean equals(Object other) {
		if (! (other instanceof ReducibleGroup<?>) ) return false;

		ReducibleGroup<?> otherGroup = (ReducibleGroup<?>) other;
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
