package com.doemais.api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.doemais.api.dto.EmailAddressType;
import com.doemais.api.dto.IdUsuarioType;
import com.doemais.api.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Email;
import com.doemais.api.repository.EmailRepository;
import com.doemais.api.repository.UsuarioRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public Email enviarEmail(Email email)
			throws MailException, EntidadeNaoEncontradaException {

		ArrayList<String> emails = validaInformacoesEmail(email);

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setBcc(emails.get(0));
		mail.setCc(emails.get(0));
		//mail.setFrom(emails.get(0)); // Modo ideal; requer servidor de email proprio...
		mail.setTo(emails.get(1));
		mail.setSubject(email.getAssunto());
		mail.setText(email.getCorpoEmail());

		javaMailSender.send(mail);
		email.setDataEnvioEmail(LocalDateTime.now());
		return this.emailRepository.save(email);
	}

//	public Email enviarEmailComAnexo(Email email)
//			throws MailException, MessagingException, EntidadeNaoEncontradaException {
//
//		validaInformacoesEmail(email);
//
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//		helper.setTo(email.getEmailDest());
//		helper.setSubject(email.getAssunto());
//		helper.setText(email.getCorpoEmail());
//		email.setDataEnvioEmail(LocalDateTime.now());
//
//		ClassPathResource classPathResource = new ClassPathResource("teste.pdf");
//		helper.addAttachment(classPathResource.getFilename(), classPathResource);
//
//		javaMailSender.send(mimeMessage);
//		return this.emailRepository.save(email);
//	}
	
	private ArrayList<String> validaInformacoesEmail(Email email)
			throws EntidadeNaoEncontradaException {

		// VERIFICA SE USUARIOS FORAM INFORMADOS
		if (email.getUsuarioRemet() == null || email.getUsuarioRemet().getIdUsuario() == 0) {
			throw new IllegalArgumentException("Usuário remetente não informado");
		}

		if (email.getUsuarioDest() == null || email.getUsuarioDest().getIdUsuario() == 0) {
			throw new IllegalArgumentException("Usuário destinatário não informado");
		}

		// VERIFICA SE USUARIOS EXISTEM
		usuarioRepository.findByIdUsuario(email.getUsuarioRemet().getIdUsuario())
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Usuário remetente com id %d não encontrado", email.getUsuarioRemet().getIdUsuario())));

		usuarioRepository.findByIdUsuario(email.getUsuarioDest().getIdUsuario())
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Usuário destinatário com id %d não encontrado", email.getUsuarioDest().getIdUsuario())));

		// OBTEM E VALIDA EMAIL DOS USUARIOS...
		String emailRemet = emailRepository.findEmailAddressByIdUsuario(email.getUsuarioRemet().getIdUsuario())
				.orElseThrow(() -> new IllegalArgumentException(String.format("Remetente %d não possui email cadastrado", email.getUsuarioRemet().getIdUsuario())));
		String emailDest = emailRepository.findEmailAddressByIdUsuario(email.getUsuarioDest().getIdUsuario())
				.orElseThrow(() -> new IllegalArgumentException(String.format("Destinatário %d não possui email cadastrado", email.getUsuarioDest().getIdUsuario())));

		if (emailRemet.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("Remetente %d não possui email cadastrado", email.getUsuarioRemet().getIdUsuario()));
		}

		if (emailDest.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("Destinatário %d não possui email cadastrado", email.getUsuarioDest().getIdUsuario()));
		}

		// VERIFICA SE ASSUNTO E CORPO FORAM INFORMADOS
		if (email.getAssunto() == null || email.getAssunto().trim().isEmpty()) {
			throw new IllegalArgumentException("O assunto do email não pode ser vazio");
		}

		if (email.getCorpoEmail() == null || email.getCorpoEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("O corpo do email não pode ser vazio");
		}

		// EM CASO DE SUCESSO, RETORNA EMAIL DOS USUARIOS ENVOLVIDOS
		return new ArrayList<>(Arrays.asList(emailRemet, emailDest));
	}


	public Email consultarEmail(long idEmail)
			throws EntidadeNaoEncontradaException {

		return emailRepository.findByIdEmail(idEmail)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Email %d não encontrado", idEmail)));
	}

	public List<Email> listarEmails()
			throws EntidadeNaoEncontradaException {

		return emailRepository.findAllEmails()
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum email encontrado"));
	}

	public List<Email> listarEmailsEnviados(long idUsuario)
			throws EntidadeNaoEncontradaException {

		Usuario u = usuarioRepository.findByIdUsuario(idUsuario)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Usuário %d não encontrado", idUsuario)));

		return emailRepository.findAllByIdUsuarioRemet(u)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Usuário %d não enviou nenhum email", idUsuario)));
	}

	public List<Email> listarEmailsRecebidos(long idUsuario)
			throws EntidadeNaoEncontradaException {

		Usuario u = usuarioRepository.findByIdUsuario(idUsuario)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Usuário %d não encontrado", idUsuario)));

		return emailRepository.findAllByIdUsuarioDest(u)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Usuário %d não recebeu nenhum email", idUsuario)));
	}

	public EmailAddressType consultarEnderecoEmail(long idUsuario)
			throws EntidadeNaoEncontradaException {

		usuarioRepository.findByIdUsuario(idUsuario)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Usuário com id %d não encontrado", idUsuario)));

		String email = emailRepository.findEmailAddressByIdUsuario(idUsuario)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Usuário %d não possui email cadastrado", idUsuario)));

		if (email.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("Usuário %d não possui email cadastrado", idUsuario));
		}

		EmailAddressType eat = new EmailAddressType();
		eat.setEmail(email);

		return eat;
	}

	public IdUsuarioType consultarIdUsuario(EmailAddressType email)
			throws EntidadeNaoEncontradaException {

		if (email.getEmail() == null || email.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("Email não informado");
		}

		Long id = emailRepository.findIdUsuarioByEmailAddress(email.getEmail())
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Nenhum usuário possui o email %s", email.getEmail())));

		IdUsuarioType it = new IdUsuarioType();
		it.setIdUsuario(id);

		return it;
	}
}
