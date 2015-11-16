package TESTEPDF;

import java.awt.Image;
import java.io.FileOutputStream;

import org.w3c.dom.Document;

public class CodigoDeBarras {

	public static void main(String[] args) {

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		try {

			PdfWriter writer = PdfWriter

			.getInstance(

			document,

			new FileOutputStream(

			"C:\\Users\\-Matheus\\Desktop\\Codigo_Barra_iText.pdf"));

			document.open();

			PdfContentByte cb = writer.getDirectContent();

			BarcodeEAN codeEAN = new BarcodeEAN();

			codeEAN.setCodeType(codeEAN.EAN13);

			codeEAN.setCode("9740201615443");

			Image imageEAN = codeEAN.createImageWithBarcode(cb, null, null);

			document.add(new Phrase(new Chunk(imageEAN, 0, 0)));

		}

		catch (Exception de) {

			de.printStackTrace();

		}

		document.close();

	}

}
