package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        assertNull(series.members.comparator());
    }

    @Test
    public void constructUnComparableWithComparator() throws Exception{
        Series<SomethingUnComparable> series = new Series<SomethingUnComparable>(listUnComparable,(each,other)->each.toString().compareTo(other.toString()));
        assertNotNull(series.members.comparator());
    }

    @Test
    public void constructUnComparableWithComparing() throws Exception{
        Series<SomethingUnComparable> series =
                new Series<SomethingUnComparable>(listUnComparable, Comparator.comparing(SomethingUnComparable::toString));
    }

    @Test
    public void constructUnComparableWithComparingReverse() throws Exception{
        Comparator<SomethingUnComparable> stringAndReverse = Comparator.comparing(SomethingUnComparable::toString,Comparator.reverseOrder());
        Comparator<SomethingUnComparable> toStringAndReverse =(each,other)->each.toString().compareTo(other.toString()) * -1  ;

        Series<SomethingUnComparable> series =
                new Series<SomethingUnComparable>(listUnComparable, stringAndReverse);
        Series<SomethingUnComparable> expected = new Series<>(listUnComparable,toStringAndReverse);
        System.out.println(expected.toString());
        System.out.println(series.toString());
        System.out.println(expected.equals(series));
        assertEquals(expected,series);

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
        Series<SomethingComparable> expect = Series.of(abcComparable);
        Series<SomethingComparable> source = new Series<SomethingComparable>(listComparable);
        Series<SomethingComparable> target = source.select(each->each.equals(abcComparable));
        assertEquals(expect,target);
    }

    @Test
    public void selectUnComparable() throws Exception
    {
        Comparator<SomethingUnComparable> comparator = Comparator.comparing(SomethingUnComparable::toString);
        Series<SomethingUnComparable> expect = Series.of(comparator,abcUnComparable);
        Series<SomethingUnComparable> source = new Series<SomethingUnComparable>(listUnComparable,comparator);
        Series<SomethingUnComparable> target = source.select(each->each.equals(abcUnComparable));
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
        Series<SomethingComparable> series = Series.of(abcComparable,xyzComparable);
        assertNull(series.members.comparator());
    }

    @Test(expected = ClassCastException.class)
    public void ofUnComparable() throws Exception
    {
        Series<SomethingUnComparable> series = Series.of(abcUnComparable,xyzUnComparable);
    }

}