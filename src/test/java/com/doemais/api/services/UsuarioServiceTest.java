 package com.doemais.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.doemais.api.dto.AvaliacaoType;
import com.doemais.api.dto.CadastroDto;
import com.doemais.api.dto.ReputacaoDto;
import com.doemais.api.enums.CategoriaDoadorEnum;
import com.doemais.api.enums.GeneroEnum;
import com.doemais.api.models.Perfil;
import com.doemais.api.models.ReputacaoUsuario;
import com.doemais.api.models.ResumoReputacaoUsuario;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.MedalhaRepository;
import com.doemais.api.repository.ReputacaoRepository;
import com.doemais.api.repository.ResumoReputacaoRepository;
import com.doemais.api.repository.UsuarioRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UsuarioRepository.class, UsuarioService.class})
class UsuarioServiceTest {

	@MockBean
	private UsuarioRepository usuarioRepository;

	@MockBean
	private UsuarioService usuarioService;

	@MockBean
	private ResumoReputacaoRepository resumoReputacaoRepository;
	
	@MockBean
	private ReputacaoRepository reputacaoRepository;
	
    private CadastroDto cadastroDto;  
	private Usuario usuario;
	private List<Usuario> usuarios = new ArrayList<>();
	private ReputacaoDto dto;
	private ReputacaoUsuario reputacaoUsuario;
	private ResumoReputacaoUsuario resumoReputacaoUsuario;
	private Perfil perfil;

    @BeforeEach
    void Configuracao() throws ParseException {
    	cadastroDto = new CadastroDto("teste", "teste@gmail.com", "12345", "teste", "2545645",
				LocalDate.of(1998, Month.DECEMBER, 22), "5454455", "teste", "testando");
    	
    	usuario = new Usuario("testando", "testando@aluno.ifsp.edu.br", "teste1", LocalDate.of(2020, Month.DECEMBER, 3),
				LocalDate.of(1998, Month.DECEMBER, 22), "teste2", "teste2", "teste3");
    	
    	usuario.setIdUsuario(1);
    	
    	perfil = new Perfil();
    	perfil.setId(1L);
    	
    	reputacaoUsuario = new ReputacaoUsuario();
    	
		dto = new ReputacaoDto(1, "teste", 1, "teste");
		
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = formato2.parse("05/02/2021");
		
	    resumoReputacaoUsuario = new ResumoReputacaoUsuario(1, "Teste", 2, data2, usuario);
	    
    	
    }
    
    
    
    @Test
    void TestCadastraUsuarioService() throws Exception{
           Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
           
           Usuario result = usuarioService.cadastrarUsuario(cadastroDto);
          assertNull(result);
            
    }
    
    
    @Test
     void  TestBuscaUsuarioId() throws Exception {
    	
    	Mockito.when(usuarioRepository.findByIdUsuario(usuario.getIdUsuario())).thenReturn(Optional.ofNullable(usuario));
    	Usuario resultado = usuarioService.buscarUsuarioPorId(usuario.getIdUsuario());
    	assertNull(resultado);
    }
    
    
    @Test
     void  TestDeletaUsuario() throws Exception {
    
    	Mockito.when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.ofNullable(usuario));
    	Mockito.doNothing().when(usuarioRepository).deleteById(usuario.getIdUsuario());
    	
    	usuarioService.deletarUsuario(usuario.getIdUsuario());
    	
    	assertNotNull(usuario);
    	
    	
    }	
    
    @Test
     void  TestAtualizaUsuario() throws Exception {
    	Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
    	Mockito.when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
    	
    	Usuario usuarioAtualiza = usuarioService.atualizarUsuario(usuario);
    	
    	assertNotEquals(usuarioAtualiza, usuario);
    }
    
    
    @Test
     void  TestAvaliacaoUsuario() throws Exception {
    	Mockito.when(reputacaoRepository.existsById(dto.getIdAvaliador())).thenReturn(false);
    	Mockito.when(reputacaoRepository.findByIdAvaliacao(dto.getIdAvaliador())).thenReturn(reputacaoUsuario);
    	Mockito.when(resumoReputacaoRepository.
    			findById(resumoReputacaoUsuario.getUsuario().getIdUsuario())).thenReturn(Optional.of(resumoReputacaoUsuario));
    	Mockito.when(resumoReputacaoRepository.findById(resumoReputacaoUsuario.getUsuario().getIdUsuario()))
    	.thenReturn(Optional.of(resumoReputacaoUsuario));
    	Mockito.when(usuarioService.registraAvaliacaoUsuario(dto, reputacaoUsuario.getIdAvaliacao()))
    	.thenReturn(reputacaoUsuario);
    	
    	ReputacaoUsuario reputDto = usuarioService.registraAvaliacaoUsuario(dto, dto.getIdAvaliador());
    	
    	assertNull(reputDto);
    	
    }
    
    @Test
     void  TestListaUsuarios() throws Exception {
    	Mockito.when(usuarioService.listaTodosUsuarios()).thenReturn(usuarios);
    	Mockito.when(usuarioRepository.findByIdUsuario(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));
    	
    	List<Usuario> users = usuarioService.listaTodosUsuarios();
    	assertTrue(users.isEmpty());
    }
    
    @Test
    void  TestGetAvaliacaoUsuario() throws Exception {
   	 Mockito.when(resumoReputacaoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()))
   	 .thenReturn(Optional.of(resumoReputacaoUsuario));
   	 
   	AvaliacaoType avaliacao = usuarioService.getAvaliacaoUsuario(usuario.getIdUsuario());
	assertNull(avaliacao);
   	 
   }
    
    @Test
    void TestCategoriaDoador() {
    	double nota = 4;
    	String categoria = CategoriaDoadorEnum.NONE.getNome();

		if (nota >= CategoriaDoadorEnum.BRONZE.getMin())
			categoria = CategoriaDoadorEnum.BRONZE.getNome();

		if (nota >= CategoriaDoadorEnum.PRATA.getMin())
			categoria = CategoriaDoadorEnum.PRATA.getNome();

		if (nota >= CategoriaDoadorEnum.OURO.getMin())
			categoria = CategoriaDoadorEnum.OURO.getNome();

		if (nota >= CategoriaDoadorEnum.PLATINA.getMin())
			categoria = CategoriaDoadorEnum.PLATINA.getNome();

		if (nota >= CategoriaDoadorEnum.DIAMANTE.getMin())
			categoria = CategoriaDoadorEnum.DIAMANTE.getNome();

		if (nota > 5)
			categoria = CategoriaDoadorEnum.NONE.getNome();
		
		assertNotNull(categoria);
    }
    
   
      
    
}
