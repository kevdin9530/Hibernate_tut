package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Course;
import demo.hibernate.entity.Instructor;
import demo.hibernate.entity.InstructorDetail;
import demo.hibernate.entity.Student;

public class GetCourseFromInstructor {

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
			// get the instructor from db
			Instructor ins = s.get(Instructor.class,4);
			
			
			//get course from instructor n printout
			System.out.println(ins.getCourse());
			// commit transaction
			s.getTransaction().commit();

		} finally {
			s.close();
			factory.close();
		}
	}
}
