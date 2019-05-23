package com.igti.produtos.vo;

import lombok.Data;

@Data
public class ProdutoRequest {
	private String nome;
	private String descricao;
	private double quantidade;
	private double valor;
}
