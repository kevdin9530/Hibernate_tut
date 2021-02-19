package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.*;

public class DeleteAStudentInManyToMany {

	public static void main(String[] args) {
		//create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session s = factory.getCurrentSession();
		//use the session object to save java object
		try {
			//start transaction
			s.beginTransaction();
			//get the course u want to remove from db
			Course c = s.get(Course.class, 14);
			//delete the course
			System.out.println("Deleting the course:"+c);
			s.delete(c);
			//commit transaction
			s.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}
}
