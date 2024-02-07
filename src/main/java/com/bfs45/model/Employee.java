package com.bfs45.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "employee")
@NamedQueries({
	@NamedQuery(name = "employee.FETCH", query= "from Employee e "),
	@NamedQuery( name = "employee.UPDATE", query = "UPDATE Employee e set e.salary=:salary"
			+" where e.emp_id=: emp_id")
})
public class Employee {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "empId_generator")
	private int emp_id;
	
	@Column(name = "emp_name")
	private String emp_name;
	
	@Column(name = "salary")
	private int salary;
	
	@Column(name = "position")
	private String position;
	
	public Employee() {
		
	}
	
	public Employee(int emp_id, int salary) {
		super();
		this.emp_id = emp_id;
		this.salary = salary;
	}
	
	public Employee(int emp_id, String emp_name, int salary, String position) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.salary = salary;
		this.position = position;
	}
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
}
