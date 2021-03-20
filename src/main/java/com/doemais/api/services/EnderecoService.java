package com.doemais.api.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doemais.api.dto.EnderecoDto;
import com.doemais.api.dto.EnderecoType;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Endereco;
import com.doemais.api.models.Usuario;
import com.doemais.api.repository.EnderecoRepository;
import com.google.gson.Gson;

@Service
public class EnderecoService {

	Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	UsuarioService usuarioService;

	public EnderecoDto consultarEnderecoUsuario(long idUsuario) throws EntidadeNaoEncontradaException {
		Endereco endereco = buscarEnderecoPorIdUsuario(idUsuario);

		EnderecoDto enderecoFormatado = new EnderecoDto(endereco.getUsuario().getIdUsuario(), endereco.getIdEndereco(),
				endereco.getLogradouro(), endereco.getNumero(), endereco.getCep(), endereco.getComplemento(),
				endereco.getBairro(), endereco.getUf(), endereco.getLocalidade());

		return enderecoFormatado;
	}

	public void deletarEndereco(Usuario usuario) throws EntidadeNaoEncontradaException {
		buscarEnderecoPorIdUsuario(usuario.getIdUsuario());
		enderecoRepository.deleteByUsuarioIdUsuario(usuario.getIdUsuario());
	}

	public Endereco buscarEnderecoPorIdUsuario(long idUsuario) throws EntidadeNaoEncontradaException {
		return enderecoRepository.findByUsuarioIdUsuario(idUsuario)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("O usuário com id %d não possui endereço cadastrado", idUsuario)));
	}

	public Endereco cadastrarEndereco(Endereco endereco) throws EntidadeNaoEncontradaException, ConflictException {
		validarInformacoesEndereco(endereco);
		return enderecoRepository.save(endereco);
	}

	protected void validarInformacoesEndereco(Endereco endereco) throws EntidadeNaoEncontradaException, ConflictException {
		
		Optional<Endereco> endeaux = enderecoRepository.findByUsuarioIdUsuario(endereco.getUsuario().getIdUsuario());
		
		if(endeaux.isPresent()) {
			throw new ConflictException("O usuário já possui um endereço cadastrado");
		}
		
		if (endereco.getBairro().trim().isEmpty() || endereco.getBairro() == null) {
			throw new NullPointerException("O bairro não pode ser nulo");
		}

		if (endereco.getCep().trim().isEmpty() || endereco.getCep() == null) {
			throw new NullPointerException("O cep não pode ser nulo");
		}

		if (endereco.getLocalidade().trim().isEmpty() || endereco.getLocalidade() == null) {
			throw new NullPointerException("A cidade não pode ser nula");
		}

		if (endereco.getLogradouro().trim().isEmpty() || endereco.getLogradouro() == null) {
			throw new NullPointerException("O logradouro não pode ser nulo");
		}

		if (endereco.getNumero() == 0) {
			throw new NullPointerException("O número da moradia não pode ser nulo");
		}

		if (endereco.getUf().trim().isEmpty() || endereco.getUf() == null) {
			throw new NullPointerException("A UF não pode ser nulo");
		}

		if (endereco.getUsuario() == null || endereco.getUsuario().getIdUsuario() == 0) {
			throw new NullPointerException("Nenhum usuario atrelado ao endereco não pode ser nulo");
		}

		usuarioService.buscarUsuarioPorId(endereco.getUsuario().getIdUsuario());
	}

	public EnderecoType pesquisarEndereco(String cep) throws Exception {
		URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
		URLConnection connection = url.openConnection();

		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		String cepx = "";
		StringBuilder jsonCep = new StringBuilder();
		while ((cepx = br.readLine()) != null) {
			jsonCep.append(cepx);
		}

		EnderecoType endereco = new Gson().fromJson(jsonCep.toString(), EnderecoType.class);

		return endereco;
	}
}
