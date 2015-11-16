package choppeidanca.modelo;

import java.io.Serializable;
import java.util.Date;

public class Contas implements Serializable {

	private int id;
	private Movimento movimento;
	private FormaPagamento forma;
	private CondicaoPagamento condicao;
	private Date data_emissao; //Data atual
	private Date data_fatura;
	private double valor_parcela;
	private double valor_total;
	private int tipo; //se 1 entrada, se 0 saida
	private int status; //se 0 em debito, se 1 quitado
	
	public Contas() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Movimento getMovimento() {
		return movimento;
	}
	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}
	public FormaPagamento getForma() {
		return forma;
	}
	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}
	public CondicaoPagamento getCondicao() {
		return condicao;
	}
	public void setCondicao(CondicaoPagamento condicao) {
		this.condicao = condicao;
	}
	public Date getData_emissao() {
		return data_emissao;
	}
	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}
	public Date getData_fatura() {
		return data_fatura;
	}
	public void setData_fatura(Date data_fatura) {
		this.data_fatura = data_fatura;
	}
	public double getValor_parcela() {
		return valor_parcela;
	}
	public void setValor_parcela(double valor_parcela) {
		this.valor_parcela = valor_parcela;
	}
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
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
		return "Contas [id=" + id + ", movimento=" + movimento + ", forma="
				+ forma + ", condicao=" + condicao + ", data_emissao="
				+ data_emissao + ", data_fatura=" + data_fatura
				+ ", valor_parcela=" + valor_parcela + ", valor_total="
				+ valor_total + ", tipo=" + tipo + ", status=" + status + "]";
	}
	
	
	
}
