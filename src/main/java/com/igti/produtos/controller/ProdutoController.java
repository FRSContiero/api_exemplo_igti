package com.igti.produtos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.igti.produtos.entity.Produto;
import com.igti.produtos.repository.ProdutoRepository;
import com.igti.produtos.vo.ProdutoRequest;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody @Valid ProdutoRequest request) {
		Produto produto= new Produto();
		produto.setNome(request.getNome());
		produto.setDescricao(request.getDescricao());
		produto.setQuantidade(request.getQuantidade());
		produto.setValor(request.getValor());
		
		produtoRepository.save(produto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listar(){

		List<Produto> produto = produtoRepository.findAll();
			
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> obterById(@PathVariable String id){
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		
		if (!produtoOptional.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(produtoOptional.get(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable String id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		if(!produtoOptional.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		produtoRepository.delete(produtoOptional.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(path = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Produto> obterByNome(@PathVariable String nome){
		Optional<Produto> produtoOptional = produtoRepository.findByNome(nome);
		
		if (!produtoOptional.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(produtoOptional.get(), HttpStatus.OK);
	}
}
