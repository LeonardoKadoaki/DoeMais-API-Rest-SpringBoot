package com.doemais.api.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.models.EstatisticasUsuario;
import com.doemais.api.models.MissaoRealizadaUsuario;
import com.doemais.api.models.Usuario;


@SpringBootTest
 class EstatisticasUsuarioTest {

	private EstatisticasUsuario estatistica = new EstatisticasUsuario();
	private Usuario user = new Usuario();
	
	@Test
	 void TestMoedaEstatisticaUsuario()  {
		
		estatistica.setTotalMoedas(100);
		estatistica.setIdEstatisticas(1);
		estatistica.setUsuario(user);
		
		assertEquals(100, estatistica.getTotalMoedas());
		assertEquals(1, estatistica.getIdEstatisticas());
		assertEquals(user, estatistica.getUsuario());

	}
	
	
	@Test
	 void TestEquals()  {
		
		EstatisticasUsuario estatistica2 = new EstatisticasUsuario();
		
		estatistica.setTotalMoedas(100);
		estatistica2.setTotalMoedas(100);
	   
		boolean res = estatistica.equals(estatistica2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestNotEquals()  {
        EstatisticasUsuario estatistica2 = new EstatisticasUsuario();
		
		estatistica.setTotalMoedas(100);
		estatistica2.setTotalMoedas(300);
	   
		boolean res = estatistica.equals(estatistica2);
		
		assertTrue(res);
	}
	
	@Test
	void TestHashCode()  {
        EstatisticasUsuario estatistica2 = new EstatisticasUsuario();
		
		estatistica.setTotalMoedas(100);
		estatistica2.setTotalMoedas(100);
	  
		assertEquals(estatistica.hashCode(), estatistica2.hashCode());
		
		
	}
	
	@Test
	void ToString() {
		estatistica.setTotalMoedas(100);
		estatistica.setIdEstatisticas(1);
		estatistica.setUsuario(user);
		
		assertNotNull(estatistica.toString());
	}
	

	
}
