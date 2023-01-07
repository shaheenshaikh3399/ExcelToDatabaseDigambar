package com.excelToDatabase.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.excelToDatabase.domain.Employee;

public class ExcelUploadService {
	private static Logger logger = LoggerFactory.getLogger(ExcelUploadService.class);
	
	
	
	 public static List<Employee> getEmployeesDataFromExcel(InputStream inputStream){
	        List<Employee> Employees = new ArrayList<>();
	       try {
	           XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	           XSSFSheet sheet1 = workbook.getSheet("Sheet1");
	           int rowIndex =0;
	           for (Row row : sheet1){
	               if (rowIndex ==0){
	                   rowIndex++;
	                   continue;
	               }
	               Iterator<Cell> cellIterator = row.iterator();
	               int cellIndex = 0;
	               Employee Employee = new Employee();
	               while (cellIterator.hasNext()){
	                   Cell cell = cellIterator.next();
	                   switch (cellIndex){
	                       case 0 -> Employee.setEmployeeNo((int)cell.getColumnIndex());
	                       case 1 -> Employee.setEmployeeName(cell.getStringCellValue());
	                       case 2 -> Employee.setProjectEndDate(cell.getDateCellValue());
	                       case 3 -> Employee.setProjectId((int)cell.getNumericCellValue());
	                       case 4 -> Employee.setProjectName(cell.getStringCellValue());
	                       case 5 -> Employee.setProjectStartDate(cell.getDateCellValue());
	                       default -> {
	                       }
	                   }
	                   cellIndex++;
	               }
	               Employees.add(Employee);
	               for (int i =0 ; i<Employees.size(); i++) {
	            	   logger.info(i+ "Employee details");
	               }
	           }
	       } catch (IOException e) {
	           e.getStackTrace();
	       }
	       return Employees;
	   }

	}


