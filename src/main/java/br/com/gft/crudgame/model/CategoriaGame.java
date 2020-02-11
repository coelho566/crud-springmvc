package br.com.gft.crudgame.model;

public enum CategoriaGame {
	
	ACAO("Ação"),
	AVENTURA("Aventura"),
	RPG("RPG"),
	CORRIDA("Corrida"),
	LUTA("Luta");
	
	private String descricao;
	
	CategoriaGame(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
