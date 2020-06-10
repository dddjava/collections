package org.dddjava.collections.series;

import org.dddjava.collections.Element;
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
        one = Series.of(abc,xyz);
        another = Series.of(jkl);
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
    public void includes() {
        assertThat(one.includes(abc)).isTrue();
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
    public void selectComparable()
    {
        Series<Element> expected = Series.of(abc);
        Series<Element> target = one.select(each->each.equals(abc));
        assertThat(target).isEqualTo(expected);
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

    @Test
    public void ofComparable(){
        Series<Element> series = Series.of(abc, xyz);
        assertThat(series.members.comparator()).isNull();
    }
}