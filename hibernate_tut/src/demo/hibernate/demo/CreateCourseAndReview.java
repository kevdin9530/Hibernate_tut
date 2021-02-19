package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.*;

public class CreateCourseAndReview {

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
			//create course
			Course someCourse = new Course("Piano - How to play in 1 hours");
			
			//save the course
			System.out.println("Saving the course");
			s.save(someCourse);	
			
			//create student
			Student st1= new Student("Kevin","SomeOne","kevsom.sss@gaaass.sss");
			Student st2= new Student("Nguyen","Chong","ngucho.sss@gaaass.sss");
			//add student
			someCourse.add(st1);
			someCourse.add(st2);
			
			//save student
			System.out.println("Saving the students");
			s.save(st1);
			s.save(st2);
			System.out.println("Saved the student: "+someCourse.getStudent());
			//commit transaction
			s.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}
}
