package choppeidanca.modelo;

import java.io.Serializable;

public class CondicaoPagamento implements Serializable {

	private int id;
	private String nome;
	private int qtdVezes;

	public CondicaoPagamento() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdVezes() {
		return qtdVezes;
	}

	public void setQtdVezes(int qtdVezes) {
		this.qtdVezes = qtdVezes;
	}

	@Override
	public String toString() {
		return "CondicaoPagamento [	nome=" + nome + ", qtdVezes=" + qtdVezes
				+ "]";
	}

}
