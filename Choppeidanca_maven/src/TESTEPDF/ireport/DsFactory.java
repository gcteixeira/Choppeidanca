package TESTEPDF.ireport;

import java.util.ArrayList;
import java.util.List;

import choppeidanca.modelo.Pessoa;
import choppeidanca.persistencia.PessoaDAO;
import net.sf.jasperreports.engine.JRDataSource;

public class DsFactory {

	
	public static JRDataSource createDatasource() {
		
		PessoaDAO dao = new PessoaDAO();
		
		List<Pessoa> pessoa = dao.listar();
		
		return new MeuDataSource(pessoa);
	}
	
}
