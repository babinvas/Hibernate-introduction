package babinvas.service;

import org.hibernate.SessionFactory;

public class EmployeeManager {
	private static SessionFactory factory;

	public static void main(String[] args) {
	}

	// Reads all the employees
	public void listEmployees() {
	}

	// Create an employee in the database
	public Integer addEmployee(String firstName, String lastName, int salary) {
		return 0;
	}

	// Reads salary for an employee
	public void readEmployee(Integer EmployeeID) {
	}

	// Updates salary for an employee
	public void updateEmployee(Integer EmployeeID, int salary) {
	}

	// Deletes an employee from the records
	public void deleteEmployee(Integer EmployeeID) {

	}
}
