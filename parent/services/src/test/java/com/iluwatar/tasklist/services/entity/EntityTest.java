package com.iluwatar.tasklist.services.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTest {

	EntityManagerFactory emf = null;

	public EntityTest() {
		emf = Persistence.createEntityManagerFactory("tasklistPU");
	}

//	public void createTask(Task task) {
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			em.getTransaction().begin();
//			em.persist(task);
//			em.getTransaction().commit();
//		}
//		catch (Throwable t) {
//			t.printStackTrace();
//			em.getTransaction().rollback();
//		}
//		finally {
//			em.close();
//		}
//	}

	public void createUser(User user) {
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}
		catch (Throwable t) {
			t.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
	
	public void close() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
	
	public static void main(String[] args) {

		try {
			
			EntityTest test = new EntityTest();

			User u = new User();
			u.setUsername("jukka");
			u.setPasswordHash("235gdsGDGw3ga342");
			test.createUser(u);
			
//			Task t = new Task();
//			t.setDescription("do this and that");
//			t.setDone(false);
//			t.setUser(u);
//			test.createTask(t);
			
			test.close();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		
	}

}
