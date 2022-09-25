package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
//		Create session Factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
//		Create Session
		Session session = factory.getCurrentSession();
		
		try {
//		create the object
			/*
			Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
			
			InstructorDetail tempInstructorDetail =new 
					InstructorDetail("http://www.luv2code.com/youtube",
							"luv2code!!!");
							*/
 
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			
			InstructorDetail tempInstructorDetail =new 
					InstructorDetail("http://www.youtube.com",
							"Guitar");
					
//		Associate  the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
//			Start a transaction
			session.beginTransaction();
			
//		Save the Instructor
//			Note: this will also save the detail object
//			because of cascadeType.ALL
			System.out.println("Saving Instructor:" + tempInstructor);
			session.save(tempInstructor);
			
			
//			commit Transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}
		finally {
			factory.close();
		}

	}

}
