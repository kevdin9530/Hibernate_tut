package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Instructor;
import demo.hibernate.entity.InstructorDetail;
import demo.hibernate.entity.Student;

public class CreateOneToOne {

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
			
			//create an object
			Instructor ins = new Instructor("Kevin","Dinh","kevdin3095@gmail.com");
			//associate the object
			ins.setInstructorDetailID(new InstructorDetail("MyKevinYoutube","Coding n gaming"));
			
			//start transaction
			s.beginTransaction();
			//save the object 
			s.save(ins);
			
			//commit transaction
			s.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}
}
