package com.doemais.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.ParseException;
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
import com.doemais.api.models.Endereco;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EnderecoRepository;
import com.doemais.api.services.EnderecoService;
import com.doemais.api.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	 private EnderecoService enderecoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	public void TestListaEnderecoPorId() throws Exception {
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1);
		usuario.setNome("Tesdario");
		usuario.setUserName("teafdfdo");
		usuario.setCpf("1111111");
		usuario.setRg("33333");
		usuario.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 8));
		usuario.setDataCadastro(LocalDate.of(2021, Month.MARCH, 5));
		usuario.setNumeroCelular("22222");
		usuario.setGenero("M");
		usuario.setSobre("Tesh");
		

		Endereco endereco = new Endereco();
		endereco.setBairro("teste");
		endereco.setCep("0000000");
		endereco.setLocalidade("testando");
		endereco.setComplemento("teste");
		endereco.setIdEndereco(1);
		endereco.setLogradouro("teste");
		endereco.setNumero(1);
		endereco.setUf("SP");
		endereco.setUsuario(usuario);
		
		enderecoRepository.save(endereco);
		

		mockMvc.perform(get("/api/endereco/usuario/" + usuario.getIdUsuario())
				.contentType("application/json"))
		         .andExpect(status().isOk());
		
	}
	
	@Test
	public void TestCadastraEndereco() throws Exception{
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1);
		usuario.setNome("Tesdario");
		usuario.setUserName("teafdfdo");
		usuario.setCpf("11111");
		usuario.setRg("3333");
		usuario.setDataNascimento(LocalDate.of(1995, Month.DECEMBER, 15));
		usuario.setDataCadastro(LocalDate.of(2021, Month.NOVEMBER, 5));
		usuario.setNumeroCelular("2222");
		usuario.setGenero("M");
		usuario.setSobre("Tesh");
		

		Endereco endereco = new Endereco();
		endereco.setBairro("tete");
		endereco.setCep("09942040");
		endereco.setLocalidade("testdo");
		endereco.setComplemento("tste");
		endereco.setIdEndereco(1);
		endereco.setLogradouro("tete");
		endereco.setNumero(1);
		endereco.setUf("SP");
		endereco.setUsuario(usuario);
		
		mockMvc.perform(post("/api/endereco/cadastrar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(endereco)))
		        .andExpect(status().isOk());
		
		
	}
	
	@Test
	public void TestDeletaEnderecoUsuario() throws Exception{
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1);
		usuario.setNome("Tesro");
		usuario.setUserName("tedfdo");
		usuario.setCpf("111445");
		usuario.setRg("354553");
		usuario.setDataNascimento(LocalDate.of(2000, Month.JUNE, 15));
		usuario.setDataCadastro(LocalDate.of(2021, Month.DECEMBER, 1));
		usuario.setNumeroCelular("224554");
		usuario.setGenero("M");
		usuario.setSobre("Tesh");
		

		Endereco endereco = new Endereco();
		endereco.setBairro("tete");
		endereco.setCep("00000");
		endereco.setLocalidade("testdo");
		endereco.setComplemento("tste");
		endereco.setIdEndereco(1);
		endereco.setLogradouro("tete");
		endereco.setNumero(1);
		endereco.setUf("SP");
		endereco.setUsuario(usuario);
		enderecoRepository.save(endereco);
		
		
		enderecoRepository.delete(endereco);
		
		
		
	}
	
	
	@Test
	public void TestAtualizaEnderecoUsuario() throws Exception {
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1);
		usuario.setNome("Teso");
		usuario.setUserName("teddo");
		usuario.setCpf("1115");
		usuario.setRg("3553");
		usuario.setDataNascimento(LocalDate.of(1998, Month.DECEMBER, 5));
		usuario.setDataCadastro(LocalDate.of(2021, Month.DECEMBER, 9));
		usuario.setNumeroCelular("2254");
		usuario.setGenero("M");
		usuario.setSobre("Tesh");
		

		Endereco endereco = new Endereco();
		endereco.setBairro("tete");
		endereco.setCep("00000");
		endereco.setLocalidade("testdo");
		endereco.setComplemento("tste");
		endereco.setIdEndereco(1);
		endereco.setLogradouro("tete");
		endereco.setNumero(1);
		endereco.setUf("SP");
		endereco.setUsuario(usuario);
		
		//enderecoRepository.save(endereco);
		
		endereco.setLocalidade("São paulo");
		endereco.setNumero(1000);
		
		mockMvc.perform(put("/api/endereco/atualizar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(endereco)))
		        .andExpect(status().isOk());

		
	}
	
	
	@Test
	public void TestRetornaEnderecoCep() throws Exception {
		Endereco endereco = new Endereco();
		endereco.setCep("111111");
		mockMvc.perform(get("/api/endereco/retornacep")
				.contentType("application/json"))
		 .andExpect(status().isOk());
				
		
		
		
	}
	
	
	
	
	
}
