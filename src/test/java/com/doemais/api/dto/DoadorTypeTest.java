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
 class DoadorTypeTest {

	@Test
	 void TestSettersAndGetters() {
	DoadorType doadorType = new DoadorType();
	
		doadorType.setIdUsuario(1);
		
		assertEquals(1, doadorType.getIdUsuario());
		
	}
	
	@Test
	 void TestandoEquals() {
		DoadorType doadorType = new DoadorType();
		DoadorType doadorType2 = new DoadorType();
		
		
		doadorType.setIdUsuario(1);
		
		doadorType2.setIdUsuario(1);
		
		boolean res = doadorType.equals(doadorType2);
		
		assertFalse(res);
	
	}
	
	@Test
	 void TestandoNotEquals() {
		DoadorType doadorType = new DoadorType();
		DoadorType doadorType2 = new DoadorType();
		
		
		doadorType.setIdUsuario(1);
		
		doadorType2.setIdUsuario(2);
		
		boolean res = doadorType.equals(doadorType2);
		
		assertFalse(res);
		
	}
	
	@Test
	 void TestHashCode() {
		DoadorType doadorType = new DoadorType();
		DoadorType doadorType2 = new DoadorType();
		
		
		doadorType.setIdUsuario(1);
		
		doadorType2.setIdUsuario(1);
		
	
		assertNotEquals(doadorType.hashCode(), doadorType2.hashCode());
		
	}
	
	
}
