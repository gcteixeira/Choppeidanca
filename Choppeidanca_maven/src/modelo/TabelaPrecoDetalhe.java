package choppeidanca.modelo;

public class TabelaPrecoDetalhe {

	private int id;
	private TabelaPreco tabelaPreco;
	private Produto produto;
	private double valor;
	private Object quantidade = "1";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}
	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		this.tabelaPreco = tabelaPreco;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Object getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Object quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		return "TabelaPrecoDetalhe [id=" + id + ", tabelaPreco=" + tabelaPreco
				+ ", produto=" + produto + ", valor=" + valor + "]";
	}
	
	
}
