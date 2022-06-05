package com.github.jjunio01.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.jjunio01.dto.PedidoDTO;
import com.github.jjunio01.dto.form.cadastrar.PedidoDTOFormCadastrar;
import com.github.jjunio01.model.Carrinho;
import com.github.jjunio01.model.Cliente;
import com.github.jjunio01.model.Pedido;
import com.github.jjunio01.model.STATUS;
import com.github.jjunio01.repository.ClienteRepository;
import com.github.jjunio01.repository.PedidoRepository;

/**
 * @author JJunio
 *
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController
		implements RestControllerInterface<PedidoDTO, PedidoDTOFormCadastrar, PedidoDTOFormCadastrarAtualizar> {

	@Autowired
	private ClienteRepository repositoryCliente;

	@Autowired
	private CarrinhoController controllerCarrinho;
	
	
	@Autowired
	private PedidoRepository repositoryPedido;

	@Override
	public List<PedidoDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping
	public ResponseEntity<PedidoDTO> inserir(@Valid PedidoDTOFormCadastrar formPedido,
			UriComponentsBuilder uriBuilder) {
		Optional<Cliente> consultaClienteBD = repositoryCliente.findById(formPedido.getIdCliente());
		if (!consultaClienteBD.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Carrinho novoCarrinho = controllerCarrinho.salvar(formPedido.getProdutos());

		if (novoCarrinho == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Pedido novoPedido = new Pedido();
		novoPedido.setCarrinho(novoCarrinho);
		novoCarrinho.setPedido(novoPedido);
		novoPedido.setCliente(consultaClienteBD.get());
		novoPedido.setEndereco(consultaClienteBD.get().getEnderecos().get(0));
		novoPedido.setData(new Date());
		novoPedido.setStatus(STATUS.EM_APROVACÃO);
		
		repositoryPedido.saveAndFlush(novoPedido);
		
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(novoPedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDTO(novoPedido));
	}

	@Override
	public ResponseEntity<PedidoDTO> recuperarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PedidoDTO> atualizar(int id, @Valid PedidoDTOFormCadastrarAtualizar formG) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PedidoDTO> remover(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
