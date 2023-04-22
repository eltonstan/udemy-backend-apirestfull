package com.teste.udemybackendapirestfull.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.udemybackendapirestfull.model.Produto;

@Repository
public class ProdutoRepository {

	private List<Produto> produtos = new ArrayList<Produto>();
	private Integer ultimoId = 0;

	/**
	 * Metodo para retornar uma lista de produtos
	 * @return Lista de Produtos
	 */
	public List<Produto> obterTodos() {
		return produtos;
	}

	
	/**
	 * Metodo que retorna o produto encontrado por id
	 * @param id do produto que será localizado
	 * @return Retorna um produto caso seja encontrado
	 */
	public Optional<Produto> obterPorId(Integer id) {
		return produtos
				.stream()
				.filter(produto -> produto.getId().equals(id))
				.findFirst();
	}
	
	/**
	 * Metodo para adicionar produtos na lista.
	 * @param produto que será adicionado.
	 * @return Retorna o produto que adicionado na lista.
	 */
	public Produto adicionar(Produto produto) {
		ultimoId++;
		produto.setId(ultimoId);
		produtos.add(produto);
		return produto;
	}
	
	/**
	 * Método para deletar o produto por id.
	 * @param id do produto a ser deletado
	 */
	public void deletar(Integer id) {
		produtos.removeIf(produto -> produto.getId().equals(id));
	}
	
	/**
	 * Método para atualizar o produto na lista
	 * @param produto que será atualizado
	 * @return Retorna o produto após atualizar a lista
	 */
	public Produto atualizar(Produto produto) {
		Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
		if (produtoEncontrado.isEmpty()) {
			throw new InputMismatchException("Produto não encontrado");
		}
		this.deletar(produto.getId());
		produtos.add(produto);
		return produto;
	}

}
