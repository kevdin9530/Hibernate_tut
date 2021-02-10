package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Student;

public class CreateStudent {

	public static void main(String[] args) {
		//create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session s = factory.getCurrentSession();
		//use the session object to save java object
		try {
			
			
			//create student object
			System.out.println("Create a student object");
			Student st = new Student("Kevin","Dinh","kevdin9530@gmail.com");
			//start transaction
			s.beginTransaction();
			//save student object
			System.out.println("Save student object");
			s.save(st);
			//commit transaction
			s.getTransaction().commit();
			System.out.println("Done!");
		}finally {
			factory.close();
		}
	}
}
