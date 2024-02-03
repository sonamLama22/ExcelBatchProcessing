package com.bfs45.processor;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;

import com.bfs45.service.EmployeeService;

public class Processor {

	public static void main(String[] args) {
		
		// take another excel file from user input., say updateEmployee.xlsx.
		// Then take that xlsx file and do the updation logic in Dao.
		// different operations for different excel files.
		EmployeeService empService = new EmployeeService();
		EmployeeService empService1 = new EmployeeService();
		// Read file
		File file = new File("C:\\Users\\sonam\\bfs45_workspace\\EmployeeInfo.xlsx");
		Scanner sc = new Scanner(System.in);
			try {
				System.out.println("Do you want to read or update the file? ");
				String answer = sc.next();
				if(answer.equalsIgnoreCase("read")) {
					empService.readFromExcel(file, "employee"); // pass file path and  sheet name to empDao method.
				}
				else if(answer.equalsIgnoreCase("update")) {
					empService1.writeToExcel(file, "1updateEmployee");
				}else {
					System.out.println("Please enter choose correct option.");
				}
				
			} catch (EncryptedDocumentException | IOException | ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			

	}

}
