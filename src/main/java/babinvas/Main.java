package babinvas;

import babinvas.repository.Employee;
import babinvas.service.EmployeeManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {
		SessionFactory factory;

		try {
			factory = new Configuration().configure().buildSessionFactory();

		} catch(Throwable e) {
			System.err.println("Failed to create sessionFactory object." + e);
			throw new ExceptionInInitializerError(e);
		}

		EmployeeManager employeeManager =  new EmployeeManager(factory);

		Integer employeeId1 = employeeManager.addEmployee("Vas", "Babin", 50000);
		Integer employeeId2 = employeeManager.addEmployee("Jhon", "Grey", 30000);
		Integer employeeId3 = employeeManager.addEmployee("Paul", "Pencil", 10000);

		employeeManager.listEmployees();

		employeeManager.updateEmployee(employeeId1, 55000);

		Employee employee1 = employeeManager.readEmployee(employeeId1);
		System.out.println("\n" + "First Name: " + employee1.getFirstName() +
				"\t" + "Last Name: " + employee1.getLastName() +
				"\t" + "Salary: " + employee1.getSalary() + "\n");

		employeeManager.deleteEmployee(employeeId2);

		employeeManager.listEmployees();
	}
}
