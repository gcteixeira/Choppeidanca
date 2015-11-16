package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;




import choppeidanca.modelo.Pais;
import choppeidanca.modelo.Uf;
import choppeidanca.persistencia.UfDAO;

public class Modelo_Uf extends AbstractTableModel {

	private List<Uf> ufs = new ArrayList<>();
	private Pais pais;

	public List<Uf> getUfs() {
		return ufs;
	}
	
	public void setUfs(List<Uf> ufs) {
		this.ufs = ufs;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int getRowCount() {
		return ufs.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:
			return ufs.get(rowIndex).getId();
		case 1:
			return ufs.get(rowIndex).getNome();
		case 2:
			return ufs.get(rowIndex).getSigla();
		case 3:
			return ufs.get(rowIndex).getPais();
		default:
			return "Erro";
			
		}
	}
	
	 @Override
	    public String getColumnName(int i){
	        switch(i){
	            case 0:
	                return "Id";
	            case 1:
	                return "Nome";
	            case 2:
	                return "Sigla";
	            case 3:
	            	return "País";
	            default:
	                return "ERRO";
	        }
	    }
	 public Uf getSelectedObject(int row) {  
	     return ufs.get(row);  
	}  

}
