package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import demo.hibernate.entity.Course;
import demo.hibernate.entity.Instructor;
import demo.hibernate.entity.InstructorDetail;

public class EagerLazyFetch {

	public static void main(String[] args) {
		// create SessionFactory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session s = factory.getCurrentSession();
		// use the session object to save java object
		try {
			/*
			 * OPTION 1 : FETCH BEFORE SESSION CLOSE // start transaction
			 * s.beginTransaction(); // get the instructor from db Instructor ins =
			 * s.get(Instructor.class,4);
			 * 
			 * System.out.println("My instructor:" +ins); //get course from instructor n
			 * printout System.out.println("Courses of my instrcutor:"+ins.getCourse());
			 * 
			 * // commit transaction s.getTransaction().commit();
			 * 
			 * s.close();
			 * 
			 * //get course from instructor n printout
			 * System.out.println("Courses of my instrcutor:"+ins.getCourse());
			 */

			// OPTION 2: Hibernate query with HQL
			// start transaction
			s.beginTransaction();

			int theId = 4;
			Query<Instructor> query = s.createQuery(
					"select i from Instructor i " 
					+ "JOIN FETCH i.course " 
					+ "where i.id =:theInstructorId",
					Instructor.class);
			query.setParameter("theInstructorId", theId);

			Instructor ins = query.getSingleResult();

			System.out.println("My instructor:" + ins);
			// get course from instructor n printout
			//System.out.println("Courses of my instrcutor:" + ins.getCourse());

			// commit transaction
			s.getTransaction().commit();

			s.close();
			System.out.println("Session closes !!!!");
			// get course from instructor n printout
			System.out.println("Courses of my instrcutor:" + ins.getCourse());

		} finally {
			s.close();
			factory.close();
		}
	}
}
