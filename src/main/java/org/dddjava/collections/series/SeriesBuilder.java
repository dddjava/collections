package org.dddjava.collections.series;

import org.dddjava.collections.group.Group;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 順序付きの集合の生成
 * @param <T>
 */
public class SeriesBuilder<T extends Comparable<T>> {
	SortedSet<T> elements;

	public SeriesBuilder(Collection<T> elements) {
		this.elements = new TreeSet<>(elements);
	}

	public SeriesBuilder<T> add(T member) {
		SortedSet<T> temporary = new TreeSet<>(elements);
		temporary.add(member);
		return new SeriesBuilder<>(temporary);
	}

	public SeriesBuilder<T> remove(T member) {
		SortedSet<T> temporary = new TreeSet<>(elements);
		temporary.remove(member);
		return new SeriesBuilder<>(temporary);
	}

	public static <T extends Comparable<T>> Series<T> of(T... elements){
		Collection<T> mutableCollection = Arrays.asList(elements);
		return of(mutableCollection);
	}

	public static <T extends Comparable<T>> Series<T> of(Collection<T> elements) {
		return new Series<>(new TreeSet<>(elements));
	}

	//Objectメソッドのオーバーライド

	@Override
	public String toString() {
		return elements.toString();
	}
}
