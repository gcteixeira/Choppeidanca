package choppeidanca.modelo;

import java.io.Serializable;
import java.util.Date;

public class Movimento implements Serializable {

	private int id;
	private Pessoa pessoa;
	private FormaPagamento formaPagamento;
	private CondicaoPagamento condicaoPagamento;
	private Date data_venda;
	private int operacao; //se 0 compra, se 1 venda
	
	public Movimento(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Date getData_venda() {
		return data_venda;
	}

	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	@Override
	public String toString() {
		return "Movimento [id=" + id + ", pessoa=" + pessoa
				+ ", formaPagamento=" + formaPagamento + ", condicaoPagamento="
				+ condicaoPagamento + ", data_venda=" + data_venda
				+ ", operacao=" + operacao + "]";
	}
	
	

	
}
