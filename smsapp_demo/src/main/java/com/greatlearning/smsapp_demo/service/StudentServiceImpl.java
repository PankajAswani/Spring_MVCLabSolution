package com.greatlearning.smsapp_demo.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.greatlearning.smsapp_demo.entity.Student;

@Repository
public class StudentServiceImpl implements StudentService {
	private SessionFactory sessionFactory;
	private Session session;

	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	public List<Student> findAll() {
		Transaction transaction = session.beginTransaction();
		List<Student> students = session.createQuery("from Student", Student.class).list();
		transaction.commit();
		return students;
	}

	public Student findById(int id) {
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		transaction.commit();
		return student;
	}

	public void save(Student student) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(student);
		transaction.commit();
	}

	public void deleteById(int id) {
		Transaction transaction = session.beginTransaction();
		try {
			Student student = session.get(Student.class, id);
			session.delete(student);
		} finally {
			transaction.commit();
		}
	}
}