package com.bfs45.model;

public class Employee {
	private int emp_id;
	private String emp_name;
	private int salary;
	private String position;
	
	public Employee(int emp_id, int salary, String position) {
		super();
		this.emp_id = emp_id;
		this.salary = salary;
		this.position = position;
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
