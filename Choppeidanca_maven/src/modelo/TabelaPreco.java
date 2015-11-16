package choppeidanca.modelo;

import java.util.Date;

public class TabelaPreco {

	private int id;
	private String nome;
	private Date dataValidade;
	
	public TabelaPreco() {
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
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public String toString() {
		return "TabelaPreco [id=" + id + ", nome=" + nome + ", dataValidade="
				+ dataValidade + "]";
	}
	
	
	
}
