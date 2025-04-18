package com.group.Utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class ReadGroupExcelData {
	public static String[][] readData(String sheetName) throws IOException {
		DataFormatter dataFormatter = new DataFormatter();
		XSSFWorkbook wb = new XSSFWorkbook("./GlobalData/GlobalAutomation.xlsx");
		XSSFSheet ws = wb.getSheet(sheetName);
		
		int rowCount = ws.getLastRowNum();
		short cellCount = ws.getRow(0).getLastCellNum();
	
		String[][] data = new String [rowCount][cellCount];
		for (int i=1; i<=rowCount; i++) {
			for (int j=0; j<cellCount; j++) {
	//		    data[i-1][j] = ws.getRow(i).getCell(j).toString();
				data[i-1][j] = dataFormatter.formatCellValue(ws.getRow(i).getCell(j));
			}
		}
		wb.close();
		return data;
	}
	public static ListMultimap<String, String> readDropdownData() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("./GlobalData/GlobalAutomation.xlsx");
		XSSFSheet ws = wb.getSheet("UpdatedMetatags");
		ListMultimap<String, String> dataMap = ArrayListMultimap.create();
		for (Row row : ws) {
            Cell keyCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            Cell valueCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            String key = keyCell.toString().trim();
            String value = valueCell.toString().trim();

            dataMap.put(key, value);
        }
		wb.close();
		return dataMap;	
	}
	

}


