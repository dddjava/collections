package org.dddjava.collections.sequence;

import org.dddjava.collections.element.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {

    Element one = new Element("one", 1);

    @Test
    void size() {
        Sequence<Element> sequence = SequenceBuilder.of(one);
        assertEquals(1, sequence.size());
    }

    @Test
    void isEmpty() {
        assertTrue(SequenceBuilder.empty().isEmpty());
    }
}