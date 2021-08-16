package com.doemais.api.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.doemais.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.doemais.api.models.Email;

@Repository
@Transactional
public interface EmailRepository extends JpaRepository<Email, Long> {

	Optional<Email> findByIdEmail(long id);
	
	@Query(value = "select * from email", nativeQuery = true)
	Optional<List<Email>> findAllEmails();
	
	@Query(value = "select * from email where usuarioRemet = ?", nativeQuery = true)
	Optional<List<Email>> findAllByIdUsuarioRemet(Usuario u);

	@Query(value = "select * from email where usuarioDest = ?", nativeQuery = true)
	Optional<List<Email>> findAllByIdUsuarioDest(Usuario u);

	// TODO: Criar buscas textuais por assunto e corpo!

	@Query(value = "select email from auth where idUsuario = ?", nativeQuery = true)
	Optional<String> findEmailAddressByIdUsuario(long id);

	@Query(value = "select idUsuario from auth where email = ?", nativeQuery = true)
	Optional<Long> findIdUsuarioByEmailAddress(String email);

}
