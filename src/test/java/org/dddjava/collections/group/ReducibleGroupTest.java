package org.dddjava.collections.group;

import org.dddjava.collections.group.elements.GroupOfDays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ReducibleGroupTest {

    GroupOfDays ofTwoDays = GroupOfDays.from("01-01", "02-05" );
    GroupOfDays ofAnotherTwoDays = GroupOfDays.from("01-01", "01-05");
    GroupOfDays ofThreeDays = GroupOfDays.from("01-02", "02-05", "03-01");

    Group<GroupOfDays> group = GroupBuilder.of(ofTwoDays, ofAnotherTwoDays, ofThreeDays);
    ReducibleGroup<GroupOfDays> reducibleGroup = ReducibleGroup.from(group);

    @Test
    void selectOne() {
        ReducibleGroup<GroupOfDays> result = reducibleGroup.reduce(each -> each.equals(ofAnotherTwoDays));
        ReducibleGroup<GroupOfDays> expected = ReducibleGroup.from(GroupBuilder.of(ofAnotherTwoDays));
        assertEquals(expected, result);
    }

    @Test
    void selectOneOrThrow() {
        GroupOfDays ofAnotherDays = GroupOfDays.from("01-01", "12-31");
        assertThrows(NoSuchElementException.class,
                () -> reducibleGroup.reduce(each -> each.equals(ofAnotherDays)));
    }

    @Test
    void selectOneOrDefault() {
        GroupOfDays notInReducibleGroup = GroupOfDays.from("01-01", "12-31");
        ReducibleGroup<GroupOfDays> default_ = ReducibleGroup.from(GroupBuilder.of(notInReducibleGroup));
        ReducibleGroup<GroupOfDays> result = reducibleGroup.reduceOrDefault(each -> each.equals(notInReducibleGroup), notInReducibleGroup);
        assertEquals(default_, result);
    }

    @Test
    @DisplayName("複数の日付集合を、すべての日付を持つひとつの日付集合に畳み込む")
    void reduceMerge() {
        ReducibleGroup<GroupOfDays> result =
                reducibleGroup.reduce((one, another) -> one.merge(another));
        GroupOfDays allDays = GroupOfDays.from("01-01","01-02", "01-05", "02-05", "03-01");
        ReducibleGroup<GroupOfDays> expected = ReducibleGroup.from(GroupBuilder.of(allDays));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("複数の日付集合を、共通する日付だけの日付集合に畳み込む")
    void reduceIntersect() {
        Group<GroupOfDays> source = GroupBuilder.of(ofTwoDays, ofAnotherTwoDays);
        ReducibleGroup<GroupOfDays> reducible = ReducibleGroup.from(source);

        ReducibleGroup<GroupOfDays> result =
                reducible.reduce((one, another) -> one.intersect(another));
        GroupOfDays common = GroupOfDays.from("01-01");
        ReducibleGroup<GroupOfDays> expected = ReducibleGroup.from(GroupBuilder.of(common));
        assertEquals(expected, result);
    }

    @Test
    void reduceWithIdentity() {
        GroupOfDays 初期値 = GroupOfDays.empty();
        ReducibleGroup<GroupOfDays> result =
                reducibleGroup.reduce( 初期値, (one, another) -> one.merge(another));
        GroupOfDays allDays = GroupOfDays.from("01-01","01-02", "01-05", "02-05", "03-01");
        ReducibleGroup<GroupOfDays> expected = ReducibleGroup.from(GroupBuilder.of(allDays));
        assertEquals(expected, result);
    }
}