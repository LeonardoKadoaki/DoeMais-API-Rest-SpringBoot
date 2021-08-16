package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class DoadorDonatarioTypeTest {

	private DonatarioType donatarioType;
	private DonatarioType donatarioType2;

	@Test
	void TestSettersAndGetters() {
		donatarioType = new DonatarioType();

		donatarioType.setIdDonatario(1);

		assertEquals(1, donatarioType.getIdDonatario());

	}

	@Test
	void TestandoEquals() {
		donatarioType = new DonatarioType();
		donatarioType2 = new DonatarioType();

		donatarioType.setIdDonatario(1);
		donatarioType2.setIdDonatario(1);

		boolean res = donatarioType.equals(donatarioType2);

		assertFalse(res);

	}

	@Test
	void TestandoNotEquals() {
		donatarioType = new DonatarioType();
		donatarioType2 = new DonatarioType();

		donatarioType.setIdDonatario(1);
		donatarioType2.setIdDonatario(2);

		boolean res = donatarioType.equals(donatarioType2);

		assertFalse(res);

	}

	@Test
	void TestHashCode() {
		donatarioType = new DonatarioType();
		donatarioType2 = new DonatarioType();

		donatarioType.setIdDonatario(1);
		donatarioType2.setIdDonatario(2);

		
		assertNotEquals(donatarioType.hashCode(), donatarioType2.hashCode());
	}

}
