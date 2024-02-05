package com.bfs45.processor;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;

import com.bfs45.service.EmployeeService;

public class Processor {
	
	public static String fileName = "C:\\Users\\sonam\\bfs45_workspace\\employee.xlsx";
	public static String newSheet = "updatedEmployeeInfo";

	public static void main(String[] args) {

		EmployeeService empService = new EmployeeService();
		EmployeeService empService1 = new EmployeeService();
		// Read file
		File file = new File(fileName);
		Scanner sc = new Scanner(System.in);
			try {
				System.out.println("Do you want to read the file or write to the file? ");
				String answer = sc.next();
				if(answer.equalsIgnoreCase("read")) {
					empService.readFromExcel(file, "employee"); // pass file path and  sheet name to empDao method.
				}
				else if(answer.equalsIgnoreCase("write")) {
					empService1.updateDB();
				}else {
					System.out.println("Please enter choose correct option.");
				}
				
			} catch (EncryptedDocumentException | IOException | ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
	}

}
