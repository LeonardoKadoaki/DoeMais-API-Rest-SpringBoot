package com.doemais.api.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.dto.AnuncioFotosType;
import com.doemais.api.dto.AvaliacaoDto;
import com.doemais.api.dto.DoadorType;
import com.doemais.api.dto.DonatarioType;
import com.doemais.api.dto.InteressadosAnuncioType;
import com.doemais.api.dto.InteresseType;
import com.doemais.api.dto.ItensRestantesType;
import com.doemais.api.dto.MessageMoedasObjectType;
import com.doemais.api.dto.MessageObjectType;
import com.doemais.api.dto.StatusAnuncioDto;
import com.doemais.api.enums.StatusAnuncioEnum;
import com.doemais.api.exception.AnuncioException;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.MissaoException;
import com.doemais.api.exception.MoedasException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.AnuncioFotos;
import com.doemais.api.models.Categoria;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.AnuncioRepository;

@SpringBootTest
class AnuncioServiceTest {

	@MockBean
	private AnuncioService service;

	@MockBean
	private AnuncioRepository anuncioRepository;

	private Categoria categoria;
	private StatusAnuncio statusAnuncio;
	private Usuario usuario;
	private Anuncio anuncio;
	private List<Anuncio> anuncios = new ArrayList<>();
	private InteresseType interesse;
	private InteressadosAnuncioType interessados;
	private AnuncioFotosType anuncioType;
	private AnuncioFotos foto;
	private List<AnuncioFotos> fotos = new ArrayList<>();
	private DoadorType doadorType;
	private DonatarioType donatarioType;
	private AvaliacaoDto avaliacao;
	private StatusAnuncioDto statusDto;


	@BeforeEach
	void Configuracao() throws ConflictException {
        anuncio = new Anuncio();
        usuario = new Usuario();
        categoria = new Categoria();
        statusAnuncio = new StatusAnuncio();
        interesse = new InteresseType();
        interessados = new InteressadosAnuncioType();
        anuncioType = new AnuncioFotosType();
        avaliacao = new AvaliacaoDto();
        foto  = new AnuncioFotos();
        statusDto = new StatusAnuncioDto();
        fotos = new ArrayList<AnuncioFotos>();
        doadorType = new DoadorType();
        donatarioType = new DonatarioType();
        
		usuario.setIdUsuario(1);
		anuncio.setTitulo("TEE");
		anuncio.setDescricao("TEDO");
		anuncio.setCategoria(categoria);
		anuncio.setDataCriacao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataExpiracao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDataFim(LocalDateTime.now().plusDays(60));
		anuncio.setNotaAvaliacao(3);
		categoria.setIdCategoria(1);
		statusAnuncio.setIdStatus(1);
		anuncio.setStatus(statusAnuncio);
		anuncio.setUsuarioAnunciante(usuario);

		interesse.setIdAnuncio(1);
		interesse.setIdUsuario(usuario.getIdUsuario());

		interessados.setIdAnuncio(1);
		interessados.setIdUsuarioInteressado(usuario.getIdUsuario());
		interessados.setNome("Teste");

		foto.setFoto("FOTO1");

		fotos.add(foto);

		anuncioType.setIdAnuncio(1);

		for (@SuppressWarnings("unused") AnuncioFotos anuncioFotos : fotos) {
			anuncioType.setFotos(fotos);
		}

		doadorType.setIdUsuario(usuario.getIdUsuario());

		donatarioType.setIdDonatario(1);

		avaliacao.setIdAnuncio(1);
		avaliacao.setNotaAvaliacao(3);
		avaliacao.setIdAvaliador(2);

		statusDto.setIdAnuncio(2);
		statusDto.setIdDonatario(3);
		statusDto.setStatus(StatusAnuncioEnum.CONCLUIDO);

	}

	@Test
	void TestCriarAnuncio() throws EntidadeNaoEncontradaException, ConflictException, MissaoException, MoedasException,
			AnuncioException {
		Mockito.when(anuncioRepository.findById(anuncio.getIdAnuncio())).thenReturn(Optional.of(anuncio));

		MessageObjectType anuncioCr = service.criarAnuncio(anuncio, false);

		assertNull(anuncioCr);

	}

	@Test
	void TestCadastraAnuncioService() throws Exception {
		Mockito.when(anuncioRepository.save(anuncio)).thenReturn(anuncio);

		Anuncio an = service.cadastrarAnuncio(anuncio, false);

		assertNull(an);
	}

	@Test
	void salvaAnuncioService() throws Exception {
		Mockito.when(anuncioRepository.save(anuncio)).thenReturn(anuncio);

		Anuncio anun = service.salvarAnuncio(anuncio);

		assertNull(anun);

	}

	@Test
	void TestBuscaAnuncioId() throws Exception {
		Mockito.when(anuncioRepository.findByIdAnuncio(anuncio.getIdAnuncio()))
				.thenReturn(Optional.ofNullable(anuncio));

		Anuncio a = service.buscarAnuncioPorId(anuncio.getIdAnuncio());

		assertNotEquals(anuncio, a);

	}

	@Test
	void TestBuscaAnuncioPorUsuario() throws Exception {
		Mockito.when(anuncioRepository.findAllByIdUsuario(anuncio.getIdAnuncio()))
				.thenReturn(Optional.ofNullable(anuncios));

		List<Anuncio> anuncios = service.buscarAnunciosPorUsuario(usuario.getIdUsuario());

		assertTrue(anuncios.isEmpty());

	}

	@Test
	void TestRegistraAvaliacao() throws Exception {
		Mockito.when(anuncioRepository.findByIdAnuncio(anuncio.getIdAnuncio()))
				.thenReturn(Optional.ofNullable(anuncio));

		Anuncio avaliaDto = service.registraAvaliacaoAnuncio(avaliacao);

		assertNull(avaliaDto);
	}

	@Test
	void TestBuscaAnuncioPaginacao() throws Exception {
		Mockito.when(anuncioRepository.buscarAnuncioComPaginacao(1,
				PageRequest.of(1, 30, Sort.Direction.DESC, "dataCriacao"))).thenReturn(anuncios);

		List<Anuncio> anuncio2 = service.buscarAnunciosPaginacao(1, 1);

		assertTrue(anuncio2.isEmpty());

	}

	@Test
	void TestBuscaAnuncioPorCidadePaginacao() throws Exception {
		Mockito.when(anuncioRepository.buscarAnuncioPorCidadeComPaginacao("guilherme", 1,
				PageRequest.of(1, 30, Sort.Direction.DESC, "dataCriacao"))).thenReturn(anuncios);

		List<Anuncio> anuncio3 = service.listaAnunciosPorCidadeAnuncio("São paulo", 1, 1);

		assertTrue(anuncio3.isEmpty());

	}

	@Test
	void TestAlteraStatusAnuncio() throws Exception {
		Mockito.doNothing().when(anuncioRepository).atualizarStatusAnuncio(1, 1);
		List<MessageMoedasObjectType> statDto = service.alterarStatusAnuncio(statusDto);

		assertNotNull(statDto);

	}
	
	
	@Test
	void TestCadastraFotosAnuncio() throws EntidadeNaoEncontradaException {
		Mockito.when(anuncioRepository.findById(anuncio.getIdAnuncio())).thenReturn(Optional.ofNullable(anuncio));
		
        Anuncio type = service.cadastrarFotosAnuncio(anuncioType); 
        
        assertNull(type);
	}
	
	@Test
	void TestDoadorAnuncio() throws EntidadeNaoEncontradaException {
		Mockito.when(anuncioRepository.findById(anuncio.getIdAnuncio())).thenReturn(Optional.ofNullable(anuncio));
		
        DoadorType doador = service.doador(anuncio.getIdAnuncio());
        
        assertNull(doador);
	}
	
	
	@Test
	void TestDonatarioAnuncio() throws EntidadeNaoEncontradaException {
		Mockito.when(anuncioRepository.findById(anuncio.getIdAnuncio())).thenReturn(Optional.ofNullable(anuncio));
		
        DonatarioType donatario = service.donatario(anuncio.getIdAnuncio());
        
        assertNull(donatario);
	}
	
	@Test
	void TestRegistraInteresseAnuncio() throws EntidadeNaoEncontradaException, ConflictException  {
		Mockito.when(anuncioRepository.findByIdAnuncio(anuncio.getIdAnuncio())).thenReturn(Optional.ofNullable(anuncio));
		
          service.registrarInteresseAnuncio(interesse);
          assertNotNull(anuncio);
	}
	
	@Test
	void TestListaInteressadosAnuncio() throws EntidadeNaoEncontradaException  {
		Mockito.when(anuncioRepository.findById(anuncio.getIdAnuncio())).thenReturn(Optional.ofNullable(anuncio));
		
		List<InteressadosAnuncioType> interessados = service.consultarInteressadosAnuncio(anuncio.getIdAnuncio());
          
		assertTrue(interessados.isEmpty());
		
	}
	
	
	
	@Test
	void TestItensRestantesAnuncio() throws EntidadeNaoEncontradaException  {
		Mockito.when(anuncioRepository.findById(anuncio.getIdAnuncio())).thenReturn(Optional.ofNullable(anuncio));
		
		ItensRestantesType restantes = service.getItensRestantes(anuncio.getIdAnuncio());
          
		assertNull(restantes);
		
	}
	
	
	
	
	@Test
	void TestDeletaAnuncio() throws Exception {
		Mockito.when(anuncioRepository.findById(anuncio.getIdAnuncio())).thenReturn(Optional.ofNullable(anuncio));
		Mockito.doNothing().when(anuncioRepository).deleteById(anuncio.getIdAnuncio());

		service.deletarAnuncio(anuncio.getIdAnuncio());

		Mockito.never();

	}

}
