package TESTEPDF.ireport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.components.table.FillTableFactory;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Principal1 {

	public static void main(String[] args) {
		JasperPrint teste = null;

		// File f = new File("report1.jasper");


		
		String sourceFileName = "relatorio.jasper";

		try {
//			JRDataSource meuDataSource = DsFactory.createDatasource();
//
//			teste = JasperFillManager.fillReport("E:\\report2.jrxml", null,
//					meuDataSource);
//			
			
			JasperExportManager.exportReportToPdfFile("E:\\pessoa.jasper", "E:\\pessoa.pdf");
			
//			JasperExportManager.exportReportToPdfFile(teste, "report1.pdf");
			Desktop.getDesktop().open(new File("E:\\pessoa.pdf"));

		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

	}

}
