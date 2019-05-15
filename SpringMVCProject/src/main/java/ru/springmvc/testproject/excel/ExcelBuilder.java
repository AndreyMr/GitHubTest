package ru.springmvc.testproject.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import ru.springmvc.testproject.objects.User;

public class ExcelBuilder extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) model.get("user");

		// create a new Excel sheet
		Sheet sheet = workbook.createSheet("Users");
		sheet.setDefaultColumnWidth(30);

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("User name");
		header.createCell(1).setCellValue("User password");

		// create data rows
		int rowCount = 1;
		Row dataRow = sheet.createRow(rowCount);
		dataRow.createCell(0).setCellValue(user.getName());
		dataRow.createCell(1).setCellValue(user.getPassword());

	}
}
