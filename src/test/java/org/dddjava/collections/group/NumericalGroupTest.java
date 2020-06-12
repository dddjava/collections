package org.dddjava.collections.group;

import org.dddjava.collections.element.Element;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumericalGroupTest {

    Element two = new Element("two", 2);
    Element three = new Element("three", 3);
    Element five = new Element("five", 5);

    NumericalGroup<Element> all = new NumericalGroup<>(Set.of(two,three,five));
    NumericalGroup<Element> twoAndFive = new NumericalGroup<>(Set.of(two, five));

    @Test
    void sum() {
        assertEquals(10, all.sum());
    }

    @Test
    void average() {
        assertEquals(3, all.average());
        assertEquals(4, twoAndFive.average());
    }

    @Test
    void max() {
        assertEquals(5, all.max());
    }

    @Test
    void min() {
        assertEquals(2, all.min());
    }
}