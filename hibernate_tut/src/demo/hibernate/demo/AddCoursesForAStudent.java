package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.*;

public class AddCoursesForAStudent {

	public static void main(String[] args) {
		//create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session
		Session s = factory.getCurrentSession();
		//use the session object to save java object
		try {
			//start transaction
			s.beginTransaction();
			//create course
			Course someCourse = new Course("Piano - How to play in 1 hours");
			//add some review
			someCourse.add(new Review("Great course"));
			someCourse.add(new Review("Amazing! I know how to play piano after one hour"));
			someCourse.add(new Review("Not the worst but not the best course either"));
			//save the course
			System.out.println("Saving the course and its review");
			System.out.println(someCourse);
			System.out.println(someCourse.getReview());
			s.save(someCourse);		
			//commit transaction
			s.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}
}
