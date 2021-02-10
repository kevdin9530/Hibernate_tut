package demo.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Student;

public class QueryStudentDemo {

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
			int id = 1;
			
			//start transaction
			s.beginTransaction();
			//get student according to id
			Student st = s.get(Student.class, id);
			System.out.println("Get student with id 1 "+ st);
			//update the student
			System.out.println("Now we update the student");
			st.setFirstName("NOT_MY_NAME");
			
			s.getTransaction().commit();
			
			//start new transaction to update all email
			s = factory.getCurrentSession();
			
			s.beginTransaction();
			s.createQuery("update Student set email='some_mail@some_mail_adress.com'").executeUpdate();
			
			s.getTransaction().commit();
			System.out.println("Done!");
			
			
			//start new transaction to update email of student with last name thomsen
			s = factory.getCurrentSession();
			
			s.beginTransaction();
			s.createQuery("update Student set email='carl@thomsen.com' where lastName='Thomsen'").executeUpdate();
			
			s.getTransaction().commit();
			System.out.println("Done!");


		}finally {
			factory.close();
		}
	}

	private static void printStudentList(List<Student> stList) {
		for(Student st:stList)
			System.out.println(st);
	}
}
