package org.dddjava.collections.series;

import org.dddjava.collections.element.Element;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class SeriesTestComparable
{
    static Element abc = new Element("abc", 123);
    static Element xyz = new Element("xyz", 789);
    static Element jkl = new Element("lmn", 456);

    static Series<Element> one;
    static Series<Element> another;


    {
        one = SeriesBuilder.of(abc,xyz);
        another = SeriesBuilder.of(jkl);
    }


    @Test
    public void size() throws Exception {
        assertThat(one.size()).isEqualTo(2);
    }

    @Test
    public void isEmpty() {
        Series<Element> target = new Series(Collections.emptyList());
        assertThat(target.isEmpty()).isTrue();
    }

    @Test
    public void contains()
    {

    }

    @Test
    public void occurrencesOf()
    {

    }

    @Test
    public void add()
    {

    }

    @Test
    public void remove()
    {

    }

    @Test
    public void reject()
    {

    }

    @Test
    public void detect()
    {

    }

    @Test
    public void detectOrDefault()
    {

    }

    @Test
    public void reduce()
    {

    }

    @Test
    public void map()
    {

    }
}