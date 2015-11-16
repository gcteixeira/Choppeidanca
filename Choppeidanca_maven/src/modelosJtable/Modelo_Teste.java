package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.TabelaPrecoDetalhe;
import choppeidanca.utils.Singleton;

public class Modelo_Teste extends AbstractTableModel {

	private List<TabelaPrecoDetalhe> tabelas = new ArrayList<>();

	String valoranterior = "";
	
	public List<TabelaPrecoDetalhe> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaPrecoDetalhe> tabelas) {
		this.tabelas = tabelas;
	}

	@Override
	public int getColumnCount() {
		return 5;
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
			return "Quantidade";
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
}
