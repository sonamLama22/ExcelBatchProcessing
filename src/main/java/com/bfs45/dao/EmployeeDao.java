package com.bfs45.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bfs45.model.Employee;
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
	public void updateEmployees(Employee emp) throws ClassNotFoundException, SQLException {
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
	// deletion
	// selection
}
