package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Instructor;
import demo.hibernate.entity.InstructorDetail;
import demo.hibernate.entity.Student;

public class GetInstructorFromInstructorDetail {

	public static void main(String[] args) {
		//create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
		Session s = factory.getCurrentSession();
		//use the session object to save java object
		try {
			
			//start transaction
			s.beginTransaction();
			//get instructorDetail according to id
			InstructorDetail id = s.get(InstructorDetail.class, 333);
			
			//get instructor from instructor detail
			System.out.println(id.toString());
			
			//commit transaction
			s.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}
}
