package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;

public class SeriesTestComparableMembers
{
    static Member abc = new Member("abc");
    static Member xyz = new Member("xyz");
    static Member jkl = new Member("lmn");

    static Series<Member> one;
    static Series<Member> another;


    @BeforeClass
    public static void beforeClass() {
        one = Series.of(abc,xyz);
        another = Series.of(jkl);
    }


    @Test
    public void size() throws Exception {
        assertThat(one.size()).isEqualTo(2);
    }

    @Test
    public void isEmpty() throws Exception
    {
        Series<Member> target = new Series(Collections.emptyList());
        assertThat(target.isEmpty()).isTrue();
    }

    @Test
    public void includes() throws Exception
    {
        assertThat(one.includes(abc)).isTrue();
    }

    @Test
    public void contains() throws Exception
    {

    }

    @Test
    public void occurrencesOf() throws Exception
    {

    }

    @Test
    public void add() throws Exception
    {

    }

    @Test
    public void remove() throws Exception
    {

    }

    @Test
    public void selectComparable() throws Exception
    {
        Series<Member> expected = Series.of(abc);
        Series<Member> target = one.select(each->each.equals(abc));
        assertThat(target).isEqualTo(expected);
    }


    @Test
    public void reject() throws Exception
    {

    }

    @Test
    public void detect() throws Exception
    {

    }

    @Test
    public void detectOrDefault() throws Exception
    {

    }

    @Test
    public void reduce() throws Exception
    {

    }

    @Test
    public void map() throws Exception
    {

    }

    @Test
    public void ofComparable() throws Exception{
        Series<Member> series = Series.of(abc, xyz);
        assertThat(series.members.comparator()).isNull();
    }

}