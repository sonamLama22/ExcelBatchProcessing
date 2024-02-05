package com.bfs45.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.bfs45.model.Employee;
import com.bfs45.processor.Processor;
import com.bfs45.service.EmployeeService;
import com.bfs45.util.DBUtil;

public class EmployeeDao {

	// pass Employee object as args in order to access its private variables.
	public void saveEmployees(Employee emp) throws ClassNotFoundException, SQLException {
		Connection con = DBUtil.getConnection();
		
		String query = "INSERT INTO employee(emp_id, emp_name,salary, position)"
				+ "VALUES(?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, emp.getEmp_id());
		ps.setString(2, emp.getEmp_name());
		ps.setInt(3, emp.getSalary());
		ps.setString(4, emp.getPosition());
		
		ps.execute();
		System.out.println("Record has been inserted.");
		ps.close();
		con.close();
	}
	
	// updation
	public void updateEmployees(Employee emp) throws ClassNotFoundException, SQLException, EncryptedDocumentException, IOException {

		Connection con = DBUtil.getConnection();
		String query = "UPDATE employee SET salary = ?, position = ? WHERE emp_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, emp.getSalary());
		ps.setString(2, emp.getPosition());
		ps.setInt(3, emp.getEmp_id());
		
		ps.execute();
		System.out.println("Record has been updated.");
		ps.close();
		con.close();
			
	}

	// selection
	public void selectEmployees() throws ClassNotFoundException, SQLException, EncryptedDocumentException, IOException {

		Processor p = new  Processor();
		FileInputStream fis = new FileInputStream(p.fileName);
//		System.out.println(p.fileName);
		Workbook wb = WorkbookFactory.create(fis);
		
		// if sheet already exists, delete sheet.
		if(wb.getSheetName(1).equals(p.newSheet)) {
			wb.removeSheetAt(1);
		}
		
		// create new sheet
		Sheet sheet = wb.createSheet(p.newSheet);
		Row row = sheet.createRow(0); //create first row of the sheet 
		
		// column names of the first row
		row.createCell(0).setCellValue("emp_id");
		row.createCell(1).setCellValue("emp_name");
		row.createCell(2).setCellValue("salary");
		row.createCell(3).setCellValue("position");
		
		Connection con = DBUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from employee");
		EmployeeService e = new EmployeeService();
		while(rs.next()) {
			int emp_id = rs.getInt("emp_id");
			String emp_name = rs.getString("emp_name");
			int salary = rs.getInt("salary");
			String position = rs.getString("position");	
			// as soon as we get data from db, write the data to excel 
			e.writeToExcel(emp_id, emp_name, salary, position, row, sheet);
		}
		e.closeExcel( wb, p);
		con.close();
	}
}
