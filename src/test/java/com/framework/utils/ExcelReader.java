/**
 * 
 */
package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.framework.datadriven.BaseTest;

/**
 * @author RpaPai
 *
 */
public class ExcelReader extends BaseTest {
	//	private static XSSFWorkbook workBook;
	private static XSSFSheet sheet;
	private static XSSFRow  row;
	private static FileInputStream   xlFile;

	private static XSSFWorkbook extractWorkBook(String filePath) {
		try {
			xlFile= new FileInputStream(filePath);
			XSSFWorkbook workbook= new XSSFWorkbook(xlFile);
			return workbook;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("**** This is error : " + e.getMessage());			
			e.printStackTrace();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("**** This is error : " + e.getMessage());			
			e.printStackTrace();
		}
		return null;
	}

	private static String getCellData(int row, int col) {
		XSSFCell cell=null;
		cell = sheet.getRow(row).getCell(col);
		if( cell==null ) {
	
			logger.debug("Read ExcelData: is blank ");
			return "";
		} 
		if(cell.getCellTypeEnum()== CellType.STRING ) {
			String   data = cell.getStringCellValue();
			
			logger.debug("Read ExcelData: " + data);
			return data;
		}
		else {
			logger.error("**** Reading ExcelData: Found non string value at " + row + ", "+col);
			return "";
		}
	}


	public static String[][] GetExcelData(String filePath, String sheetName) {
		logger.debug("Reading entire data from file: "+ filePath+ " Sheet: "+sheetName);
		String xlData[][]=null;
		XSSFWorkbook workBook;

		workBook=extractWorkBook(filePath);

		//   workBook = new XSSFWorkbook(xlFile);
		sheet = workBook.getSheet(sheetName);

		int numRows = sheet.getLastRowNum();     // Zero indexed 
		logger.debug("Total Number of TestCases   : "+(numRows));

		int numCols = sheet.getRow(0).getLastCellNum();		// non zero indexed starts at 1
		logger.debug("Number of Cols in test data   : "+(numCols));
		xlData = new String[numRows][numCols-1];

		for(int r =1;r<=numRows;r++) {
			if(getCellData(r,0).isEmpty()) continue; // If no TC_ID then skip
			for(int c=1; c<numCols; c++ ) {
				xlData[r-1][c-1] = getCellData(r,c);
			}
		}

		return  xlData;
	}

	public static String[][] GetExcelData(String filePath, String sheetName, String testCaseID) {
		logger.debug("Reading test data for test case "+ testCaseID+ " from file: "+ filePath+ " Sheet: "+sheetName);
		String xlData[][]=null;

		XSSFWorkbook workBook;

		workBook=extractWorkBook(filePath);

		//	   workBook = new XSSFWorkbook(xlFile);
		sheet = workBook.getSheet(sheetName);

		int numRows = sheet.getLastRowNum();
		logger.debug("Total Number of TestCases   : "+numRows);

		int numCols = sheet.getRow(0).getLastCellNum();
		logger.debug("Number of Cols in test data   : "+numCols);
		xlData = new String[1][numCols-1];

		for(int r =1;r<=numRows;r++) {
			if(getCellData(r,0).isEmpty()) continue;			// If no TC_ID then skip
			if(testCaseID.equals(getCellData(r,0))) {		// If no TC_IDmatches required TC
				for(int c=1; c<numCols; c++ ) {
					xlData[0][c-1] = getCellData(r,c);
				}
				break;
			}
		}
		return  xlData;
	}

}
