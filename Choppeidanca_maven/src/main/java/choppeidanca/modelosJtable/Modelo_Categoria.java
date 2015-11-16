package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.Categoria;
import choppeidanca.persistencia.CategoriaDAO;

public class Modelo_Categoria extends AbstractTableModel{

	private List<Categoria> categorias = new ArrayList<>();
	
	private Categoria categoria;
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int getRowCount() {
		return categorias.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:
			return categorias.get(rowIndex).getCodigo();
		case 1:
			return categorias.get(rowIndex).getTipo();
		case 2:
			return getStringStatus(categorias.get(rowIndex).getStatus());
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
			return "Categoria";
		case 2:
			return "Status";
		default:
			return "ERRO";
		}
	}
	
	public Categoria getSelectedObject(int row) {  
	     return categorias.get(row);  
	} 	
	
	public String getStringStatus(int status) {
		String statusString = null;
		if (status == 1) {
			statusString = "Ativo";
		} else if (status == 0) {
			statusString = "Inativo";
		}
		
		return statusString;
		
	}
}
