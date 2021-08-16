package com.doemais.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.doemais.api.enums.MissaoEnum;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MissaoException;
import com.doemais.api.exception.MoedasException;
import com.doemais.api.models.EstatisticasUsuario;
import com.doemais.api.models.Missao;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EstatisticasUsuarioRepository;
import com.doemais.api.repository.MedalhaRepository;
import com.doemais.api.repository.MissaoRealizadaUsuarioRepository;
import com.doemais.api.repository.MissaoRepository;

@SpringBootTest
@ContextConfiguration(classes = {MissaoRepository.class, MissaoService.class})
class MissaoServiceTest {

	@MockBean
	private MissaoService missaoService;

	@MockBean
	private MissaoRepository missaoRepository;
	
	@MockBean
	private EstatisticasUsuarioRepository userEstatistica;
	
	@MockBean
	private MissaoRealizadaUsuarioRepository missaoRealizadaUsuarioRepository;

	private Missao missao;

	private List<Missao> missoes = new ArrayList<>();

	private Usuario user;
	
	private EstatisticasUsuario stats;
	
	private List<EstatisticasUsuario> listaStats = new ArrayList<EstatisticasUsuario>();
			
	


	@BeforeEach
	void Configuracao() {
		missao = new Missao();
		missao.setIdMissao(1);
		missao.setDescricaoMissao("teste");
		missao.setMoedasMissao(300);

		missoes = new ArrayList<>();

		user = new Usuario();
		user.setIdUsuario(1);
		
		listaStats = new ArrayList<EstatisticasUsuario>();
		
		stats = new EstatisticasUsuario();
		stats.setIdEstatisticas(1);
		stats.setTotalMoedas(300);
		stats.setUsuario(user);
		
	}

	@Test
	void TestCadastraMissao() {
		Mockito.when(missaoRepository.save(missao)).thenReturn(missao);

		Missao missaoAux = missaoService.cadastrarMissao(missao);

		assertNotEquals(missaoAux, missao);

	}

	@Test
	void TestConsultaMissao() {
		Mockito.when(missaoRepository.findAll()).thenReturn(missoes);

		List<Missao> missaoAuxList = missaoService.consultarMissoes();

		assertEquals(missaoAuxList, missoes);

	}

	@Test
	void TestCompletaMissao() throws EntidadeNaoEncontradaException, MissaoException, MoedasException {
		Mockito.when(missaoRepository.save(missao)).thenReturn(missao);
		
	    missaoService.completaMissao(user, MissaoEnum.PRIMEIRA_DOACAO);
	    
	    assertTrue(true);
	}

	@Test
	void TestEstatisticasUsuario()  {
		Mockito.when(userEstatistica.findByUsuarioIdUsuario(user.getIdUsuario())).thenReturn(Optional.of(stats));	    
		
	    EstatisticasUsuario estatistica = userEstatistica.save(stats);
	    
	    assertNotEquals(estatistica, stats);
	    
	}
	

	@Test
	void TestMissaoRealizada()  {
		int converter = (int) missao.getIdMissao();
		Mockito.when(missaoRealizadaUsuarioRepository
				.verificaMissaoRealizadaUsuario(user.getIdUsuario(), converter)).thenReturn(converter);
		            
		boolean missaoBool = missaoService.isMissaoRealizada(user.getIdUsuario(), MissaoEnum.PRIMEIRA_DOACAO);
		
		assertFalse(missaoBool);
	}
	
	
	
	
}
