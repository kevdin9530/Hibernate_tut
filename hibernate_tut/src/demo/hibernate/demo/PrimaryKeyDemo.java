package demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.hibernate.entity.Student;

public class PrimaryKeyDemo {

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
					
					
					//create 3 student object
					System.out.println("Create a student object");
					Student st1 = new Student("Kevin","Hoang","kevhoa3095@gmail.com");
					Student st2 = new Student("Carl","Dinh","cardin9530@gmail.com");
					Student st3 = new Student("Kevin","Thomsen","kevtho9530@gmail.com");
					//start transaction
					s.beginTransaction();
					//save student object
					System.out.println("Save student object");
					s.save(st1);
					s.save(st2);
					s.save(st3);
					//commit transaction
					s.getTransaction().commit();
					System.out.println("Done!");
				}finally {
					factory.close();
				}

	}

}
