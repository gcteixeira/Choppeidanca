package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.FormaPagamento;
import choppeidanca.persistencia.FormaPagamentoDAO;


public class Modelo_FormaDePagamento extends AbstractTableModel {

	private List<FormaPagamento> formas = new ArrayList<>();

	public List<FormaPagamento> getFormas() {
		return formas;
	}

	public void setFormas(List<FormaPagamento> formas) {
		this.formas = formas;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return formas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return formas.get(rowIndex).getCodigo();
		case 1:
			return formas.get(rowIndex).getTipo();
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
			return "Nome";
		default:
			return "ERRO";
		}
	}

	public FormaPagamento getSelectedObject(int row) {
		return formas.get(row);
	}

}
