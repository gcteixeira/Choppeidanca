package choppeidanca.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import choppeidanca.modelo.Categoria;
import choppeidanca.modelo.Cidade;
import choppeidanca.modelo.CondicaoPagamento;
import choppeidanca.modelo.Contas;
import choppeidanca.modelo.FormaPagamento;
import choppeidanca.modelo.Movimento;
import choppeidanca.modelo.MovimentoProduto;
import choppeidanca.modelo.Pais;
import choppeidanca.modelo.Pessoa;
import choppeidanca.modelo.Produto;
import choppeidanca.modelo.TabelaPreco;
import choppeidanca.modelo.TabelaPrecoDetalhe;
import choppeidanca.modelo.Uf;
import choppeidanca.persistencia.CategoriaDAO;
import choppeidanca.swing.dialogs.CadPais;

public class Singleton {

	private static Singleton instancia;
	
	public List<TabelaPrecoDetalhe> produtos_Venda = new ArrayList<>();
	public List<Pessoa> pessoas = new ArrayList<>();
	public List<TabelaPrecoDetalhe> tabelas = new ArrayList<>();
	
	public CondicaoPagamento cp = new CondicaoPagamento();
	public MovimentoProduto mp = new MovimentoProduto();
	public FormaPagamento fp = new FormaPagamento();
	public TabelaPreco tp = new TabelaPreco();
	public Categoria cat = new Categoria();
	public Movimento m = new Movimento();
	public Cidade cidade = new Cidade();
	public Contas c = new Contas();
	public Pessoa p = new Pessoa();
	public Pais pais = new Pais();
	public Uf uf = new Uf();
	public Produto produto = new Produto();
	
	public boolean statusBotao = true;

	public double valor_bruto = 0;
	public int quantidade = 1;
	public int ultimo_id_pessoa = pessoas.size() + 1;
	
	
	public Singleton() {
		// TODO Auto-generated constructor stub
	}
	
	public static synchronized Singleton getInstance(){
		if(instancia == null){
            instancia = new Singleton();
        }
        return instancia;		
	}
	
	
}
