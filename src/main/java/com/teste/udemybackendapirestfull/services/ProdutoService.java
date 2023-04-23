package com.teste.udemybackendapirestfull.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.udemybackendapirestfull.model.Produto;
import com.teste.udemybackendapirestfull.model.exception.ResourceNotFoundException;
import com.teste.udemybackendapirestfull.repository.ProdutoRepository;
import com.teste.udemybackendapirestfull.shared.ProdutoDTO;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/**
	 * Metodo para retornar uma lista de produtos
	 * @return Lista de Produtos
	 */
	public List<ProdutoDTO> obterTodos() {
		 
		List<Produto> produtos = produtoRepository.findAll();
		 
		return produtos.stream()
				.map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
				.collect(Collectors.toList());
		 
	}
	
	/**
	 * Metodo que retorna o produto encontrado por id
	 * @param id do produto que será localizado
	 * @return Retorna um produto caso seja encontrado
	 */
	public Optional<ProdutoDTO> obterPorId(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
		}
		ProdutoDTO produtoDto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
		return Optional.of(produtoDto);
	}
	
	/**
	 * Metodo para adicionar produtos na lista.
	 * @param produto que será adicionado.
	 * @return Retorna o produto que adicionado na lista.
	 */
	public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
		
		produtoDto.setId(null);
		
		ModelMapper mapper = new ModelMapper();
		Produto produto = mapper.map(produtoDto, Produto.class);
		
		produto = produtoRepository.save(produto);
		produtoDto.setId(produto.getId());
		return produtoDto;
		
	}
	
	/**
	 * Método para deletar o produto por id.
	 * @param id do produto a ser deletado
	 */
	public void deletar(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " + id + " Produto não existe");
		}
		produtoRepository.deleteById(id);
	}
	
	/**
	 * Método para atualizar o produto na lista
	 * @param produto que será atualizado
	 * @return Retorna o produto após atualizar a lista
	 */
	public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
		
		Optional<Produto> optionalProduto = produtoRepository.findById(id);
		if (optionalProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível atualizar o produto com o id: " + id + " Produto não existe");
		}
		
		produtoDto.setId(id);
		ModelMapper mapper = new ModelMapper();
		Produto produto = mapper.map(produtoDto, Produto.class);
		produtoRepository.save(produto);
		return produtoDto;
	}

}
