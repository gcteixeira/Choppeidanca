package choppeidanca.modelo;

import java.io.Serializable;

public class Cidade implements Serializable {

	private int id;
	private String nome;
	private Uf uf;
	
	public Cidade() {
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
	public Uf getUf() {
		return uf;
	}
	public void setUf(Uf uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
