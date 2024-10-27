package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilitis {
	public FileInputStream ifile;
	public FileOutputStream ofile;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;

	public ExcelUtilitis(String path) {
		this.path = path;
	}


	public int getRowCount(String sheetName) throws IOException {

		ifile = new FileInputStream(path);
		workbook = new XSSFWorkbook(ifile);
		sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getLastRowNum();

		workbook.close();
		ifile.close();
		return noOfRows;
	}

	public int getCellCount(String sheetName, int rowNumber) throws IOException {

		ifile = new FileInputStream(path);
		workbook = new XSSFWorkbook(ifile);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		int noOfCells = row.getLastCellNum();

		workbook.close();
		ifile.close();
		return noOfCells;

	}

	public String getCellValue(String sheetName, int rowNumber, int cellNumber) throws IOException {

		ifile = new FileInputStream(path);
		workbook = new XSSFWorkbook(ifile);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);
		String cellValue;

		switch (cell.getCellType()) {
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case NUMERIC:
			cellValue = String.valueOf((int) cell.getNumericCellValue());
			break;
		default:
			cellValue = "";
		}


		workbook.close();
		ifile.close();
		return cellValue;

	}

	public void setCellValue(String sheetName, int rowNumber, int cellNumber, String CellValue) throws IOException {

		ifile = new FileInputStream(path);
		workbook = new XSSFWorkbook(ifile);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.createCell(cellNumber);
		cell.setCellValue(CellValue);
		ofile = new FileOutputStream(path);
		workbook.write(ofile);

		workbook.close();
		ifile.close();	
		ofile.close();

	}

}



