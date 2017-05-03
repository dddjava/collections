package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.junit.Assert.*;

public class SeriesTestUncomparableMembers
{

    static MemberUncomparable abc = new MemberUncomparable("abc");
    static MemberUncomparable xyz = new MemberUncomparable("xyz");
    static MemberUncomparable jkl = new MemberUncomparable("lmn");
    static List<MemberUncomparable> list = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() {

        list.add(abc);
        list.add(xyz);
        list.add(jkl);
    }

    @Test(expected = ClassCastException.class)
    public void constructUnComparable() throws Exception{
        Series<MemberUncomparable> series = new Series<>(list);
        assertNull(series.members.comparator());
    }

    @Test
    public void constructUnComparableWithComparator() throws Exception{
        Series<MemberUncomparable> series =
                new Series<>(
                        list, comparing(MemberUncomparable::toString)
                );
        assertNotNull(series.members.comparator());
    }

    @Test
    public void constructUnComparableWithComparing() throws Exception{
        Series<MemberUncomparable> series =
                new Series<MemberUncomparable>(list, comparing(MemberUncomparable::toString));
    }

    @Test
    public void constructUnComparableWithComparingReverse() throws Exception{
        Comparator<MemberUncomparable> stringAndReverse = comparing(MemberUncomparable::toString,Comparator.reverseOrder());
        Comparator<MemberUncomparable> toStringAndReverse =(each, other)->each.toString().compareTo(other.toString()) * -1  ;

        Series<MemberUncomparable> series =
                new Series<MemberUncomparable>(list, stringAndReverse);
        Series<MemberUncomparable> expected = new Series<>(list,toStringAndReverse);
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
    public void selectUnComparable() throws Exception
    {
        Comparator<MemberUncomparable> comparator = comparing(MemberUncomparable::toString);
        Series<MemberUncomparable> expect = Series.of(comparator, abc);
        Series<MemberUncomparable> source = new Series<MemberUncomparable>(list,comparator);
        Series<MemberUncomparable> target = source.select(each->each.equals(abc));
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

    @Test(expected = ClassCastException.class)
    public void ofUnComparable() throws Exception
    {
        Series<MemberUncomparable> series = Series.of(abc, xyz);
    }

}