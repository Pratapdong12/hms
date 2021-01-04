package com.pratap.hms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.hms.model.Bill;
import com.pratap.hms.model.Patient;


@Repository
public class BillRepositoryImpl implements BillRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addBill(Bill bill) {
		
		getSession().merge(bill);
		
	}

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public void updateBill(Bill bill) {
		getSession().merge(bill);
		
	}

	@Override
	public void deleteBill(int id) {
		Bill bill = getBillById(id);
		if (bill != null) {
			getSession().delete(bill);
		}
	}

	@Override
	public Bill getBillById(int id) {
		return (Bill) getSession().get(Bill.class, id);
	}

	@Override
	public List<Bill> getAllBill() {
		return getSession().createCriteria(Bill.class).list();
	}
	@Override
	public List<Bill> getPatientBill(Patient pat) {
		return getSession().createCriteria(Bill.class).add(Restrictions.eq("patient",pat)).list();
	}
}
