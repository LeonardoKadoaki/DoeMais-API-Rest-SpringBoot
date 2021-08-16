package com.doemais.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doemais.api.models.Medalha;


public interface MedalhaRepository extends JpaRepository<Medalha, Long> {

	Optional<Medalha> findByIdMedalha(long idMedalha);
}
