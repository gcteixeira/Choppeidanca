package choppeidanca.modelosJtable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;




import choppeidanca.modelo.TabelaPreco;
import choppeidanca.persistencia.TabelaPrecoDAO;

public class Modelo_TabelaPreco extends AbstractTableModel {

private List<TabelaPreco> tabelas = new ArrayList<>();
		
	public List<TabelaPreco> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaPreco> tabelas) {
		this.tabelas = tabelas;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return tabelas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:
			return tabelas.get(rowIndex).getId();
		case 1:
			return tabelas.get(rowIndex).getNome();
		case 2:
			return retornaDate(tabelas.get(rowIndex).getDataValidade());
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
            	return "Data Validade";
            default:
                return "ERRO";
        }
    }

	public TabelaPreco getSelectedObject(int row) {  
	     return tabelas.get(row);  
	}  
	
	public String retornaDate(Date data) {
		SimpleDateFormat formatOut = new SimpleDateFormat(
				"dd/MM/yyyy");		
		
		String str = formatOut.format(data);

		return str;
	}
}
