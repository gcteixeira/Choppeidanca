package choppeidanca.modelo;

import java.io.Serializable;


public class Categoria implements Serializable {
	
	private int codigo;
	private String tipo;
	private int status;
	
	public Categoria(){
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return tipo;
	}
	
}
