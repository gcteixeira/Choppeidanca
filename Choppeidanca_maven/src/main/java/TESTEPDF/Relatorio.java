package TESTEPDF;

import java.sql.Connection;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import choppeidanca.persistencia.Conexao;
import choppeidanca.swing.dialogs.BuscaPessoa;

public class Relatorio {

	
	public void geraRelatorio() {

		Connection conexao = Conexao.conectar();
        try {

   
        	
        	System.err.println("CLICOU");
        	
//            JasperPrint jp = JasperFillManager.fillReport(System.getProperty("user.dir")+"\\src\\relatorios\\RelatorioCategoria.jasper", null, conexao);

        	JasperPrint jp = JasperFillManager.fillReport("E:\\pessoa.jasper", null, conexao);
        	
        	JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Relatório de Pessoa");
            
        } catch (Exception ex) {
        	 Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
	}
	
}
