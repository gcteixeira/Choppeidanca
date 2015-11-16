package choppeidanca.modelo;

import java.util.Date;

public class DataFatura {

	private int id;
	private Contas conta;
	private Date data_fatura;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contas getConta() {
		return conta;
	}

	public void setConta(Contas conta) {
		this.conta = conta;
	}

	public Date getData_fatura() {
		return data_fatura;
	}

	public void setData_fatura(Date data_fatura) {
		this.data_fatura = data_fatura;
	}

	@Override
	public String toString() {
		return "DataFatura [id=" + id + ", conta=" + conta + ", data_fatura="
				+ data_fatura + "]";
	}

}
