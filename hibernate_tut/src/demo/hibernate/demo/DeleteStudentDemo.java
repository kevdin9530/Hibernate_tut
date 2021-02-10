package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		//create SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session s = factory.getCurrentSession();
		//use the session object to save java object
		try {
			int id = 3;
			
			//begin transaction
			s.beginTransaction();
			//get student object
			Student st = s.get(Student.class, id);
			//delete student from database
			/*System.out.println("Delete student " + st +  " from database");
			s.delete(st);*/
			s.createQuery("delete from Student where id=5").executeUpdate();
			s.getTransaction().commit();
			System.out.println("Done!");
			
			//add
			
		}finally {
			factory.close();
		}
	}
}
