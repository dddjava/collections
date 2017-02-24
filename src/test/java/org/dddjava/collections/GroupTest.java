package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class GroupTest {

	static Group<BigDecimal> one;
	static Group<BigDecimal> another;

	@BeforeClass
	public static void beforeClass() {
		one = Group.of(
				new BigDecimal("0.1"),
				new BigDecimal("1.0"),
				new BigDecimal("2.3")
		);
		another = Group.of(
				new BigDecimal("1.0"),
				new BigDecimal("1.2"),
				new BigDecimal("2.3")
		);

		System.out.println(one);
		System.out.println(another);
	}

	@Test
	public void size() throws Exception {
		assertEquals(3,one.size());
	}

	@Test
	public void isEmpty() throws Exception {
		assertFalse(one.isEmpty());
	}

	@Test
	public void includes() throws Exception {
		assertTrue(one.includes(new BigDecimal("0.1")));
	}

	@Test
	public void includesGroup() throws Exception {
		Group<BigDecimal> other = Group.of(new BigDecimal("0.1"),new BigDecimal("2.3"));
		assertTrue(one.includes(other));
	}

	@Test
	public void contains() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(new BigDecimal("1.0")) > 0 ;
		assertTrue(one.contains(predicate));
	}

	@Test
	public void occurrencesOf() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(new BigDecimal("1.0")) >= 0 ;
		assertEquals(2,one.occurrencesOf(predicate));
	}

	@Test
	public void union() throws Exception {
		Group<BigDecimal> result = Group.of(
				new BigDecimal("0.1"),
				new BigDecimal("1.0"),
				new BigDecimal("1.2"),
				new BigDecimal("2.3")
		);

		assertTrue(one.union(another).equals(result));
	}

	@Test
	public void minus() throws Exception {
		Group<BigDecimal> result = Group.of(
				new BigDecimal("0.1")
		);

		assertTrue(one.minus(another).equals(result));
	}

	@Test
	public void intersect() throws Exception {
		Group<BigDecimal> result = Group.of(
				new BigDecimal("1.0"),
				new BigDecimal("2.3")
		);

		assertTrue(one.intersect(another).equals(result));
	}

	@Test
	public void select() throws Exception {

		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.ONE) > 0 ;

		Group<BigDecimal> result = Group.of(
				new BigDecimal("2.3")
		);

		assertTrue(one.select(predicate).equals(result));
	}

	@Test
	public void reject() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.ONE) > 0 ;

		Group<BigDecimal> result = Group.of(
				new BigDecimal("0.1"),
				new BigDecimal("1.0")
		);

		assertTrue(one.reject(predicate).equals(result));
	}

	@Test
	public void detect() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.ONE) > 0 ;
		assertEquals(new BigDecimal("2.3"),one.detect(predicate));
	}

	@Test(expected = NoSuchElementException.class)
	public void detectThrowException() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.TEN) > 0 ;
		one.detect(predicate);
	}

	@Test
	public void detectOrDefault() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.TEN) > 0 ;
		assertEquals( BigDecimal.ZERO,one.detectOrDefault(predicate,BigDecimal.ZERO));
	}

	@Test
	public void mapTest() throws Exception {
		Function<BigDecimal,Integer> function = each -> each.intValue();

		Group<Integer> result = Group.of(
			new Integer("0"),
			new Integer("1"),
			new Integer("2")
		);

		assertTrue(one.map(function).equals(result));
	}

}