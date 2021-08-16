package com.doemais.api.controller;

import com.doemais.api.dto.MessageObjectType;
import com.doemais.api.dto.PedidoDto;
import com.doemais.api.dto.StatusPedidoDto;
import com.doemais.api.exception.ConflictException;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.exception.PedidoException;
import com.doemais.api.models.Pedido;
import com.doemais.api.services.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/pedido")
@Api(tags = "Pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@ApiOperation(value = "Retorna todos os pedidos")
	@GetMapping
	public List<Pedido> getAll() {
		return pedidoService.getAll();
	}

	@ApiOperation(value = "Consulta as informações de um pedido")
	@GetMapping("/{idPedido}")
	public Pedido getById(@PathVariable(value = "idPedido") long idPedido)
			throws EntidadeNaoEncontradaException {
		return pedidoService.getById(idPedido);
	}

	@ApiOperation(value = "Cadastra um pedido")
	@PostMapping
	public MessageObjectType create(@RequestBody @Valid Pedido pedido)
			throws EntidadeNaoEncontradaException, ConflictException, PedidoException {
		return pedidoService.create(pedido);
	}

	@ApiOperation(value = "Atualiza um pedido")
	@PutMapping("/{idPedido}")
	public Pedido update(@PathVariable(value = "idPedido") long idPedido,
						 @RequestBody @Valid PedidoDto pedido)
			throws EntidadeNaoEncontradaException {
		return pedidoService.update(idPedido, pedido);
	}

	@ApiOperation(value = "Deleta um pedido")
	@DeleteMapping("/{idPedido}")
	public void delete(@PathVariable(value = "idPedido") long idPedido)
			throws EntidadeNaoEncontradaException {
		pedidoService.delete(idPedido);
	}
	
	@ApiOperation(value = "Altera o status do pedido")
	@PostMapping("/alterar-status")
	public Pedido alterarStatus(@RequestBody @Valid StatusPedidoDto status)
			throws EntidadeNaoEncontradaException, ConflictException {
		return pedidoService.alterarStatus(status);
	}

	@ApiOperation(value = "Consulta o histórico de pedidos por idUsuario")
	@GetMapping("/usuario/{idUsuario}")
	public List<Pedido> getByIdUsuario(@PathVariable(value = "idUsuario") long idUsuario)
			throws EntidadeNaoEncontradaException {
		return pedidoService.getByIdUsuario(idUsuario);
	}
}
