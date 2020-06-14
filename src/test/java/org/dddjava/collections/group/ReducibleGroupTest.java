package org.dddjava.collections.group;

import org.dddjava.collections.group.elements.GroupOfDays;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ReducibleGroupTest {

    GroupOfDays ofTwoDays = GroupOfDays.from("01-01", "02-05" );
    GroupOfDays ofAnotherTwoDays = GroupOfDays.from("01-01", "01-05");
    GroupOfDays ofThreeDays = GroupOfDays.from("01-02", "02-05", "03-01");

    Group<GroupOfDays> group = GroupBuilder.of(ofAnotherTwoDays, ofTwoDays, ofThreeDays);
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
    void reduce() {
        ReducibleGroup<GroupOfDays> result =
                reducibleGroup.reduce((one, another) -> one.merge(another));
        GroupOfDays allDays = GroupOfDays.from("01-01","01-02", "01-05", "02-05", "03-01");
        ReducibleGroup<GroupOfDays> expected = ReducibleGroup.from(GroupBuilder.of(allDays));
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