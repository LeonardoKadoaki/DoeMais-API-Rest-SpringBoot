package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
import com.doemais.api.enums.MessageEnum;
import com.doemais.api.enums.StatusAnuncioEnum;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.AnuncioFotos;
import com.doemais.api.models.Categoria;
import com.doemais.api.models.StatusAnuncio;
import com.doemais.api.models.Usuario;
import com.doemais.api.services.AnuncioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AnuncioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AnuncioService anuncioService;
	
	private Categoria categoria = new Categoria();
	private List<Categoria> categorias = new ArrayList<>();
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<>();
	private Anuncio anuncio = new Anuncio();
	private List<Anuncio> anuncios = new ArrayList<>();
	private InteresseType interesse = new InteresseType();
	private List<InteresseType> interesseType = new ArrayList<>();
	private InteressadosAnuncioType interessados = new InteressadosAnuncioType();
	private List<InteressadosAnuncioType> interesseTypeLista = new ArrayList<>();
	private List<MessageMoedasObjectType> moedas = new ArrayList<>();
	private AnuncioFotosType anuncioType = new AnuncioFotosType();
	private AnuncioFotos foto = new AnuncioFotos();
	private AvaliacaoDto avaliacao = new AvaliacaoDto();
	private StatusAnuncioDto statusDto = new StatusAnuncioDto();
	private DoadorType doadorType = new DoadorType();
	private DonatarioType donatarioType = new DonatarioType();
	private ItensRestantesType itens = new ItensRestantesType();
    private StatusAnuncio stats = new StatusAnuncio();
	private MessageObjectType message = new MessageObjectType();
	
	@BeforeEach
	void configuracao() throws ConflictException {
		interesseType = new ArrayList<>();
		categorias = new ArrayList<>();
		usuarios = new ArrayList<>();
		interesseTypeLista = new ArrayList<>();
		moedas = new ArrayList<>();
		
		usuario = new Usuario();
		usuario.setIdUsuario(1);
		usuario.setCategoria(categoria.getNome());
		usuario.setCpf("5455445");
		usuario.setDataCadastro(LocalDate.of(2021, Month.JUNE, 28));
		usuario.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		usuario.setGenero("M");
		usuario.setNome("Teste");
		usuario.setUserName("Testesss");
		usuario.setNumeroCelular("1554545");
		usuario.setSobre("ijaaçnhna");
		categoria = new Categoria();
		categoria.setIdCategoria(1);
		categoria.setMoedasCategoria(300);
		categoria.setNome("Teste");
		
		stats = new StatusAnuncio();
		stats.setIdStatus(1);
		stats.setDescricaoStatus("CONCLUIDO");

		foto = new AnuncioFotos();
		List<AnuncioFotos> fotos = new ArrayList<>();
		anuncioType = new AnuncioFotosType();
		anuncioType.setIdAnuncio(1);
		foto.setFoto("FOTO1");
		
		for (AnuncioFotos anuncioFotos : fotos) {
			anuncioType.setFotos(fotos);
		}
		
		anuncio = new Anuncio();
		anuncio.setDataCriacao(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		anuncio.setDescricao("Teste");
		anuncio.setTitulo("Teste");
		anuncio.setCategoriaUsuarioAnunciante(categoria.getNome());
		anuncio.setFotos(fotos);
		anuncio.setNotaAvaliacao(3);
		anuncio.setIdAnuncio(1);
		anuncio.setCategoria(categoria);
		anuncio.setUsuarioAnunciante(usuario);
		anuncio.setStatus(stats);
		
		interesse = new InteresseType(1, 2);
		interessados = new InteressadosAnuncioType(1, 2, "testando");
		
		doadorType = new DoadorType();
		doadorType.setIdUsuario(usuario.getIdUsuario());
		
		donatarioType = new DonatarioType();
		donatarioType.setIdDonatario(1);
		
	    avaliacao = new AvaliacaoDto();
		avaliacao.setIdAnuncio(1);
		avaliacao.setNotaAvaliacao(3);
		avaliacao.setIdAvaliador(2);
		
		statusDto = new StatusAnuncioDto();
		statusDto.setIdAnuncio(2);
		statusDto.setIdDonatario(3);
		statusDto.setStatus(StatusAnuncioEnum.CONCLUIDO);
		
		itens = new ItensRestantesType();
		itens.setItensRestantes(1);
		itens.setNextIdAnuncio(1);
		
		message.setMessage("teste");
		message.setMessageStatus(MessageEnum.ANUNCIO_CRIADO);
		
	}

	
	@Test
	void consultaAnuncioPaginacaoTest() throws Exception {

		Mockito.when(anuncioService.buscarAnunciosPaginacao(1, 3)).thenReturn(anuncios);
		
		mockMvc.perform(
				get("/api/anuncio/consultar-anuncio-paginacao?limite=10&pagina=0").contentType("application/json"))
				.andExpect(status().isOk());

	}
	
	@Test
	void listaAnuncioPorIdUsuarioTest() throws Exception {

		Mockito.when(anuncioService.buscarAnunciosPorUsuario(usuario.getIdUsuario())).thenReturn(anuncios);

		mockMvc.perform(get("/api/anuncio/usuario/" + usuario.getIdUsuario()).contentType("application/json"))
				.andExpect(status().isOk());
	}
	
	@Test
	void listaAnuncioPorIdTest() throws Exception {

		Mockito.when(anuncioService.buscarAnuncioPorId(anuncio.getIdAnuncio())).thenReturn(anuncio);

		mockMvc.perform(get("/api/anuncio/" + anuncio.getIdAnuncio()).contentType("application/json"))
				.andExpect(status().isOk());
	}
	
	
	@Test
	void cadastrarAnuncioTest() throws Exception {

		Mockito.when(anuncioService.criarAnuncio(anuncio, true)).thenReturn(message);

		mockMvc.perform(post("/api/anuncio")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(anuncio)))
				.andExpect(status().isOk());

	}
	

	@Test
	void deletarAnuncioTest() throws Exception  {

		Mockito.doNothing().when(anuncioService).deletarAnuncio(anuncio.getIdAnuncio());
				
		mockMvc.perform(delete("/api/anuncio/" + anuncio.getIdAnuncio()).contentType("application/json"))
				.andExpect(status().isOk());

	}

	
	@Test
	void atualizarAnuncioTest() throws Exception {

		Mockito.when(anuncioService.salvarAnuncio(anuncio)).thenReturn(anuncio);

		mockMvc.perform(put("/api/anuncio/" + anuncio.getIdAnuncio()).contentType("application/json")
				.content(objectMapper.writeValueAsString(anuncio))).andExpect(status().isOk());

	}
	
	@Test
	void listaStatusAnuncioTest() throws Exception {

		mockMvc.perform(get("/api/anuncio/status/lista").contentType("application/json")).andExpect(status().isOk());

	}
	
	@Test
	void consultaAnunciosPorPalavrasChaveTest() throws Exception {

		Mockito.when(anuncioService.listarAnunciosPorPalavrasChave("teste", 1, 2)).thenReturn(anuncios);
		mockMvc.perform(get("/api/anuncio/consultar-anuncios-por-palavras-chave-paginacao?texto=Teste&limite=1&pagina=2")
				.contentType("application/json")).andExpect(status().isOk());

	}


	@Test
	void consultaAnunciosPorCidadePaginacaoTest() throws Exception {

		Mockito.when(anuncioService.listaAnunciosPorCidadeAnuncio("São paulo", 1, 3)).thenReturn(anuncios);
		mockMvc.perform(get("/api/anuncio/consultar-anuncios-por-cidade-paginacao?cidade=São Paulo&limite=60&pagina=0")
				.contentType("application/json")).andExpect(status().isOk());

	}
	
	@Test
	void RetornaAvaliacaoAnuncioTest() throws Exception {
		Mockito.when(anuncioService.getAvaliacaoAnuncio(anuncio.getIdAnuncio())).thenReturn(anuncio.getNotaAvaliacao());

		mockMvc.perform(get("/api/anuncio/" + anuncio.getIdAnuncio() + "/avaliacao").contentType("application/json"))
				.andExpect(status().isOk());

	}
	
	@Test
	void AvaliaAnuncioTest() throws Exception {
		
		Mockito.when(anuncioService.registraAvaliacaoAnuncio(avaliacao)).thenReturn(anuncio);
		
		mockMvc.perform(post("/api/anuncio/avaliar").contentType("application/json")
				.content(objectMapper.writeValueAsString(avaliacao))).andExpect(status().isOk());

	}
	
	@Test
	void AlteraStatusAnuncioTest() throws Exception {
        
		Mockito.when(anuncioService.alterarStatusAnuncio(statusDto))
		.thenReturn(moedas);
		
		mockMvc.perform(post("/api/anuncio/alterar-status").contentType("application/json")
				.content(objectMapper.writeValueAsString(statusDto))).andExpect(status().isOk());

	}
	
	@Test
	void AdicionaFotosAnuncioTest() throws Exception {
		
		
		Mockito.when(anuncioService.cadastrarFotosAnuncio(anuncioType)).thenReturn(anuncio);

		mockMvc.perform(post("/api/anuncio/fotos").contentType("application/json")
				.content(objectMapper.writeValueAsString(anuncioType))).andExpect(status().isOk());

	}
	
	@Test
	void RetornaDoadorAnuncioTest() throws Exception {
	
		Mockito.when(anuncioService.doador(anuncio.getIdAnuncio())).thenReturn(doadorType);

		mockMvc.perform(
				get("/api/anuncio/" + anuncio.getIdAnuncio() + "/doador").contentType("application/json"))
				.andExpect(status().isOk());

	}
	
	@Test
	void RetornaDonatarioAnuncioTest() throws Exception {
	
		Mockito.when(anuncioService.donatario(anuncio.getIdAnuncio())).thenReturn(donatarioType);

		mockMvc.perform(
				get("/api/anuncio/" + anuncio.getIdAnuncio() + "/donatario").contentType("application/json"))
				.andExpect(status().isOk());

	}
	
	
	@Test
	void DemonstraInteresseAnuncioTest() throws Exception {
         
		Mockito.doNothing().when(anuncioService).registrarInteresseAnuncio(interesse);
		
		mockMvc.perform(post("/api/anuncio/interesse").contentType("application/json")
				.content(objectMapper.writeValueAsString(interesse))).andExpect(status().isOk());

	}
	
	@Test
	void RetornaInteressadosAnuncioTest() throws Exception {

		Mockito.when(anuncioService.consultarInteressadosAnuncio(interesse.getIdAnuncio())).thenReturn(interesseTypeLista);

		mockMvc.perform(
				get("/api/anuncio/interessados/" + interessados.getIdAnuncio()).contentType("application/json"))
				.andExpect(status().isOk());

	}

	@Test
	void RetornaIndicadorAnunciosRestantesTest() throws Exception {
		Mockito.when(anuncioService.getItensRestantes(anuncio.getIdAnuncio())).thenReturn(itens);
		
		mockMvc.perform(get("/api/anuncio/itens-restantes/" + anuncio.getIdAnuncio())
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	
	

}
