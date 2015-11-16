package choppeidanca.modelosJtable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.text.Caret;

import choppeidanca.modelo.Pessoa;
import choppeidanca.persistencia.PessoaDAO;
import choppeidanca.utils.Singleton;

public class Modelo_PessoaFisica extends AbstractTableModel {

	private List<Pessoa> pessoas = new ArrayList<>();
	
	Singleton s = Singleton.getInstance();

	private List<Pessoa> carregarLista() {

		List<Pessoa> pessoas = new ArrayList<>();
		
		for (int i = 0; i < s.pessoas.size(); i++) {
			if (s.pessoas.get(i).getTipo() == 0) {
				pessoas.add(s.pessoas.get(i));
			}
		}

		return pessoas;

	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		pessoas = carregarLista();
		return pessoas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return pessoas.get(rowIndex).getId();
		case 1:
			return pessoas.get(rowIndex).getNome();
		case 2:
			return pessoas.get(rowIndex).getApelido();
		case 3:
			return verificaTipo(pessoas.get(rowIndex).getTipo());
		case 4:
			return pessoas.get(rowIndex).getEndereco() + ", "
					+ pessoas.get(rowIndex).getNumero();
		case 5:
			return pessoas.get(rowIndex).getCidade();
		case 6:
			return pessoas.get(rowIndex).getTelefone();
		case 7:
			return pessoas.get(rowIndex).getRginscricao();
		case 8:
			return pessoas.get(rowIndex).getCpfcnpj();
		default:
			return "ERRO";
		}
	}

	@Override
	public String getColumnName(int i) {
		switch (i) {
		case 0:
			return "ID";
		case 1:
			return "Nome";
		case 2:
			return "Apelido";
		case 3:
			return "Tipo";
		case 4:
			return "Endereço";
		case 5:
			return "Cidade";
		case 6:
			return "Telefone";
		case 7:
			return "RG";
		case 8:
			return "CPF";
		default:
			return "ERRO";
		}
	}

	private String verificaTipo(int tipo) {
		String statusString = null;
		if (tipo == 1) {
			statusString = "Jurídica";
		} else if (tipo == 0) {
			statusString = "Física";
		}

		return statusString;
	}

	public Pessoa getSelectedObject(int row) {
		return pessoas.get(row);
	}

	public String retornaDate(Date data) {
		try {
			SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");
			
			String dataStr = formatOut.format(data);
			
			return dataStr;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
