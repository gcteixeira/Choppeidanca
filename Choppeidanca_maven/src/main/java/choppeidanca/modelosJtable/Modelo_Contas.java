package choppeidanca.modelosJtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import choppeidanca.modelo.CondicaoPagamento;
import choppeidanca.modelo.Contas;

public class Modelo_Contas extends AbstractTableModel {

	private List<Contas> contas = new ArrayList<>();
	
	public List<Contas> getContas() {
		return contas;
	}

	public void setContas(List<Contas> contas) {
		this.contas = contas;
	}

	@Override
	public int getColumnCount() {
		return 10;
	}

	@Override
	public int getRowCount() {
		
		return contas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return contas.get(rowIndex).getId();
		case 1:
			return contas.get(rowIndex).getMovimento().getPessoa();
		case 2:
			return contas.get(rowIndex).getCondicao().getNome();
		case 3:
			return contas.get(rowIndex).getForma().getTipo();
		case 4:
			return contas.get(rowIndex).getData_emissao();
		case 5:
			return contas.get(rowIndex).getData_fatura();
		case 6:
			return retornaTipo(contas.get(rowIndex).getTipo());
		case 7:
			return contas.get(rowIndex).getValor_total();
		case 8:
			return contas.get(rowIndex).getCondicao().getQtdVezes() + "x " + contas.get(rowIndex).getValor_parcela();
		case 9:
			return retornaStatus(contas.get(rowIndex).getStatus());
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
			return "Pessoa";
		case 2:
			return "Condição";
		case 3:
			return "Forma";
		case 4:
			return "Data da Venda";
		case 5:
			return "Data de Vencimento";
		case 6:
			return "Tipo";
		case 7:
			return "Valor Total";
		case 8:
			return "Parcelas";
		case 9:
			return "Status";
		default:
			return "ERRO";
		}
	}

	public Contas getSelectedObject(int row) {
		return contas.get(row);
	}
	
	public String retornaStatus(int status) {
	
		if (status == 0) {
			return "Em Debito";
		} else {
			return "Quitado";
		}
		
	}
	
	public String retornaTipo(int tipo) {
		
		if (tipo == 0) {
			return "Saida";
		} else {
			return "Entrada";
		}
	}
	
}
