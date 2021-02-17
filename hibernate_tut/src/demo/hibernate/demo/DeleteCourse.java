package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Course;
import demo.hibernate.entity.Instructor;
import demo.hibernate.entity.InstructorDetail;
import demo.hibernate.entity.Student;

public class DeleteCourse {

	public static void main(String[] args) {
		// create SessionFactory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session s = factory.getCurrentSession();
		// use the session object to save java object
		try {

			// start transaction
			s.beginTransaction();
			//get the course with id 10
			Course delcourse = s.get(Course.class, 10);
			
			//delete course 
			s.delete(delcourse);

			
			//commit trans
			s.getTransaction().commit();
		} finally {
			s.close();
			factory.close();
		}
	}
}
