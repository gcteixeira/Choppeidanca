package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.CondicaoPagamento;
import choppeidanca.persistencia.CondicaoPagamentoDAO;

public class Modelo_Condicao extends AbstractTableModel {

	private List<CondicaoPagamento> condicoes = new ArrayList<>();
	
	public List<CondicaoPagamento> getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(List<CondicaoPagamento> condicoes) {
		this.condicoes = condicoes;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return condicoes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return condicoes.get(rowIndex).getId();
		case 1:
			return condicoes.get(rowIndex).getNome();
		case 2:
			return condicoes.get(rowIndex).getQtdVezes();
		default:
			return "ERRO";
		}	
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Descrição";
		case 2:
			return "Quantidade de Vezes";
		default:
			return "ERRO";
		}
		
	}

	public CondicaoPagamento getSelectedObject(int row) {
		return condicoes.get(row);
	}

}
