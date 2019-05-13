package ru.springmvc.testproject.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ru.springmvc.testproject.objects.User;

public class PDFBuilder extends AbstractUserPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) model.get("user");
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.RED);
		document.add(new Paragraph("User's login and passwords:", font));
		// document.add(new Paragraph(user.getName()));
		// document.add(new Paragraph(user.getPassword()));

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 3.0f, 2.0f });
		table.setSpacingBefore(10);

		font.setColor(BaseColor.WHITE);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("User", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Password", font));
		table.addCell(cell);

		// add User data

		table.addCell(user.getName());
		table.addCell(user.getPassword());

		document.add(table);

	}
}
