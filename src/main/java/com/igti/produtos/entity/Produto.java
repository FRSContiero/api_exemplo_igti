package com.igti.produtos.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Produto {
	private String id;
	private String nome;
	private String descricao;
	private double quantidade;
	private double valor;
}
