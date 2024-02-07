package com.bfs45.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bfs45.model.Employee;
import com.bfs45.processor.Processor;
import com.bfs45.service.EmployeeService;
import com.bfs45.util.HibernateUtil;

public class EmployeeDao {

	// insert
	public void saveEmployees(Employee emp){
			
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		emp.getEmp_id();
		emp.getEmp_name();
		emp.getSalary();
		emp.getPosition();
		
		session.save(emp);	
		transaction.commit();
		session.close();
		System.out.println("Record has been inserted");

	}
	
	// update
	public void updateEmployees(Employee emp)  {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createNamedQuery("employee.UPDATE");
		query.setParameter("salary", emp.getSalary());
		query.setParameter("emp_id", emp.getEmp_id());
		query.executeUpdate();
		transaction.commit();
		session.close();
		System.out.println("Record has been updated");			
	}

	// select
	public void selectEmployees() throws EncryptedDocumentException, IOException {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Processor p = new  Processor();
		FileInputStream fis = new FileInputStream(p.fileName);
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
		
		Query<Employee> query = session.createNamedQuery("employee.FETCH");
		List<Employee> list = query.list();
		EmployeeService service = new EmployeeService();
		for(Employee e : list) {
			System.out.println(e.getEmp_id() + " "+ e.getEmp_name() +" "+ e.getSalary() +" "+e.getPosition());
			service.writeToExcel(e.getEmp_id(),e.getEmp_name(), e.getSalary(), e.getPosition(), row, sheet);
		}
		session.close();
		service.closeExcel( wb, p);
	}
}
