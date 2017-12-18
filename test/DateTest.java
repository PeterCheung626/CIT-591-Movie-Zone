package test;
import moviezone.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class DateTest {
	Date d = new Date("2016-3-21");
	
	@Test
	void testBefore() {
		Date tmp = new Date("2017-5-2");
		int res = d.compareTo(tmp);
		assertEquals("2016-3-21 is before 2017-5-2", -1, res);
	}
	
	@Test
	void testAfter() {
		Date tmp = new Date("2016-1-31");
		int res = d.compareTo(tmp);
		assertEquals("2016-3-21 is after 2016-1-31", 1, res);
	}
	
	@Test
	void testEqual() {
		Date tmp = new Date("2016-3-21");
		int res = d.compareTo(tmp);
		assertEquals("2016-3-21 equals 2016-3-21", 0, res);
	}
	
	@Test
	void testDay() {
		int day = d.getDay();
		assertEquals("Day should be 21", 21, day);
	}
	
	@Test
	void testMonth() {
		int month = d.getMonth();
		assertEquals("Month should be 3", 3, month);
	}
	
	@Test
	void testYear() {
		int year = d.getYear();
		assertEquals("Year should be 2016", 2016, year);
	}
	
	@Test
	void testToString() {
		String standard = "2016 / 3 / 21";
		String res = d.toString();
		assertEquals("String form should be 2016 / 3 / 21", standard, res);
	}

}
