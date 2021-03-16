package com.doemais.api.service;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.doemais.api.dto.CadastroDto;
import com.doemais.api.models.Auth;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.AuthRepository;
import com.doemais.api.repository.UsuarioRepository;
import com.doemais.api.services.AuthService;
import com.doemais.api.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioServiceTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
    private	AuthRepository authRepository;
	
	@Autowired
	private AuthService authService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Usuario usuario;
    
    @Autowired
    private UsuarioService service;
    
    
    @Test
    public void TestAutenticaUsuario() throws Exception{
            CadastroDto cadastroDto = new CadastroDto();
            
        cadastroDto.setCpf("5454");
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date datas = formato.parse("11/02/2021");
		Date datas2 = formato.parse("30/06/2021");
        cadastroDto.setDataCadastro(LocalDate.of(1998, Month.DECEMBER, 22));
        cadastroDto.setDataNascimento(LocalDate.of(2021, Month.DECEMBER, 22));
        cadastroDto.setGenero("M");
        cadastroDto.setNome("Teste");
        cadastroDto.setSobre("Testando");
        cadastroDto.setUserName("Test");
        cadastroDto.setNumeroCelular("5445");
        
        cadastroDto.setEmail("guihgh@gmail.com");
        cadastroDto.setSenha("12345");
        
        usuario = new Usuario(cadastroDto.getNome(), cadastroDto.getUserName(), cadastroDto.getCpf(),
				cadastroDto.getDataNascimento(), cadastroDto.getDataCadastro(), cadastroDto.getNumeroCelular(),
				cadastroDto.getGenero(), cadastroDto.getSobre());
        
        Usuario user = usuarioRepository.save(usuario);
        
        Auth auth = new Auth(user, 
        		cadastroDto.getEmail(),
        		passwordEncoder.encode(cadastroDto.getSenha()));
        
        authRepository.save(auth);        
        
	
    }
    
    
    @Test
    public void  TestBuscaUsuarioId() throws Exception {
    	
    	usuarioRepository.findById(1);
    }
    
    
    @Test
    public void  TestDeletaUsuario() throws Exception {
    	Usuario usuario = new Usuario();
    	usuario.setIdUsuario(3);
    	usuarioRepository.findById(usuario.getIdUsuario());
    	authRepository.deleteByUsuarioIdUsuario(usuario.getIdUsuario());
    	usuarioRepository.deleteById(usuario.getIdUsuario());
    }
    
   
    
    
    
	
	
	
	
	
}
