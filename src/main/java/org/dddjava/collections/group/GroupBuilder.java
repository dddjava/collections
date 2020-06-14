package org.dddjava.collections.group;

import java.util.*;

/**
 * 集合の生成
 * @param <T>
 */
public class GroupBuilder<T> {

    Set<T> elements;

    public GroupBuilder(Collection<T> elements) {
        this.elements = new HashSet<>(elements);
    }

    /**
     * 要素の追加
     * @param element
     * @return 要素が追加された別の集合
     */
    public Group<T> add(T element) {
        Set<T> mutableSet = new HashSet<>(elements);
        mutableSet.add(element);
        return new Group<>(mutableSet);
    }

    /**
     * 要素の削除
     * @param element
     * @return 要素が削除された別の集合
     */
    public Group<T> remove(T element) {
        Set<T> mutableSet = new HashSet<>(elements);
        mutableSet.remove(element);
        return new Group<>(mutableSet);
    }

    /**
     * 可変の要素から集合を生成
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> Group<T> of(T... elements){
        return of(Arrays.asList(elements));
    }

    /**
     * コレクションから集合を生成
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> Group<T> of(Collection<T> elements) {
        Set<T> mutableSet = new HashSet<>(elements);
        return new Group(mutableSet);
    }

    /**
     * 空の集合を生成
     * @param <T>
     * @return
     */
    public static <T> Group<T> empty() {
        return new Group<>(Collections.emptySet());
    }
}
