package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.Pais;
import choppeidanca.persistencia.PaisDAO;


public class Modelo_Pais extends AbstractTableModel{

	private List<Pais> paises = new ArrayList<>();
	
	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return paises.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:
			return paises.get(rowIndex).getId();
		case 1:
			return paises.get(rowIndex).getNome();
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
            default:
                return "ERRO";
        }
    }

	public Pais getSelectedObject(int row) {  
	     return paises.get(row);  
	}  
	
}
