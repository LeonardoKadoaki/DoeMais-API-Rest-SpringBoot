package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.doemais.api.enums.MessageEnum;

@SpringBootTest
class MessageObjectTypeTest {

	private MessageObjectType msg;
	private MessageObjectType msg2;

	@Test
	void TestSettersAndGetters() {
		msg = new MessageObjectType();
		msg.setMessage("teste");
		msg.setMessageStatus(MessageEnum.ANUNCIO_CRIADO);
		
		assertEquals("teste", msg.getMessage());
		assertEquals(MessageEnum.ANUNCIO_CRIADO, msg.getMessageStatus());

	}
	

	@Test
	void TestConstrutor() {
		msg = new MessageObjectType("teste", MessageEnum.ANUNCIO_CRIADO);
		msg2 = new MessageObjectType("teste", MessageEnum.ANUNCIO_CRIADO);

		assertNotEquals(msg, msg2);

	}
	
	
}
