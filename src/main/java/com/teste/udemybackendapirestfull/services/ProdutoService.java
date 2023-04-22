package com.teste.udemybackendapirestfull.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.udemybackendapirestfull.model.Produto;
import com.teste.udemybackendapirestfull.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/**
	 * Metodo para retornar uma lista de produtos
	 * @return Lista de Produtos
	 */
	public List<Produto> obterTodos() {
		return produtoRepository.obterTodos();
	}
	
	/**
	 * Metodo que retorna o produto encontrado por id
	 * @param id do produto que será localizado
	 * @return Retorna um produto caso seja encontrado
	 */
	public Optional<Produto> obterPorId(Integer id) {
		return produtoRepository.obterPorId(id);
	}
	
	/**
	 * Metodo para adicionar produtos na lista.
	 * @param produto que será adicionado.
	 * @return Retorna o produto que adicionado na lista.
	 */
	public Produto adicionar(Produto produto) {
		return produtoRepository.adicionar(produto);
	}
	
	/**
	 * Método para deletar o produto por id.
	 * @param id do produto a ser deletado
	 */
	public void deletar(Integer id) {
		produtoRepository.deletar(id);
	}
	
	/**
	 * Método para atualizar o produto na lista
	 * @param produto que será atualizado
	 * @return Retorna o produto após atualizar a lista
	 */
	public Produto atualizar(Integer id, Produto produto) {
		produto.setId(id);
		return produtoRepository.atualizar(produto);
	}

}
