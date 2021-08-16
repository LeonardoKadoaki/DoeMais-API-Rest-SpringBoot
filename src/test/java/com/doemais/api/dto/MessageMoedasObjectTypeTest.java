package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.doemais.api.enums.MessageEnum;

@SpringBootTest
class MessageMoedasObjectTypeTest {

	private MessageMoedasObjectType message;
	private MessageMoedasObjectType message2;

	@Test
    void TestSettersAndGetters() {
		message = new MessageMoedasObjectType();
		message.setMessage("teste");
		message.setMessageStatus(MessageEnum.ANUNCIO_CRIADO);
		message.setMoedas(300);
		
		assertEquals("teste", message.getMessage());
		assertEquals(MessageEnum.ANUNCIO_CRIADO, message.getMessageStatus());
		assertEquals(300, message.getMoedas());
	}
	
	@Test
	void TestConstrutor() {
		message = new MessageMoedasObjectType("teste", MessageEnum.ANUNCIO_CRIADO, 300);
		message2 = new MessageMoedasObjectType("teste", MessageEnum.ANUNCIO_CRIADO, 300);

		assertNotEquals(message, message2);
		
	}
}
