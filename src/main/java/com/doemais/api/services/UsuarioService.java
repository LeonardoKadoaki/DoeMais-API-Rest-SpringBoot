package com.doemais.api.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doemais.api.dto.CadastroDto;
import com.doemais.api.dto.ReputacaoDto;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Auth;
import com.doemais.api.models.ReputacaoUsuario;
import com.doemais.api.models.ResumoReputacaoUsuario;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.AuthRepository;
import com.doemais.api.repository.ReputacaoRepository;
import com.doemais.api.repository.ResumoReputacaoRepository;
import com.doemais.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

	Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AuthRepository authRepository;

	@Autowired
	ReputacaoRepository reputacaoRepository;

	@Autowired
	ResumoReputacaoRepository resumoReputacaoRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Usuario cadastrarUsuario(CadastroDto cadastro) throws ConflictException {

		verificaInformacoesValidas(cadastro);

		return salvaUsuario(cadastro);
	}

	protected Usuario formataUsuario(CadastroDto cadastro) {

		Usuario usuario = new Usuario(cadastro.getNome(), cadastro.getUserName(), cadastro.getCpf(),
				cadastro.getDataNascimento(), LocalDate.now(), cadastro.getNumeroCelular(),
				cadastro.getGenero(), cadastro.getSobre());

		return usuario;
	}

	protected Auth formataAuth(Usuario usuario, CadastroDto cadastro) {

		Auth auth = new Auth(usuario, cadastro.getEmail(), passwordEncoder.encode(cadastro.getSenha()));

		return auth;
	}

	protected Usuario salvaUsuario(CadastroDto cadastro) {

		Usuario usuario = usuarioRepository.save(formataUsuario(cadastro));
		authRepository.save(formataAuth(usuario, cadastro));

		return usuario;
	}

	protected void verificaInformacoesValidas(CadastroDto cadastro) throws ConflictException {

		Optional<Usuario> retornoCpf = usuarioRepository.findByCpf(cadastro.getCpf());
		Optional<Auth> retornoEmail = authRepository.findByEmail(cadastro.getEmail());
		Optional<Usuario> retornoCelular = usuarioRepository.findByNumeroCelular(cadastro.getNumeroCelular());
//		Optional<Usuario> retornoRg = usuarioRepository.findByRg(cadastro.getRg());

		if (cadastro.getDataNascimento().isAfter(LocalDate.now())) {
			throw new ConflictException("A data de nascimento não pode ser maior que a data de hoje");
		}

		if (retornoCpf.isPresent() && retornoEmail.isPresent() && retornoCelular.isPresent()) {
			throw new ConflictException(String.format("CPF %s, email %s e numero de celular %s já cadastrados",
					cadastro.getCpf(), cadastro.getEmail(), cadastro.getNumeroCelular()));
		}

		else if (retornoEmail.isPresent() && retornoCelular.isPresent()) {
			throw new ConflictException(String.format("email %s e numero de celular %s já cadastrados",
					cadastro.getEmail(), cadastro.getNumeroCelular()));
		}

		else if (retornoCpf.isPresent() && retornoCelular.isPresent()) {
			throw new ConflictException(String.format("CPF %s e numero de celular %s já cadastrados", cadastro.getCpf(),
					cadastro.getNumeroCelular()));
		}

		else if (retornoCpf.isPresent() && retornoEmail.isPresent()) {
			throw new ConflictException(
					String.format("CPF %s e email %s já cadastrados", cadastro.getCpf(), cadastro.getEmail()));
		}

		else if (retornoCpf.isPresent()) {
			throw new ConflictException(String.format("Já existe um cadastro com o CPF %s", cadastro.getCpf()));
		}

		else if (retornoEmail.isPresent()) {
			throw new ConflictException(String.format("Já existe um cadastro com o email %s", cadastro.getEmail()));
		}

		else if (retornoCelular.isPresent()) {
			throw new ConflictException(
					String.format("Já existe um cadastro com o numero de celular %s", cadastro.getNumeroCelular()));
		}

	}

	public List<Usuario> listaTodosUsuarios() {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();

		return listaUsuarios;
	}

	public Usuario buscarUsuarioPorId(long idUsuario) throws EntidadeNaoEncontradaException {
		return usuarioRepository.findByIdUsuario(idUsuario).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Usuário com id %d não encontrado", idUsuario)));
	}

	public void deletarUsuario(long idUsuario) throws EntidadeNaoEncontradaException {
		Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Usuário com id %d não existe ou já excluído", idUsuario)));
		authRepository.deleteByUsuarioIdUsuario(idUsuario);
		usuarioRepository.delete(usuario);
	}

	public Usuario atualizarUsuario(Usuario usuario) throws ConflictException {
//		verificaInformacoesValidas(formataCadastroDto(usuario));
		return usuarioRepository.save(usuario);
	}

	protected CadastroDto formataCadastroDto(Usuario usuario) {
		CadastroDto cadastro = new CadastroDto();
		cadastro.setCpf(usuario.getCpf());
		cadastro.setDataNascimento(usuario.getDataNascimento());
		cadastro.setGenero(usuario.getGenero());
		cadastro.setNome(usuario.getNome());
		cadastro.setNumeroCelular(usuario.getNumeroCelular());
		cadastro.setUserName(usuario.getUserName());
		cadastro.setSobre(usuario.getSobre());

		return cadastro;
	}

	public double getAvaliacaoUsuario(long idUsuario) throws EntidadeNaoEncontradaException {

		Optional<ResumoReputacaoUsuario> rru = resumoReputacaoRepository.findByUsuarioIdUsuario(idUsuario);
		if (rru.isPresent())
			return rru.get().getNotaAvaliacao();
		else
			throw new EntidadeNaoEncontradaException(String.format("Usuário com id %d não encontrado", idUsuario));
	}

	public ReputacaoUsuario registraAvaliacaoUsuario(ReputacaoDto reputacao, long idUsuario)
			throws EntidadeNaoEncontradaException {

		Usuario u = buscarUsuarioPorId(idUsuario);
		ReputacaoUsuario ru = new ReputacaoUsuario();
		ru.setNotaAvaliacao(reputacao.getNotaAvaliacao());
		ru.setDataRegistro(LocalDate.now());
		ru.setPapelUsuario(reputacao.getPapelUsuario());
		ru.setUsuario(u);
		ReputacaoUsuario ret = reputacaoRepository.save(ru);

		Optional<ResumoReputacaoUsuario> rru = resumoReputacaoRepository.findByUsuarioIdUsuario(idUsuario);
		if (rru.isPresent()) {
			rru.get().setNotaAvaliacao(reputacaoRepository.mediaReputacaoByIdUsuario(u.getIdUsuario()));
			resumoReputacaoRepository.save(rru.get());
			return ret;
		}

		ResumoReputacaoUsuario rrun = new ResumoReputacaoUsuario();
		rrun.setDescricaoResumo("teste");
		rrun.setNotaAvaliacao(reputacaoRepository.mediaReputacaoByIdUsuario(u.getIdUsuario()));
		rrun.setDataAtualizacaoRegistro(new Date());
		rrun.setUsuario(u);
		resumoReputacaoRepository.save(rrun);

		return ret;
	}

}
