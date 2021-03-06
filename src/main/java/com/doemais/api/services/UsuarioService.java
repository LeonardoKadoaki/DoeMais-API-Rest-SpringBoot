package com.doemais.api.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.doemais.api.dto.AvaliacaoType;
import com.doemais.api.enums.CategoriaDoadorEnum;
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
		return new Usuario(cadastro.getNome(), cadastro.getUserName(), cadastro.getCpf(),
				cadastro.getDataNascimento(), LocalDate.now(), cadastro.getNumeroCelular(),
				cadastro.getGenero(), cadastro.getSobre());
	}

	protected Auth formataAuth(Usuario usuario, CadastroDto cadastro) {
		return new Auth(usuario, cadastro.getEmail(), passwordEncoder.encode(cadastro.getSenha()));
	}

	protected Usuario salvaUsuario(CadastroDto cadastro) {

		Usuario usuario = usuarioRepository.save(formataUsuario(cadastro));
		authRepository.save(formataAuth(usuario, cadastro));

		return usuario;
	}

	protected void verificaInformacoesValidas(CadastroDto cadastro) throws ConflictException {

		Optional<Usuario> retornoCpf = usuarioRepository.findByCpf(cadastro.getCpf());
		Optional<Auth> retornoEmail = Optional.ofNullable((authRepository.findByEmail(cadastro.getEmail())));
		Optional<Usuario> retornoCelular = usuarioRepository.findByNumeroCelular(cadastro.getNumeroCelular());
	    //Optional<Usuario> retornoRg = usuarioRepository.findByRg(cadastro.getRg());

		if (cadastro.getDataNascimento().isAfter(LocalDate.now())) {
			throw new ConflictException("A data de nascimento n??o pode ser maior que a data de hoje");
		}

		if (retornoCpf.isPresent() && retornoEmail.isPresent() && retornoCelular.isPresent()) {
			throw new ConflictException(String.format("CPF %s, email %s e numero de celular %s j?? cadastrados",
					cadastro.getCpf(), cadastro.getEmail(), cadastro.getNumeroCelular()));
		}

		else if (retornoEmail.isPresent() && retornoCelular.isPresent()) {
			throw new ConflictException(String.format("email %s e numero de celular %s j?? cadastrados",
					cadastro.getEmail(), cadastro.getNumeroCelular()));
		}

		else if (retornoCpf.isPresent() && retornoCelular.isPresent()) {
			throw new ConflictException(String.format("CPF %s e numero de celular %s j?? cadastrados", cadastro.getCpf(),
					cadastro.getNumeroCelular()));
		}

		else if (retornoCpf.isPresent() && retornoEmail.isPresent()) {
			throw new ConflictException(
					String.format("CPF %s e email %s j?? cadastrados", cadastro.getCpf(), cadastro.getEmail()));
		}

		else if (retornoCpf.isPresent()) {
			throw new ConflictException(String.format("J?? existe um cadastro com o CPF %s", cadastro.getCpf()));
		}

		else if (retornoEmail.isPresent()) {
			throw new ConflictException(String.format("J?? existe um cadastro com o email %s", cadastro.getEmail()));
		}

		else if (retornoCelular.isPresent()) {
			throw new ConflictException(
					String.format("J?? existe um cadastro com o numero de celular %s", cadastro.getNumeroCelular()));
		}
	}

	public List<Usuario> listaTodosUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarUsuarioPorId(long idUsuario) throws EntidadeNaoEncontradaException {
		Usuario u = usuarioRepository.findByIdUsuario(idUsuario).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Usu??rio com id %d n??o encontrado", idUsuario)));

		AvaliacaoType av = null;
		try {
			av = this.getAvaliacaoUsuario(idUsuario);
		} catch (EntidadeNaoEncontradaException e) {
			logger.info(String.format("Usu??rio com id %d ainda n??o foi avaliado", idUsuario));
		}

		u.setCategoria(av == null ? CategoriaDoadorEnum.NONE.getNome() : av.getCategoria());
		return u;
	}

	public void deletarUsuario(long idUsuario) throws EntidadeNaoEncontradaException {
		Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Usu??rio com id %d n??o existe ou j?? exclu??do", idUsuario)));
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

	public AvaliacaoType getAvaliacaoUsuario(long idUsuario)
			throws EntidadeNaoEncontradaException {

		Optional<ResumoReputacaoUsuario> rru = resumoReputacaoRepository.findByUsuarioIdUsuario(idUsuario);
		if (rru.isPresent()) {
			int numAvaliacoes = reputacaoRepository.totalAvaliacoesByIdUsuario(idUsuario);

			AvaliacaoType av = new AvaliacaoType();
			av.setAvaliacao(rru.get().getNotaAvaliacao());
			av.setNumeroDeAvaliacoes(numAvaliacoes);

			/* Categoria e' um atributo derivado, logo, nao
			   precisa adicionar uma coluna aa parte na tabela */
			av.setCategoria(numAvaliacoes >= 5 ?
					getCategoriaUsuario(rru.get().getNotaAvaliacao()) :
					CategoriaDoadorEnum.NONE.getNome());

			return av;
		} else {
			throw new EntidadeNaoEncontradaException(String.format("N??o h?? avalia????o para o usu??rio com id %d", idUsuario));
		}
	}

	public ReputacaoUsuario registraAvaliacaoUsuario(ReputacaoDto reputacao, long idUsuario)
			throws EntidadeNaoEncontradaException, ConflictException {

		long idAvaliador = reputacao.getIdAvaliador();
		Usuario u = buscarUsuarioPorId(idUsuario);
		buscarUsuarioPorId(idAvaliador);

		if (idAvaliador == idUsuario) {
			throw new ConflictException("O usu??rio n??o pode se autoavaliar!");
		}

		if (reputacaoRepository.verificaAvaliacao(idUsuario, idAvaliador) > 0) {
			throw new ConflictException(
					String.format("O usu??rio id %d j?? foi avaliado pelo usu??rio id %d", idUsuario, idAvaliador));
		}

		ReputacaoUsuario ru = new ReputacaoUsuario();
		ru.setNotaAvaliacao(reputacao.getNotaAvaliacao());
		ru.setDataRegistro(LocalDate.now());
		ru.setPapelUsuario(reputacao.getPapelUsuario());
		ru.setUsuario(u);
		ru.setIdAvaliador(idAvaliador);

		ReputacaoUsuario ret = reputacaoRepository.save(ru);
		Optional<ResumoReputacaoUsuario> rru = resumoReputacaoRepository.findByUsuarioIdUsuario(idUsuario);

		if (rru.isPresent()) {
			if (!reputacao.getDescricaoResumo().isEmpty())
				rru.get().setDescricaoResumo(reputacao.getDescricaoResumo());

			rru.get().setNotaAvaliacao(reputacaoRepository.mediaReputacaoByIdUsuario(idUsuario));
			rru.get().setDataAtualizacaoRegistro(new Date());
			resumoReputacaoRepository.save(rru.get());
		} else {
			ResumoReputacaoUsuario rrun = new ResumoReputacaoUsuario();
			if (!reputacao.getDescricaoResumo().isEmpty())
				rrun.setDescricaoResumo(reputacao.getDescricaoResumo());

			rrun.setNotaAvaliacao(reputacaoRepository.mediaReputacaoByIdUsuario(idUsuario));
			rrun.setDataAtualizacaoRegistro(new Date());
			rrun.setUsuario(u);
			resumoReputacaoRepository.save(rrun);
		}

		return ret;
	}

	private String getCategoriaUsuario(double nota) {

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

		return categoria;
	}
}
