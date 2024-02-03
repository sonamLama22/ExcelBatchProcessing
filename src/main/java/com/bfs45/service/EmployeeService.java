package com.bfs45.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.bfs45.dao.EmployeeDao;
import com.bfs45.model.Employee;

public class EmployeeService {
	
	EmployeeDao empDao; 

	// File path: C:\\Users\\sonam\\bfs45_workspace\\EmployeeInfo.xlsx
		// take excel sheet as arg.
		public void readFromExcel(File file, String sheetName) throws EncryptedDocumentException, IOException, ClassNotFoundException, SQLException {
			
			FileInputStream fis = null;
			if(sheetName.equals("employee")){
				fis = new FileInputStream(file); //excel sheet path employee.xlsx
				
				//create object of workbook.
				Workbook workBook = WorkbookFactory.create(fis);
				Sheet sheet = workBook.getSheet(sheetName);
				
				//create object of empDao to access its methods.
				empDao = new EmployeeDao();
			
				// loop thru excel sheet
				for(Row row: sheet) {
					if(row.getRowNum() != 0) { // skip first row
						// create Employee object  -------> Add validation for employee id. it shouln't exceed 30 chars.
											      // --------> Add validation for address, and others too
						Employee emp=new Employee((int)row.getCell(0).getNumericCellValue(), 
								String.valueOf(row.getCell(1)),
								(int)row.getCell(2).getNumericCellValue(),
								String.valueOf(row.getCell(3)));
						empDao.saveEmployees(emp); //save data to DB.
						
					}
					System.out.println();
				}
			} 	
		
	}
		
		public void writeToExcel(File file, String sheetName) throws EncryptedDocumentException, IOException, ClassNotFoundException, SQLException {
			
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.createSheet(sheetName);
			empDao = new EmployeeDao();
			
			while(true) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter emp_id: ");
				int emp_id = sc.nextInt();
				System.out.println("update employee position: ");
				String position = sc.next();
				System.out.println("update salary: ");
				int salary = sc.nextInt();
				
				Employee emp = new Employee(emp_id, salary, position); 
				empDao.updateEmployees(emp);
				
				FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				
				System.out.println("Update more employee details(Y/N): ");
				String choice = sc.next();
				if(choice.toUpperCase().equals("N")) {
					break;
				}
				
			}
		
			
			
			
		}
	
}
