package com.doemais.api.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.enums.StatusAnuncioEnum;


@SpringBootTest
 class StatusAnuncioDtoTest {

	@Test
	 void TestSettersAndGetters() {
	StatusAnuncioDto status = new StatusAnuncioDto();
	
		status.setStatus(StatusAnuncioEnum.CONCLUIDO);
		
		assertEquals(StatusAnuncioEnum.CONCLUIDO , status.getStatus());
		
				
	}
	
	@Test
	 void TestandoEquals() {
		StatusAnuncioDto status = new StatusAnuncioDto();
		StatusAnuncioDto status2 = new StatusAnuncioDto();
		
		status.setStatus(StatusAnuncioEnum.CONCLUIDO);
		
		status2.setStatus(StatusAnuncioEnum.CONCLUIDO);
		
		
		boolean res = status.equals(status2);
		
		assertFalse(res);
	
	}
	
	@Test
	 void TestandoNotEquals() {
		StatusAnuncioDto status = new StatusAnuncioDto();
		StatusAnuncioDto status2 = new StatusAnuncioDto();
		
		status.setStatus(StatusAnuncioEnum.CONCLUIDO);
		
		status2.setStatus(StatusAnuncioEnum.EM_ANDAMENTO);
		
		
		boolean res = status.equals(status2);
		
		assertFalse(res);
		
	}
	
	@Test
        void TestHashCode() {
		StatusAnuncioDto status = new StatusAnuncioDto();
		StatusAnuncioDto status2 = new StatusAnuncioDto();
		
		status.setStatus(StatusAnuncioEnum.CONCLUIDO);
		
		status2.setStatus(StatusAnuncioEnum.CONCLUIDO);

		assertNotEquals(status.hashCode(), status2.hashCode());
		
	}
	
	@Test
	 void TestSetterAndGetterNull() {
		StatusAnuncioDto status = new StatusAnuncioDto();
		StatusAnuncioDto status2 = new StatusAnuncioDto();
		
		status.setStatus(null);
		
		status2.setStatus(null);

		
		assertNull(null, status.getStatus());
		
		
	}
	
	
	
	
}
