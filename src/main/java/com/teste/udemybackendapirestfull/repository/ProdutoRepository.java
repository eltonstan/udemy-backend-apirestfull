package com.teste.udemybackendapirestfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.udemybackendapirestfull.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
