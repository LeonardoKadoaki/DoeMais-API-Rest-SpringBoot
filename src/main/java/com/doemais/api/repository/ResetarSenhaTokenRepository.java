package com.doemais.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doemais.api.models.ResetarSenhaToken;

@Repository
public interface ResetarSenhaTokenRepository extends JpaRepository<ResetarSenhaToken, Long> {

    ResetarSenhaToken findByToken(String token);
    
    @Transactional
	@Modifying
	@Query(value="truncate table redefine_senha;", nativeQuery=true)
	void deleteTokens();
  

}
