package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Predicate;

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
		assertEquals(one.size(),3);
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

	}

	@Test
	public void detectOrDefault() throws Exception {

	}

	@Test
	public void asSorted() throws Exception {

	}

	@Test
	public void asSorted1() throws Exception {

	}

}