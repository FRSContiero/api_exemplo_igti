package com.igti.produtos.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.igti.produtos.entity.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String>{
	Optional<Produto> findByNome(String nome);
}
