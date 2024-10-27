package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException {
		
		String pathString = "C:\\Users\\Abishekh\\eclipse-workspace\\uiAutomation\\testData\\loginDatas.xlsx";
				
		ExcelUtilitis excelUtilitis = new ExcelUtilitis(pathString);
		
		int rowCount = excelUtilitis.getRowCount("Sheet1");
		int cellCount = excelUtilitis.getCellCount("Sheet1", 0);
		
		String[][] loginData = new String[rowCount][cellCount];
		
		for (int i = 1; i <=rowCount;i++) {
			for (int j = 0; j < cellCount; j++) {
				loginData[i-1][j]= excelUtilitis.getCellValue("Sheet1", i, j);
			}
		}
		
		return loginData;
		
	}

}
