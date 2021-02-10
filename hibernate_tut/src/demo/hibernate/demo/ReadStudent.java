package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Student;

public class ReadStudent {

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
			Student st = new Student("Some","Guy","some_email@gmail.com");
			//start transaction
			s.beginTransaction();
			//save student object
			System.out.println("Save student object");
			System.out.println(st);
			s.save(st);
			//commit transaction
			s.getTransaction().commit();
			System.out.println("Done!");
			
			//find student
			System.out.println("Find student");
			System.out.println("Save student generated id :"+ st.getId());
			
			//Get new session
			s = factory.getCurrentSession();
			s.beginTransaction();
			//retrieve student based on id
			
			System.out.println("Get student with id:" + st.getId());
			System.out.println(s.get(Student.class, st.getId()));
			System.out.println("Done!");
			//commit transaction
			s.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
}
