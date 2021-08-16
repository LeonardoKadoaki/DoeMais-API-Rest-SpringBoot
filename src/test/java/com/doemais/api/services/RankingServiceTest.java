package com.doemais.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.doemais.api.dto.PosicaoRankingType;
import com.doemais.api.models.EstatisticasUsuario;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EstatisticasUsuarioRepository;
import com.doemais.api.repository.MedalhaRepository;


@SpringBootTest
@ContextConfiguration(classes = {EstatisticasUsuarioRepository.class, RankingService.class})
class RankingServiceTest {

	@MockBean
	private RankingService rankingService;

	@MockBean
	private EstatisticasUsuarioRepository estatistica;
	
	private PosicaoRankingType posicao;
	
	private List<EstatisticasUsuario> listaEstatistica = new ArrayList<>();
	
	private EstatisticasUsuario estatisticasUsuario;
	
	private Usuario user;
	
    @BeforeEach
    void Configuracao() {
    	
    	user = new Usuario();
    	posicao = new PosicaoRankingType();
    	posicao.setNome("teste");
    	posicao.setPosicao(1);
    	posicao.setTotalMoedas(300);
    	
    	estatisticasUsuario = new EstatisticasUsuario();
    	estatisticasUsuario.setIdEstatisticas(1);
    	estatisticasUsuario.setTotalMoedas(300);
    	estatisticasUsuario.setUsuario(user);
    	
    	
    	listaEstatistica = new ArrayList<EstatisticasUsuario>();
    	
    }
    
    
    @Test
    void TestConsultaRanking() {
    Mockito.when(estatistica.consultarRanking()).thenReturn(listaEstatistica);
    	
    List<PosicaoRankingType> estats = rankingService.consultarRanking();
    
    assertEquals(estats, listaEstatistica );
    
    }
     
    
}
