package org.dddjava.collections.group;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {

	static Group<MonthDay> 節句;
	static Group<MonthDay> 祭日;

	static MonthDay 七草 = MonthDay.of(1, 7);
	static MonthDay 桃の節句 = MonthDay.of(3,3);
	static MonthDay 端午 = MonthDay.of(5, 5);
	static MonthDay 七夕 = MonthDay.of(7, 7);
	static MonthDay 菊の節句 = MonthDay.of(9, 9);

	static MonthDay 正月 = MonthDay.of(1,1);
	static MonthDay 子供の日 = MonthDay.of(5, 5);
	static MonthDay 体育の日 = MonthDay.of(10, 10);

	{
		節句 = GroupBuilder.of(七草,桃の節句,端午,七夕,菊の節句);
		祭日 = GroupBuilder.of(正月,子供の日,体育の日);

		System.out.println(節句);
		System.out.println(祭日);
	}

	@Test
	public void size() {
		assertEquals(5, 節句.size());
	}

	@Test
	public void isEmpty() {
		assertFalse(節句.isEmpty());
	}

	@Test
	public void includes() {
		MonthDay 雛祭 = MonthDay.of(3, 3);
		assertTrue(節句.includes(雛祭));
	}

	@Test
	public void includesGroup()  {
		MonthDay 雛祭 = MonthDay.of(3, 3);
		MonthDay 菖蒲 = MonthDay.of(5, 5);

		Group<MonthDay> こどもの節句 = GroupBuilder.of(雛祭,菖蒲);
		assertTrue(節句.includes(こどもの節句));
	}

	@Test
	public void contains() throws Exception {
		Predicate<MonthDay> 七夕より後 = each -> each.compareTo(七夕) > 0 ;
		assertTrue(節句.contains(七夕より後));
	}

	@Test
	public void occurrencesOf() throws Exception {
		Predicate<MonthDay> 七夕以降 = each -> each.compareTo(七夕) >= 0 ;
		assertEquals(2, 節句.occurrencesOf(七夕以降));
	}

	@Test
	public void union() throws Exception {
		Group<MonthDay> expected =
			GroupBuilder.of(正月,七草,桃の節句,端午,七夕,菊の節句,体育の日 );

		assertTrue(節句.union(祭日).equals(expected));
	}

	@Test
	public void minus() throws Exception {
		Group<MonthDay> expected = GroupBuilder.of(七草,桃の節句,七夕,菊の節句 );

		assertTrue(節句.minus(祭日).equals(expected));
	}

	@Test
	public void intersect() throws Exception {
		Group<MonthDay> expected = GroupBuilder.of(端午);

		assert(節句.intersect(祭日).equals(expected));
	}

	@Test
	public void select() throws Exception {

		Predicate<MonthDay> 七夕以降 = each -> each.compareTo(七夕) >= 0 ;

		Group<MonthDay> 七夕以降の節句 = GroupBuilder.of(七夕,菊の節句);

		assertTrue(節句.select(七夕以降).equals(七夕以降の節句));
	}

	@Test
	public void reject() throws Exception {
		Predicate<MonthDay> 七夕以降 = each -> each.compareTo(七夕) >= 0 ;

		Group<MonthDay> 七夕より前の節句 = GroupBuilder.of(七草,桃の節句,端午);

		assertTrue(節句.reject(七夕以降).equals(七夕より前の節句));
	}

	@Test
	public void detect() throws Exception {
		Predicate<MonthDay> 七夕より後の節句 = each -> each.compareTo(七夕) > 0 ;
		assertEquals(菊の節句, 節句.detect(七夕より後の節句));
	}

	@Test
	public void detectThrowException() throws Exception {
		Predicate<MonthDay> 菊の節句より後の節句 = each -> each.compareTo(菊の節句) > 0 ;
		assertThrows(NoSuchElementException.class, () -> 節句.detect(菊の節句より後の節句));
	}

	@Test
	public void detectOrDefault() throws Exception {
		Predicate<MonthDay> 菊の節句より後の節句 = each -> each.compareTo(菊の節句) > 0 ;

		MonthDay 既定値 = 七草;
		MonthDay target = 節句.detectOrDefault(菊の節句より後の節句,七草);
		assertEquals(既定値, target);
	}

	@Test
	public void reduce()  {
		BinaryOperator<MonthDay> 遅い節句 =
				(one, another) -> one.isAfter(another)? one : another;
		MonthDay target = 節句.reduce(正月, 遅い節句);
		assertEquals(菊の節句,target);
	}


	@Test
	public void mapTest() throws Exception {
		Function<MonthDay,Month> 月に = each -> each.getMonth();

		Group<Month> expected = GroupBuilder.of(
				Month.JANUARY,
				Month.MARCH,
				Month.MAY,
				Month.JULY,
				Month.SEPTEMBER
		);

		assertEquals(expected, 節句.map(月に));
	}

	@Test
	public void mapReduce() {

		Integer expected = 1+3+5+7+9; //節句の月の整数値の合計

		Function<MonthDay,Integer> 月の整数値 = each -> each.getMonth().getValue();

		BinaryOperator<Integer> 月の足し算 = (one,another)->one + another;

		Integer resultWithTarget= 節句.map(月の整数値).reduce(0,月の足し算);

		assertEquals(expected, resultWithTarget);

		Integer resultWithoutTarget= 節句.map(月の整数値).reduce(月の足し算);

		assertEquals(expected, resultWithoutTarget);
	}

}