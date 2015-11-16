package choppeidanca.modelo;

public class MovimentoProduto {

	private int id;
	private Movimento movimento;
	private TabelaPrecoDetalhe tabela_detalhe;
	private Object quantidade;
	private double valorund; // VALOR BRUTO
	private double desconto;
	private double acrescimo;
	private double valor_liquido; //que é um campo calculado do valorund - desconto + acrescimo;
	
	public MovimentoProduto() {
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

	public TabelaPrecoDetalhe getTabela_detalhe() {
		return tabela_detalhe;
	}

	public void setTabela_detalhe(TabelaPrecoDetalhe tabela_detalhe) {
		this.tabela_detalhe = tabela_detalhe;
	}
	public Object getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Object quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorund() {
		return valorund;
	}

	public void setValorund(double valorund) {
		this.valorund = valorund;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public double getValor_liquido() {
		return valor_liquido;
	}

	public void setValor_liquido(double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}

	@Override
	public String toString() {
		return "MovimentoProduto [id=" + id + ", movimento=" + movimento
				+ ", tabela_detalhe=" + tabela_detalhe + ", quantidade="
				+ quantidade + ", valorund=" + valorund + ", desconto="
				+ desconto + ", acrescimo=" + acrescimo + ", valor_liquido="
				+ valor_liquido + "]";
	}

	public double calculaSubtotal(double desconto, double acrescimo) {
		
		valor_liquido = valor_liquido - desconto + acrescimo;
		
		return valor_liquido;
	}

}
