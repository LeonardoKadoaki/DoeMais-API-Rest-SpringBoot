package com.doemais.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doemais.api.dto.PosicaoRankingType;
import com.doemais.api.services.RankingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/ranking")
@Api(tags = "Ranking")
public class RankingController {

	@Autowired
	RankingService rankingService;
	
	@ApiOperation(value = "Consulta o ranking")
	@GetMapping
	public List<PosicaoRankingType> consultarRanking() {
		return rankingService.consultarRanking();
	}
}
