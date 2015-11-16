package TESTEPDF;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;









import choppeidanca.modelo.Pessoa;
import choppeidanca.modelo.Uf;
import choppeidanca.persistencia.PessoaDAO;
import choppeidanca.persistencia.UfDAO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorPDF {
	

//	public static void main(String[] args) {
//		
//		GeradorPDF g = new GeradorPDF();
//		g.geraRelatorio("Joao", "Jose");
//		
//	}
	
	
	
	public void geraRelatorio(String nomearquivo, String desc){
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\-Matheus\\Desktop\\"+nomearquivo));
			document.open();
			
			document.add(new Paragraph(desc));
			
			document.add(new Paragraph("Sigla \t \t Nome \t" ));
//			List<Pessoa> lista = new ArrayList<>();
//			lista.add("AEOOW");
//			PessoaDAO pdao = new PessoaDAO();
//			lista = pdao.listar();
//			PersistDB pdb = new PersistDB();
//			lista = pdb.mostarChats(de, para);

			Rectangle rect = new Rectangle(getPoints(16.90f), getPoints(12.90f), getPoints(2.5f), getPoints(9.9f));
			document.add(rect);
			List<Uf> lista = new ArrayList<>();
			
			UfDAO dao = new UfDAO();
			lista = dao.listar();

			
			for (int i = 0; i < lista.size(); i++) {
				document.add(new Paragraph(lista.get(i).getSigla() + "\t \t \t" + lista.get(i).getNome()));
				
			}
			document.add(new Paragraph("----------------------------------------------------------------- Fim -----------------------------------------------------------" ));
			
			System.out.println("GEROU O RELATORIO");
			
			
			
		} catch (DocumentException dex) {
			System.err.println(dex.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
		abrirArquivo(nomearquivo);
	}

	public float getPoints(float centimeters) {
	    return centimeters * 28.3464566929f;
	  }
	
	private void abrirArquivo(String nomearquivo) {
		try {
			java.awt.Desktop.getDesktop().open( new File( "C:\\Users\\-Matheus\\Desktop\\"+nomearquivo ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
