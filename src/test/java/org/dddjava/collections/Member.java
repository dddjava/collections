package org.dddjava.collections;

public class Member implements Comparable<Member> {
	String value ;

	public Member(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(Member other) {
		return value.compareTo(other.value);
	}

	@Override
	public boolean equals(Object o) {

		if (!(o instanceof Member)) return false;

		Member other = (Member) o;

		return value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value;
	}
}
