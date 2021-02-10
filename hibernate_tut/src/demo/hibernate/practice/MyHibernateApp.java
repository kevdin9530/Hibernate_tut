package demo.hibernate.practice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import demo.hibernate.entity.Employee;

public class MyHibernateApp {
	
	public SessionFactory startFactory() {
		return new Configuration()
				.configure("hibernate.cfg2.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
	}
	
	public void saveObject(Employee e) {
		SessionFactory factory = startFactory();
		try {
			//Create session
			Session s = factory.getCurrentSession();
			//start transaction
			s.beginTransaction();
			//save object
			s.save(e);
			//commit transaction
			s.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	
	public Employee getObject(int id) {
		SessionFactory factory = startFactory();
		try {
			//Create session
			Session s = factory.getCurrentSession();
			//start transaction
			s.beginTransaction();
			//read object
			Employee e = s.get(Employee.class, id);
			//commit transaction
			s.getTransaction().commit();
			//return object
			return e;
		}finally {
			factory.close();
		}
	}
	
	public List<Employee> getCompany(String company) {
		SessionFactory factory = startFactory();
		try {
			//Create session
			Session s = factory.getCurrentSession();
			//start transaction
			s.beginTransaction();
			//read object list according to company
			List<Employee> e = s.createQuery("from Employee where company='"+company+"'").getResultList();
			//commit transaction
			s.getTransaction().commit();
			return e;
		}finally {
			factory.close();
		}
	}
	
	public void deleteObject(int id) {
		SessionFactory factory = startFactory();
		try {
			//Create session
			Session s = factory.getCurrentSession();
			//start transaction
			s.beginTransaction();
			//find object according to primary key
			Employee e = s.get(Employee.class, id);
			//delete object
			s.delete(e);
			//commit transaction
			s.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
	
	

	public static void main(String [] args) {
		MyHibernateApp a = new MyHibernateApp();
		
		Employee e = new Employee("Kevin","Dinh","Jobless");
		Employee e1 = new Employee("Carl","Thomsen","Halmstad Kindergarden");
		Employee e2 = new Employee("Filip","Hanson","Halmstad University");
		Employee e3 = new Employee("Jacob","Spens","Jayway");
		Employee e4 = new Employee("Johan","Herö","Jayway");
		
		System.out.println("Save object into database");
		a.saveObject(e);
		a.saveObject(e1);
		a.saveObject(e2);
		a.saveObject(e3);
		a.saveObject(e4);
		
		System.out.println("//////////Get object with id 3///////////");
		System.out.println("Employee with id 3 is " + a.getObject(3));
		
		System.out.println("Get employee list of jayway");
		List<Employee> eList = a.getCompany("Jayway");
		for(Employee etemp: eList)
			System.out.println(etemp);
		
		System.out.println("/////////REMOVE EMPLOYEE WITH ID 1//////////////");
		a.deleteObject(2);
		
	}
}
