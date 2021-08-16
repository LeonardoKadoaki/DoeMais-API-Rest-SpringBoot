package com.doemais.api.services;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.doemais.api.dto.RecuperarSenhaDto;
import com.doemais.api.dto.ResetarSenhaDto;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.EntidadeSemValorException;
import com.doemais.api.models.Auth;
import com.doemais.api.models.ResetarSenhaToken;
import com.doemais.api.repository.AuthRepository;
import com.doemais.api.repository.ResetarSenhaTokenRepository;
import com.doemais.api.repository.UsuarioRepository;

@Service
public class RecuperarSenhaService {

	Logger logger = LoggerFactory.getLogger(RecuperarSenhaService.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AuthRepository authRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ResetarSenhaTokenRepository resetToken;

	@Autowired
	EmailService emailService;

	@Autowired
	SpringTemplateEngine templateEngine;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private JavaMailSender javaMailSender;
	
	@Autowired
	public RecuperarSenhaService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public RecuperarSenhaDto enviaEmailRecuperaSenha(RecuperarSenhaDto passForgotDto)
			throws MailException, EntidadeNaoEncontradaException, EntidadeSemValorException, MessagingException {

		Auth auth = authRepository.findByEmail(passForgotDto.getEmail());
		validaInformacoesSenha(passForgotDto);

		ResetarSenhaToken token = new ResetarSenhaToken();
		token.setToken(UUID.randomUUID().toString());
		token.setAuth(auth);
		token.setExpiryDate(60);
		resetToken.save(token);

		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/api/recuperar-senha/redefinir-senha?token=" + token.getToken();
		String link = response.encodeURL(url);

		Map<String, Object> model = new HashMap<>();
		model.put("URL", link);
		model.put("email", auth.getUsername());

		Context context = new Context();
		context.setVariables(model);

		String html = templateEngine.process("/template-email", context);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		
		
		helper.setTo(auth.getEmail());
		helper.setSubject("Doe+ - Redefinição de senha");
		helper.setText(html, true);
		javaMailSender.send(message);

		return passForgotDto;
	}

	public ResetarSenhaToken retornaPaginaRedefineSenha(String token) throws EntidadeNaoEncontradaException {
		ResetarSenhaToken resetarSenhaToken = resetToken.findByToken(token);
		if (resetarSenhaToken.isExpired()) {
			throw new EntidadeNaoEncontradaException(String.format("O Token está expirado %s", resetarSenhaToken));
		} else if (resetarSenhaToken.getToken().isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("O Token esta vazio %s", resetarSenhaToken));
		}

		return resetarSenhaToken;

	}

	@Transactional
	public ResetarSenhaDto RedefineSenha(ResetarSenhaDto reset)
			throws EntidadeNaoEncontradaException, EntidadeSemValorException {
		ResetarSenhaToken token = resetToken.findByToken(reset.getToken());
		if (token.isExpired()) {
			throw new EntidadeNaoEncontradaException(String.format("O token está expirado %s", token));
		} else if (reset.getPassword().isEmpty() || reset.getPassword() == null) {
			throw new EntidadeSemValorException(String.format("A senha é nula ou vazia %s", reset));
		} else if (reset.getConfirmPassword().isEmpty() || reset.getPassword() == null) {
			throw new EntidadeSemValorException(String.format("A confirmação de senha é nula ou vazia %s", reset));
		}

		Auth auth = token.getAuth();
		String updatedPassword = passwordEncoder.encode(auth.getSenha());
		authRepository.updatePassword(updatedPassword, auth.getIdAuth());
		resetToken.deleteTokens();

		return reset;
	}

	protected void validaInformacoesSenha(RecuperarSenhaDto recuperarSenhaDto)
			throws EntidadeNaoEncontradaException, EntidadeSemValorException {
		Optional<Auth> recupera = Optional.ofNullable(authRepository.findByEmail(recuperarSenhaDto.getEmail()));
		if (recuperarSenhaDto.getEmail().trim().isEmpty() || recuperarSenhaDto.getEmail() == null) {
			throw new EntidadeSemValorException(
					String.format("O Email não pode ser nulo ou vazio %s", recuperarSenhaDto));
		}
		if (!recupera.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Email não encontrado %s", recupera));
		}

	}

}
