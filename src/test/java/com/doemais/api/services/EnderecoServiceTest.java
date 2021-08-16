package com.doemais.api.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.doemais.api.dto.EnderecoDto;
import com.doemais.api.dto.EnderecoType;
import com.doemais.api.models.Endereco;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EnderecoRepository;
import com.doemais.api.repository.UsuarioRepository;


@SpringBootTest
class EnderecoServiceTest {

	@MockBean
	private EnderecoRepository enderecoRepository;

	@MockBean
	private UsuarioRepository usuarioRepository;

	@MockBean
	private EnderecoService enderecoService;

	private Usuario usuario;
	private Endereco endereco;
	private EnderecoDto enderecoDto;
	private EnderecoType enderecoType;

	@BeforeEach
	void Configuracao() {

		usuario = new Usuario("testando", "testando@aluno.ifsp.edu.br", "teste1", LocalDate.of(2020, Month.DECEMBER, 3),
				LocalDate.of(1998, Month.DECEMBER, 22), "teste2", "teste2", "teste3");
		usuario.setIdUsuario(1);

		endereco = new Endereco();
		endereco.setBairro("teste");
		endereco.setCep("09942040");
		endereco.setLocalidade("teste");
		endereco.setLogradouro("teste");
		endereco.setNumero(240);
		endereco.setUf("SP");
		endereco.setUsuario(usuario);
		
		enderecoType = new EnderecoType();
		enderecoType.setCep("09942040");
		
		enderecoDto = new EnderecoDto();
		enderecoDto.setIdUsuario(usuario.getIdUsuario());
		enderecoDto.setBairro("teste");
		enderecoDto.setCep("09942040");
		enderecoDto.setIdEndereco(1);
		enderecoDto.setNumero(1444);
	}

	@Test
	void TestCadastrarEnderecoService() throws Exception {
		Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
		Endereco end = enderecoService.cadastrarEndereco(endereco);

		assertNotEquals(end, endereco);

	}

	@Test
	void TestBuscarEnderecoService() throws Exception {
		Mockito.when(enderecoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()))
				.thenReturn(Optional.of(endereco));

		EnderecoDto endDto = enderecoService.consultarEnderecoUsuario(usuario.getIdUsuario());

		assertNull(endDto);
	}

	@Test
	void TestDeletaEnderecoService() throws Exception {
		Mockito.doNothing().when(enderecoRepository).deleteByUsuarioIdUsuario(usuario.getIdUsuario());
		
		enderecoService.deletarEndereco(usuario);
		
		assertNotNull(usuario);

	}
	
	
	@Test
	void TestConsultaEnderecoUsuarioService() throws Exception {
		Mockito.when(enderecoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()))
		.thenReturn(Optional.of(endereco));
		
        Endereco enderecoAux = enderecoService.buscarEnderecoPorIdUsuario(usuario.getIdUsuario());
        
		assertNotEquals(enderecoAux, endereco);

	}
	
	@Test
	void TestPesquisaEndereco() throws Exception {
		
        EnderecoType typeEnd = enderecoService.pesquisarEndereco(enderecoType.getCep());
        
		assertNotEquals(typeEnd, enderecoType);

	}
	
	

}