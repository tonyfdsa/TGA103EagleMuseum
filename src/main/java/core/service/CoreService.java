package core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static core.util.HibernateUtil.*;

public interface CoreService {
	
//	default Transaction beginTransacation() {
//		return getsession().beginTransaction();
//	}
//	
//	default void commit() {
//		getTransaction().commit();
//	}
//	
//	default void rollback() {
//		getTransaction().rollback();
//	}
	
	default Session getsession() {
		return getSessionFactory().getCurrentSession();
	}
	
	default Transaction getTransaction() {
		return getsession().getTransaction();
		
	}

}
