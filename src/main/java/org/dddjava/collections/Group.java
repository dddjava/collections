package org.dddjava.collections;

import java.util.*;
import java.util.function.Predicate;

public class Group<T> {
	Set<T> members;

	public Group(Collection<T> source) {
		new HashSet<>(source);
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
		temporary.removeAll(other.members);
		return new Group<>(temporary);
	}

	//フィルタリングと検出

	public Group select(Predicate<T> predicate) {
		//ToDo フィルタリング
		return new Group<>(members);
	}

	public Group reject(Predicate<T> predicate) {
		//ToDo 逆フィルタリング
		return new Group<>(members);
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
	// 全体を一つのオブジェクトで表現する
	// intValueが使える前提で実装　T instanceOf Number

	// BigDecimalで、桁数と丸め方法を指定して演算への対応方法を検討する　多分、別クラス

	// sum
	// average
	// max
	// min
	// first sort()して最初の要素を返す
	// firstOrDefault
	// last  sort()して最後の要素を返す
	// lastOrDefault

	//ToDo 変換

	private SortedSet<T> sort() {
		//Todo
		// T が comparableであれば、 Collections.sort()
		// そうでなければ、toString() の結果でソート
		return null;
	}

	public T[] asSorted() {
		//ToDo
		return null;
	}

	public T[] asSorted(Comparator<T> comparator) {
		//ToDo
		return null;
	}

	// ToDo map
	// Functionを引数にして、異なる型で、同じ要素数のGroupを返す

	//ToDo 生成
	// of( <T> ... )　可変パラメータ
}
