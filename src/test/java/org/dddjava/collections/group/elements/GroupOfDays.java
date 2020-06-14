package org.dddjava.collections.group.elements;

import org.dddjava.collections.group.Group;
import org.dddjava.collections.group.GroupBuilder;

import java.time.LocalDate;
import java.util.Objects;

public class GroupOfDays {
    Group<LocalDate> elements;

    public GroupOfDays(Group<LocalDate> elements) {
        this.elements = elements;
    }

    public static GroupOfDays from(String... days) {
        Group<String> source = GroupBuilder.of(days);
        Group<String> withYear = source.map(each -> "2020-" + each);
        Group<LocalDate> groupOfDays = withYear.map(each -> LocalDate.parse(each));
        return new GroupOfDays(groupOfDays);
    }

    public static GroupOfDays empty() {
        return new GroupOfDays(GroupBuilder.empty());
    }

    public GroupOfDays merge(GroupOfDays other) {
        Group<LocalDate> result = elements.union(other.elements);
        return new GroupOfDays(result);
    }

    public GroupOfDays intersect(GroupOfDays other) {
        Group<LocalDate> result = elements.intersect(other.elements);
        return new GroupOfDays(result);
    }

    @Override
    public boolean equals(Object other) {
        GroupOfDays otherGroupOfDays = (GroupOfDays) other;
        return elements.equals(otherGroupOfDays.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return "GroupOfDays{" +
                "elements=" + elements +
                '}';
    }
}
