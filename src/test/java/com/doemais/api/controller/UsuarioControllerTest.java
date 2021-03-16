package com.doemais.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.doemais.api.dto.CadastroDto;
import com.doemais.api.dto.ReputacaoDto;
import com.doemais.api.models.ResumoReputacaoUsuario;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.AuthRepository;
import com.doemais.api.repository.ResumoReputacaoRepository;
import com.doemais.api.repository.UsuarioRepository;
import com.doemais.api.services.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UsuarioController usuarioController;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ResumoReputacaoRepository resumoReputacaoRepository;
	
	
	@Test
	public void TestListaUsuario() throws Exception {
		
	mockMvc.perform(get("/api/usuario/lista")
			.contentType("application/json"))
	.andExpect(status().isOk());

	}
	
	
	@Test
	public void TestCadastraUsuario() throws Exception {
		
		CadastroDto cadastroDto = new CadastroDto();
		
		cadastroDto.setNome("Testehrio");
		cadastroDto.setEmail("gi@gmail.com");
		cadastroDto.setSenha("123");
		cadastroDto.setUserName("tejfdo");
		cadastroDto.setCpf("555");
		cadastroDto.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		cadastroDto.setDataCadastro(LocalDate.of(2021, Month.DECEMBER, 22));
		cadastroDto.setNumeroCelular("3333");
		cadastroDto.setGenero("F");
		cadastroDto.setSobre("Tesh");
		
		
		mockMvc.perform(post("/api/usuario/cadastrar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cadastroDto)))
		        .andExpect(status().isCreated());
				
	}
	
	@Test
	public void TestPerfilPorId() throws Exception {
		
		CadastroDto cadastroDto = new CadastroDto();
		cadastroDto.setNome("Tlhjjh");
		cadastroDto.setEmail("kjk@aluno.ifsp.edu.br");
		cadastroDto.setSenha("12348544");
		cadastroDto.setUserName("tkkh");
		cadastroDto.setCpf("6875");
		cadastroDto.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		cadastroDto.setDataCadastro(LocalDate.of(2021, Month.JANUARY, 22));
		cadastroDto.setNumeroCelular("748654");
		cadastroDto.setGenero("F");
		cadastroDto.setSobre("Testandooff");
		
		Usuario user = usuarioService.cadastrarUsuario(cadastroDto);

		
		mockMvc.perform(get("/api/usuario/perfil/" + user.getIdUsuario())
				.contentType("application/json"))
		.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void TestAtualizarUsuario()  throws JsonProcessingException, Exception {
		
		CadastroDto cadastroDto = new CadastroDto();
		
		cadastroDto.setNome("guidedjddhh");
		cadastroDto.setEmail("guissfsdfsdffggg@aluno.ifsp.edu.br");
		cadastroDto.setSenha("1234s55j4b");
		cadastroDto.setUserName("guilhermedsantshj4ggb");
		cadastroDto.setCpf("11112141");
		cadastroDto.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		cadastroDto.setDataCadastro(LocalDate.of(2020, Month.DECEMBER, 3));
		cadastroDto.setNumeroCelular("55554774555");
		cadastroDto.setGenero("F");
		cadastroDto.setSobre("Tesfft");
		
		Usuario user = usuarioService.cadastrarUsuario(cadastroDto);
   
      	user.setNome("ugarysb");
		user.setNumeroCelular("7114242455674");
		
		usuarioRepository.save(user);
      		                  
		
	}
	
	
	@Test
	public void TestRemoveUsuario() throws JsonProcessingException, Exception {
		CadastroDto cadastroDto = new CadastroDto();
		cadastroDto.setNome("guido fdsssss");
		cadastroDto.setEmail("guisafsfdfdn@gmail.com");
		cadastroDto.setSenha("123457521744");
		cadastroDto.setUserName("guidooofdf");
		cadastroDto.setCpf("78421155845");
		cadastroDto.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 22));
		cadastroDto.setDataCadastro(LocalDate.of(2021, Month.DECEMBER, 6));
		cadastroDto.setNumeroCelular("11874417774");
		cadastroDto.setGenero("M");
		cadastroDto.setSobre("Tesdk");
		
		Usuario user = usuarioService.cadastrarUsuario(cadastroDto);
   
		
		authRepository.delete(authRepository.findByUsuarioIdUsuario(user.getIdUsuario())); 
		usuarioRepository.delete(user);
			        
		        
	}
	
	
	@Test
	public void TestAvaliaUsuario() throws Exception {
		ReputacaoDto reputacao = new ReputacaoDto();
		ResumoReputacaoUsuario resumo = new ResumoReputacaoUsuario();
		Usuario user = new Usuario();
		user.setIdUsuario(3);
		
		
		reputacao.setNotaAvaliacao(2);
        reputacao.setDataRegistro(LocalDate.of(2021, Month.JANUARY, 2));
        reputacao.setPapelUsuario("Teste");
        
        mockMvc.perform(post("/api/usuario/perfil/" + user.getIdUsuario() + "/avaliar")
        		.contentType("application/json")
        		.content(objectMapper.writeValueAsString(reputacao)));
        
        
        resumo.setIdResumo(1);
        DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = formato2.parse("05/02/2021");
        resumo.setDataAtualizacaoRegistro(data2);
        resumo.setDescricaoResumo("teste");
        resumo.setNotaAvaliacao(3);
        resumo.setUsuario(user);
        
        resumoReputacaoRepository.save(resumo);
		
        
        
        
	}
	
	
	@Test
	public void TesteRetornaAvaliacaoUsuario() throws Exception {
		 Usuario usuario = new Usuario();
		 usuario.setIdUsuario(3);
		 
		
	mockMvc.perform(get("/api/usuario/perfil/" + usuario.getIdUsuario() + "/avaliacao")
	.contentType("application/json"))
	.andExpect(status().isOk());
	
	}
	
	
	
	/*@Test
	public void TestCadastrarUsuario_CelularIgual() throws Exception {
		CadastroDto cadastroDto = new CadastroDto();
		cadastroDto.setNome("Test");
		cadastroDto.setEmail("tes@gmail.com");
		cadastroDto.setSenha("12345");
		cadastroDto.setUserName("teste uni");
		cadastroDto.setCpf("111");
		cadastroDto.setRg("222");
		//cadastroDto.setDataNascimento("1998-08-03T00:00:00.000+00:00");
		//cadastroDto.setDataCadastro("2020-08-03T00:00:00.000+00:00");
		cadastroDto.setNumeroCelular("12345678912");
		cadastroDto.setGenero("M");
		cadastroDto.setSobre("Teste");
		
		Usuario user = new Usuario();
		user.setNome("Test");
		user.setUserName("teste uni");
		user.setNumeroCelular("12345678912");
		
		mockMvc.perform(post("/api/usuario/cadastrar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cadastroDto)));
		
		
		mockMvc.perform(post("/api/usuario/cadastrar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(user)))
		        .andExpect(status().isBadRequest());
		
		
		
	}*/
	
	
	
	/*@Test
	public void TestCadastraNomeVazio() throws JsonProcessingException, Exception{
		CadastroDto cadastroDto = new CadastroDto();
		cadastroDto.setNome(null);
		cadastroDto.setEmail("testi@gmail.com");
		cadastroDto.setSenha("123456");
		cadastroDto.setUserName("teste unit");
		cadastroDto.setCpf("11145");
		cadastroDto.setRg("22225");
		//cadastroDto.setDataNascimento("1998-08-03T00:00:00.000+00:00");
		//cadastroDto.setDataCadastro("2020-08-03T00:00:00.000+00:00");
		cadastroDto.setNumeroCelular("12345678914");
		cadastroDto.setGenero("M");
		cadastroDto.setSobre("Teste");
		
		mockMvc.perform(post("/api/usuario/cadastrar")
			    .contentType("application/json")
			    .content(objectMapper.writeValueAsString(cadastroDto)))
		.andExpect(status().isBadRequest());
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
