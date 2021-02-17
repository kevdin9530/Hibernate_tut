package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Course;
import demo.hibernate.entity.Instructor;
import demo.hibernate.entity.InstructorDetail;
import demo.hibernate.entity.Student;

public class AddCourseToInstructor {

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
			
			
			//create the course
			Course c1=new Course("Blue Archive Weebo");
			Course c2=new Course("Priconne Weebo");
			Course c3=new Course("Genshin Impact weeb");
			//add course to the instructor
			ins.add(c1);
			ins.add(c2);
			ins.add(c3);

			//save the course
			s.save(c1);
			s.save(c2);
			s.save(c3);
			// commit transaction
			s.getTransaction().commit();

		} finally {
			s.close();
			factory.close();
		}
	}
}
