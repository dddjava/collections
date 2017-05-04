package org.dddjava.collections;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(one.size()).isEqualTo(3);
	}

	@Test
	public void isEmpty() throws Exception {
		assertThat(one.isEmpty()).isFalse();
	}

	@Test
	public void includes() throws Exception {
		assertThat(one.includes(new BigDecimal("0.1"))).isTrue();
	}

	@Test
	public void includesGroup() throws Exception {
		Group<BigDecimal> other = Group.of(new BigDecimal("0.1"),new BigDecimal("2.3"));
		assertThat(one.includes(other)).isTrue();
	}

	@Test
	public void contains() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(new BigDecimal("1.0")) > 0 ;
		assertThat(one.contains(predicate)).isTrue();
	}

	@Test
	public void occurrencesOf() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(new BigDecimal("1.0")) >= 0 ;
		assertThat(one.occurrencesOf(predicate)).isEqualTo(2);
	}

	@Test
	public void union() throws Exception {
		Group<BigDecimal> result = Group.of(
				new BigDecimal("0.1"),
				new BigDecimal("1.0"),
				new BigDecimal("1.2"),
				new BigDecimal("2.3")
		);

		assertThat(one.union(another).equals(result)).isTrue();
	}

	@Test
	public void minus() throws Exception {
		Group<BigDecimal> result = Group.of(
				new BigDecimal("0.1")
		);

		assertThat(one.minus(another).equals(result)).isTrue();
	}

	@Test
	public void intersect() throws Exception {
		Group<BigDecimal> result = Group.of(
				new BigDecimal("1.0"),
				new BigDecimal("2.3")
		);

		assertThat(one.intersect(another).equals(result)).isTrue();
	}

	@Test
	public void select() throws Exception {

		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.ONE) > 0 ;

		Group<BigDecimal> result = Group.of(
				new BigDecimal("2.3")
		);

		assertThat(one.select(predicate).equals(result)).isTrue();
	}

	@Test
	public void reject() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.ONE) > 0 ;

		Group<BigDecimal> result = Group.of(
				new BigDecimal("0.1"),
				new BigDecimal("1.0")
		);

		assertThat(one.reject(predicate).equals(result)).isTrue();
	}

	@Test
	public void detect() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.ONE) > 0 ;
		assertThat(one.detect(predicate)).isEqualTo(new BigDecimal("2.3"));
	}

	@Test(expected = NoSuchElementException.class)
	public void detectThrowException() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.TEN) > 0 ;
		one.detect(predicate);
	}

	@Test
	public void detectOrDefault() throws Exception {
		Predicate<BigDecimal> predicate = each -> each.compareTo(BigDecimal.TEN) > 0 ;
		BigDecimal target = one.detectOrDefault(predicate,BigDecimal.ZERO);
		assertThat(target).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	public void reduce() throws Exception {
		BigDecimal expected = new BigDecimal("3.4");
		BigDecimal target = one.reduce(BigDecimal.ZERO, (one, another) -> one.add(another));
		assertThat(target).isEqualTo(expected);
	}

	@Test
	public void mapReduce() throws Exception {
		String expected = "values: 0.1 1.0 2.3 ";

		Function<BigDecimal,String> mapper = each -> each.toString();
		String target = one.map(mapper)
				.reduce("values: ",(one,another)->one + another + " ");
		assertThat(target).isEqualTo(expected);

	}

	@Test
	public void mapTest() throws Exception {
		Function<BigDecimal,Integer> function = each -> each.intValue();

		Group<Integer> result = Group.of(
			new Integer("0"),
			new Integer("1"),
			new Integer("2")
		);

		assertThat(one.map(function).equals(result)).isTrue();
	}

}