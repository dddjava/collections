package org.dddjava.collections.group;

import java.util.*;

public class GroupBuilder<T> {

    Set<T> elements;

    public GroupBuilder(Collection<T> elements) {
        this.elements = new HashSet<>(elements);
    }

    // 要素の追加と削除
    public Group<T> add(T member) {
        Set<T> mutableSet = new HashSet<>(elements);
        mutableSet.add(member);
        return new Group<>(mutableSet);
    }

    public Group<T> remove(T member) {
        Set<T> mutableSet = new HashSet<>(elements);
        mutableSet.remove(member);
        return new Group<>(mutableSet);
    }

    // 生成

    public static <T> Group<T> of(T... args){
        Collection<T> mutableCollection = Arrays.asList(args);
        return of(mutableCollection);
    }

    public static <T> Group<T> of(Collection<T> elements) {
        Set<T> mutableSet = new HashSet<>(elements);
        return new Group(mutableSet);
    }

    public static <T> Group<T> empty() {
        return new Group<>(Collections.emptySet());
    }
}
