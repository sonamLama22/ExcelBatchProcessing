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
import com.bfs45.processor.Processor;

public class EmployeeService {
	
	EmployeeDao empDao; 
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
		
		public void updateDB() throws SQLException, EncryptedDocumentException, ClassNotFoundException, IOException {
			
			empDao = new EmployeeDao();
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Update employee details(Y/N): ");
			String choice = sc.next();
			if(choice.toUpperCase().equals("Y")) {
				System.out.println("Enter emp_id: ");
				int emp_id = sc.nextInt();
				System.out.println("update employee position: ");
				String position = sc.next();
				System.out.println("update salary: ");
				int salary = sc.nextInt();
				
				Employee emp = new Employee(emp_id, salary, position); 
				empDao.updateEmployees(emp);
			} else {
				empDao.selectEmployees();
			}
			
		}
		
		
		
		int i=1;
		
		public void writeToExcel(int emp_id, String emp_name, int salary, String position, Row row, Sheet sheet) throws EncryptedDocumentException, IOException {

			
			// create new row in excel sheet
			Row r = sheet.createRow(i++);
			// captured data will show up in each of these cells
			r.createCell(0).setCellValue(emp_id);
			r.createCell(1).setCellValue(emp_name);
			r.createCell(2).setCellValue(salary);
			r.createCell(3).setCellValue(position);
			
		}
		
		
		public void closeExcel(Workbook wb, Processor p) throws IOException {
			
			FileOutputStream fos = new FileOutputStream(p.fileName);
			System.out.println("File has been written to excel sheet.");
			wb.write(fos);
			wb.close();
			fos.close();
		}
	
}
