package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
//		Create session Factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
//		Create Session
		Session session = factory.getCurrentSession();
		
		try {	
			
//			Start a transaction
			session.beginTransaction();
			
//		get the instructor by a primary key
			int theId= 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found instructor:" + tempInstructor);
			
//			Delete the instructor
			if(tempInstructor!=null) {
				System.out.println("Deleting:" +tempInstructor);
				
//				note: will also delete associated details object
//				because of cascadeType.ALL
				
				session.delete(tempInstructor);
			}
			
			
//			commit Transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}
		finally {
			factory.close();
		}

	}

}
