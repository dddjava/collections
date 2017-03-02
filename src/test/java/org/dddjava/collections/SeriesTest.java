package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SeriesTest
{
    static SomethingComparable abcComparable = new SomethingComparable("abc");
    static SomethingComparable xyzComparable = new SomethingComparable("xyz");
    static SomethingComparable lmnComparable = new SomethingComparable("lmn");
    static List<SomethingComparable> listComparable = new ArrayList<>();

    static SomethingUnComparable abcUnComparable = new SomethingUnComparable("abc");
    static SomethingUnComparable xyzUnComparable = new SomethingUnComparable("xyz");
    static SomethingUnComparable lmnUnComparable = new SomethingUnComparable("lmn");
    static List<SomethingUnComparable> listUnComparable = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() {
        listComparable.add(abcComparable);
        listComparable.add(xyzComparable);
        listComparable.add(lmnComparable);

        listUnComparable.add(abcUnComparable);
        listUnComparable.add(xyzUnComparable);
        listUnComparable.add(lmnUnComparable);
    }

    @Test
    public void constructComparable() throws Exception{
        Series<SomethingComparable> series = new Series<>(listComparable);
}

    @Test(expected = ClassCastException.class)
    public void constructUnComparable() throws Exception{
        Series<SomethingUnComparable> series = new Series<>(listUnComparable);
    }

    @Test()
    public void constructUnComparableWithComparator() throws Exception{
        Series<SomethingUnComparable> series = new Series<SomethingUnComparable>(listUnComparable,(each,other)->each.toString().compareTo(other.toString()));
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
    public void select() throws Exception
    {

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
        Series<SomethingComparable> series = Series.of(abcComparable,xyzComparable);
    }

    @Test(expected = ClassCastException.class)
    public void ofUnComparable() throws Exception
    {
        Series<SomethingUnComparable> series = Series.of(abcUnComparable,xyzUnComparable);
    }

}