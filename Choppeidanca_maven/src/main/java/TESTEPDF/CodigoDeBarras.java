package TESTEPDF;

import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;

import com.itextpdf.text.Document;

import com.itextpdf.text.Image;

import com.itextpdf.text.PageSize;

import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.BarcodeEAN;

import com.itextpdf.text.pdf.PdfContentByte;

import com.itextpdf.text.pdf.PdfWriter;

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
