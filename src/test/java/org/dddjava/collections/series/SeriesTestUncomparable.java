package org.dddjava.collections.series;

import org.dddjava.collections.element.ElementUncomparable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import java.util.Comparator;
import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SeriesTestUncomparable
{

    static ElementUncomparable abc = new ElementUncomparable("abc", 123);
    static ElementUncomparable xyz = new ElementUncomparable("xyz", 789);
    static ElementUncomparable jkl = new ElementUncomparable("lmn", 456);
    static List<ElementUncomparable> list = new ArrayList<>();

    static
    {
        list.add(abc);
        list.add(xyz);
        list.add(jkl);
    }

    @Test
    public void constructUnComparable() {
        assertThrows(ClassCastException.class,
                () -> new Series<>(list));
    }

    @Test
    public void constructUnComparableWithComparator(){
        Series<ElementUncomparable> series =
                new Series<>(
                        list, comparing(ElementUncomparable::toString)
                );
        assertThat(series.members.comparator()).isNotNull();
    }

    @Test
    public void constructUnComparableWithComparing(){
        Series<ElementUncomparable> series =
                new Series<>(list, comparing(ElementUncomparable::toString));
    }

    @Test
    public void constructUnComparableWithComparingReverse(){
        Comparator<ElementUncomparable> stringAndReverse = comparing(ElementUncomparable::toString,Comparator.reverseOrder());
        Comparator<ElementUncomparable> toStringAndReverse =(each, other)->each.toString().compareTo(other.toString()) * -1  ;

        Series<ElementUncomparable> series =
                new Series<>(list, stringAndReverse);
        Series<ElementUncomparable> expected = new Series<>(list,toStringAndReverse);
        System.out.println(expected.toString());
        System.out.println(series.toString());
        System.out.println(expected.equals(series));
        assertThat(series).isEqualTo(expected);
    }

    @Test
    public void size()
    {

    }

    @Test
    public void isEmpty()
    {

    }

    @Test
    public void includes()
    {

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
    public void selectUnComparable()
    {
        Comparator<ElementUncomparable> comparator = comparing(ElementUncomparable::toString);
        Series<ElementUncomparable> expected = Series.of(comparator, abc);
        Series<ElementUncomparable> source = new Series<>(list,comparator);
        Series<ElementUncomparable> target = source.select(each->each.equals(abc));
        assertThat(target).isEqualTo(expected);
    }

    @Test
    public void reject() {

    }

    @Test public void detect() {

    }

    @Test
    public void detectOrDefault() {

    }

    @Test
    public void reduce() {

    }

    @Test
    public void map() {

    }

    @Test
    public void ofUnComparable() {
        assertThrows(ClassCastException.class, () -> Series.of(abc, xyz));
    }
}