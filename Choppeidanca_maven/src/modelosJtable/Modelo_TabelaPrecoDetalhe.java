package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.TabelaPreco;
import choppeidanca.modelo.TabelaPrecoDetalhe;
import choppeidanca.persistencia.TabelaPDetalheDAO;
import choppeidanca.utils.Singleton;

public class Modelo_TabelaPrecoDetalhe extends AbstractTableModel {

	private List<TabelaPrecoDetalhe> tabelas = new ArrayList<>();

	private List<Object> quantidades = new ArrayList<>();

	Object quantidade = "1";
	String quantidade2 = "1";


	public List<TabelaPrecoDetalhe> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaPrecoDetalhe> tabelas) {
		this.tabelas = tabelas;
	}

	String valoranterior = "";
	

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return tabelas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return tabelas.get(rowIndex).getId();
		case 1:
			return tabelas.get(rowIndex).getProduto().getDescricao();
		case 2:
			return tabelas.get(rowIndex).getTabelaPreco().getNome();
		case 3:
			return tabelas.get(rowIndex).getValor();
		case 4:
			return tabelas.get(rowIndex).getQuantidade();
		case 5:
			return tabelas.get(rowIndex).getProduto().getQtdEstoque();
		default:
			return "ERRO";
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Produto";
		case 2:
			return "Tabela Preço";
		case 3:
			return "Valor";
		case 4:
			return "No Carrinho";
		case 5:
			return "Disponivel";
		default:
			return "ERRO";
		}
	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean status = true;
		if (columnIndex == 4) {
			return status;
		} else {
			return !status;
		}
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {


		
		Object valor =  value;
		
		switch (columnIndex) {
		case 4:
			if (rowIndex != -1) {
				
			
				tabelas.get(rowIndex).setQuantidade(valor);
				
			}
			break;

		default:
			break;
		}
		
 
		  
		    super.setValueAt(value, rowIndex, columnIndex); 
	}

	public TabelaPrecoDetalhe getSelectedObject(int row) {
		return tabelas.get(row);
	}

	public List<TabelaPrecoDetalhe> getSelectedObject(int[] rows) {
		List<TabelaPrecoDetalhe> lista = new ArrayList<>();
		Singleton s = Singleton.getInstance();
		double valor = 0;
		for (int i = 0; i < rows.length; i++) {
			lista.add(tabelas.get(rows[i]));
			
			double vlr_unitario = lista.get(i).getValor();
			String qtd_str = "";
			Object qtd = lista.get(i).getQuantidade();
			if (qtd.equals(1)) {
				qtd_str =  Integer.toString((int) lista.get(i).getQuantidade());
				
			} else {
				qtd_str =  (String) lista.get(i).getQuantidade();
			}
			
			
			int quantidade = Integer.parseInt(qtd_str);
			
			double valor_produto = vlr_unitario * quantidade;
			
			valor = valor + valor_produto;
		}
	
		s.valor_bruto = valor;
		return lista;
	}

}
