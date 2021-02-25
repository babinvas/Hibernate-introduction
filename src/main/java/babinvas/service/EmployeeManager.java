package babinvas.service;

import babinvas.repository.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeManager {
	private final SessionFactory factory;

	public EmployeeManager(SessionFactory factory) {
		this.factory = factory;
	}

	// Reads all the employees
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			List employees = session.createQuery("from Employee").list();

			for (int i = employees.size() - 1; i >= 0; i--) {
				Employee employee = (Employee) employees.get(i);

				System.out.println("First Name: " + employee.getFirstName() +
						"\t" + "Last Name: " + employee.getLastName() +
						"\t" + "Salary: " + employee.getSalary());
			}
		} catch(HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// Creates an employee in the database
	public Integer addEmployee(String firstName, String lastName, int salary) {
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer employeeId = null;

		try{
			transaction = session.beginTransaction();

			Employee employee = new Employee(firstName, lastName, salary);
			employeeId = (Integer) session.save(employee);

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();
		} finally {
			session.close();
		}

		return employeeId;
	}

	// Reads salary for an employee
	public Employee readEmployee(Integer EmployeeId) {
		Session session = factory.openSession();
		Transaction transaction = null;
		Employee employee = null;

		try {
			transaction = session.beginTransaction();

			employee = session.get(Employee.class, EmployeeId);

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();
		} finally {
			session.close();
		}

		return employee;
	}

	// Updates salary for an employee
	public void updateEmployee(Integer EmployeeId, int salary) {
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = session.get(Employee.class, EmployeeId);
			employee.setSalary(salary);
			session.update(employee);

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// Deletes an employee from the records
	public void deleteEmployee(Integer EmployeeId) {
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = session.get(Employee.class, EmployeeId);
			session.delete(employee);

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
