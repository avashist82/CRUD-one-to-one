package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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
			
			
//          Get the instructor Detail
			int theId = 3;
			InstructorDetail tempInstructorDetail =
					session.get(InstructorDetail.class, theId);
		
			
			
//			Print the instructor detail
		System.out.println("tempInstructorDetail:" +tempInstructorDetail);	
			
//			Print the associated instructor
			System.out.println("the associated instructor: " 
                          +tempInstructorDetail.getInstructor());
			
//			now lets delete the instructor detail
			System.out.println("Deleting temInstructorDetail:" +tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
//			remove the associated object reference
//			break bi directional link
			tempInstructorDetail.getInstructor()
                                 .setInstructorDetail(null);
			session.delete(tempInstructorDetail);
			
			
//			commit Transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}
		catch(Exception exe) {
			exe.printStackTrace();
		}

		finally {
//			handle connection leak issue
			session.close();
			
			factory.close();
		}

	}

}
