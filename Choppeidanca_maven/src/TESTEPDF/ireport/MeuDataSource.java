package TESTEPDF.ireport;

import java.util.Iterator;
import java.util.List;

import choppeidanca.modelo.Pessoa;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MeuDataSource implements JRDataSource{

	private int contador = 0;
	
	private Pessoa selecionado = null;
	private Iterator<Pessoa> it;
	
	public MeuDataSource(List<Pessoa> lista) {
		this.it = lista.iterator();
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		
		if (field.getName().equals("id")) {
			return String.valueOf(selecionado.getId());
		}
		if (field.getName().equals("nome")) {
			return selecionado.getNome();
		}
		
		return "Valor";
	}

	@Override
	public boolean next() throws JRException {

		if (it.hasNext()) {
			selecionado = it.next();
			return true;
		}
		return false;
		
//		return contador++ != 1000;
	}

}
