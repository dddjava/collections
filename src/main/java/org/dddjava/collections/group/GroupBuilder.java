package org.dddjava.collections.group;

import java.util.*;

public class GroupBuilder<T> {

    Set<T> members;

    public GroupBuilder(Collection<T> members) {
        this.members = new HashSet<>(members);
    }

    // 要素の追加と削除
    public Group<T> add(T member) {
        Set<T> mutableSet = new HashSet<>(members);
        mutableSet.add(member);
        return new Group<>(Collections.unmodifiableSet(mutableSet));
    }

    public Group<T> remove(T member) {
        Set<T> mutableSet = new HashSet<>(members);
        mutableSet.remove(member);
        return new Group<>(Collections.unmodifiableSet(mutableSet));
    }

    public static <T> Group<T> of(T... args){
        Collection<T> mutableCollection = Arrays.asList(args);
        return new Group<>(Collections.unmodifiableCollection(mutableCollection));
    }

    public static <T> Group<T> empty() {
        return new Group<>(Collections.emptySet());
    }
}
