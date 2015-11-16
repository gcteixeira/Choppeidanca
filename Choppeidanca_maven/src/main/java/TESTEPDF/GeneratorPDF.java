package TESTEPDF;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
public class GeneratorPDF {

	public static void main(String[] args) {
		// cria��o do documento
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

			// adicionando um par�grafo no documento
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
