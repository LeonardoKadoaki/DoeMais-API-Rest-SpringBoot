package com.doemais.api.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doemais.api.dto.PosicaoRankingType;
import com.doemais.api.models.EstatisticasUsuario;
import com.doemais.api.repository.EstatisticasUsuarioRepository;

@Service
public class RankingService {

	Logger logger = LoggerFactory.getLogger(RankingService.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	EstatisticasUsuarioRepository eur;
	
	public List<PosicaoRankingType> consultarRanking(){
		logger.info("[RankingService] INICIO: consultar ranking");
		return converterParaRanking(eur.consultarRanking());
	}

	private List<PosicaoRankingType> converterParaRanking(List<EstatisticasUsuario> listaOrdenada){
		List<PosicaoRankingType> ranking = new ArrayList<PosicaoRankingType>();
		
		long posicao = 1;
		
		EstatisticasUsuario estatisticasAux = new EstatisticasUsuario();
		
		for (int i = 0; i < listaOrdenada.size(); i++) { 
			
			if (listaOrdenada.get(i).getTotalMoedas() == estatisticasAux.getTotalMoedas()) {
				posicao--;
			}

			estatisticasAux = listaOrdenada.get(i);
			ranking.add(new PosicaoRankingType(posicao, listaOrdenada.get(i).getUsuario().getNome(), listaOrdenada.get(i).getTotalMoedas()));
			posicao++;
		}
		
		return ranking;
	}
	
}