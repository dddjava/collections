package org.dddjava.collections.sequence;

import java.util.*;

/**
 * 列の生成
 */
public class SequenceBuilder<T> {
    LinkedList<T> list;

    public SequenceBuilder(LinkedList<T> list) {
        this.list = list;
    }

    /**
     * 可変の要素から列を生成
     * @param args 可変の要素
     * @param <T> 列の要素の型
     * @return 列
     */
    @SafeVarargs
    public static <T> Sequence<T> of(T... args) {
        LinkedList<T> result = new LinkedList<>(Arrays.asList(args));
        return new Sequence<>(result);
    }

    /**
     * コレクションから列を生成
     * @param <T> 列の要素の型
     * @param elements　コレクション
     * @return 列
     */
    public static <T> Sequence<T> of(Collection<T> elements) {
        LinkedList<T> mutableList = new LinkedList<>(elements);
        return new Sequence<>(mutableList);
    }

    /**
     * 空の集合を生成
     * @param <T> 要素の型
     * @return 列
     */
    public static <T> Sequence<T> empty() {
        return new Sequence<>(new LinkedList<>());
    }
}
