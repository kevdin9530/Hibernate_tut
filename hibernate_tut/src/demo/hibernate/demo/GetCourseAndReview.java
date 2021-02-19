package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.*;

public class GetCourseAndReview {

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
			//get a student from db
			Student st = s.get(Student.class, 7);
			System.out.println("Loaded student:" +st);
			System.out.println("The course the student current have:"+st.getCourses());
			
			
			//commit transaction
			s.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}
}
