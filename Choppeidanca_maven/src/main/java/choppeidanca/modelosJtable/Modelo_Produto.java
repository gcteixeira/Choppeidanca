package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.Produto;
import choppeidanca.persistencia.ProdutoDAO;


public class Modelo_Produto extends AbstractTableModel{


	private List<Produto> produtos = new ArrayList<>();
	
	private Produto produto;
	
	//Getters and Setters
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	

	/**
	 * Metodos implementados de AbstractTableModel 
	 */

	@Override
	public int getRowCount() {
		return produtos.size();
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:
			return produtos.get(rowIndex).getCodigo();
		case 1:
			return produtos.get(rowIndex).getDescricao();
		case 2:
			return produtos.get(rowIndex).getCategoria();
		case 3:
			return getStringStatus(produtos.get(rowIndex).getStatus());
		case 4:
			return produtos.get(rowIndex).getQtdEstoque();
		default:
			return "Erro";
			
		}
	}

	@Override
	public String getColumnName(int i) {
		switch (i) {
		case 0:
			return "Id";
		case 1:
			return "Descrição";
		case 2: 
			return "Categoria";
		case 3:
			return "Status";
		case 4:
			return "Quantidade em Estoque";
		default:
			return "ERRO";
		}
	}
	
	public Produto getSelectedObject(int row) {  
	     return produtos.get(row);  
	} 

	public String getStringStatus(boolean status) {
		String statusString = null;
		if (status == true) {
			statusString = "Ativo";
		} else if (status == false) {
			statusString = "Inativo";
		}
		
		return statusString;
	}
}
