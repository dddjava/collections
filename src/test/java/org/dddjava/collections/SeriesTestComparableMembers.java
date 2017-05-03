package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SeriesTestComparableMembers
{
    static Member abc = new Member("abc");
    static Member xyz = new Member("xyz");
    static Member jkl = new Member("lmn");
    static List<Member> list = new ArrayList<>();


    @BeforeClass
    public static void beforeClass() {
        list.add(abc);
        list.add(xyz);
        list.add(jkl);
    }

    @Test
    public void constructComparable() throws Exception{
        Series<Member> series = new Series<>(list);
}



    @Test
    public void constructUnComparableWithStringBaseComparator() throws Exception{
        Series<MemberUncomparable> series = Series.withStringBaseComparator(list);
        assertNotNull(series.members.comparator());
    }



    @Test
    public void size() throws Exception
    {

    }

    @Test
    public void isEmpty() throws Exception
    {

    }

    @Test
    public void includes() throws Exception
    {

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
        Series<Member> expect = Series.of(abc);
        Series<Member> source = new Series<Member>(list);
        Series<Member> target = source.select(each->each.equals(abc));
        assertEquals(expect,target);
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
        assertNull(series.members.comparator());
    }

}