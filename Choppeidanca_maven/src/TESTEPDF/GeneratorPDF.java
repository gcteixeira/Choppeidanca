package TESTEPDF;


import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;



public class GeneratorPDF {

	public static void main(String[] args) {
		// criação do documento
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(
					"C:\\Users\\-Matheus\\Desktop\\PDF_DevMedia.pdf"));
			document.open();

			Color black = new Color(1, 1, 1);
			
			Font fontNormal = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10,
					Font.NORMAL, BaseColor.BLACK);
			Font fontNegrito = FontFactory.getFont(FontFactory.COURIER, 10,
					Font.BOLD, BaseColor.BLACK);

			// adicionando um parágrafo no documento
			Paragraph texto = new Paragraph("Ex.: \n", fontNormal);
			Phrase frase = new Phrase("texto normal, ", fontNormal);
			frase.add(new Chunk("texto negrito.", fontNegrito));

			texto.add(new Phrase("Exemplo finalizado!", fontNormal));

			document.add(texto);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}
}
