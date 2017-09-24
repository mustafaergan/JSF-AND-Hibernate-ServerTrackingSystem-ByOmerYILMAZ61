package com.sso.sunucutakip.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.sso.sunucutakip.entitiy.GirisEntity;
import com.sso.sunucutakip.entitiy.Kullanici;
import com.sso.sunucutakip.entitiy.Personel;
import com.sso.sunucutakip.entitiy.Rol;
import com.sso.sunucutakip.entitiy.Sunucu;

public class DAO {

	private static DAO uniqueInstance;

	public static DAO getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new DAO();
		}
		return uniqueInstance;
	}

	SessionFactory sessionFactory;

	public DAO() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public Kullanici checkUser(GirisEntity girisEntity) {
		Kullanici kullanici = null;
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Kullanici.class);

		criteria.add(Restrictions.eq("kullaniciAdi", girisEntity.getUserName()));

		criteria.add(Restrictions.eq("sifre", girisEntity.getPassWord()));

		List<Kullanici> list = criteria.list();

		if (list.size() > 0)
			kullanici = list.get(0);
		session.close();

		return kullanici;
	}

	public void rolEkle(Rol rol) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(rol);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Rol> rolListele() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Rol.class);

		List<Rol> list = criteria.list();

		session.close();
		return list;

	}

	public void rolSil(Rol secilenRol) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.delete(secilenRol);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void ekle(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Kullanici> getKullaniciList() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Kullanici.class);

		List<Kullanici> list = criteria.list();

		return list;
	}

	public void kullaniciEkle(Kullanici kullaniciAdd) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(kullaniciAdd);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Personel> personelListele() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Personel.class);

		List<Personel> list = criteria.list();

		session.close();
		return list;
	}

	public void sunucuEkle(Sunucu sunucuAdd) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(sunucuAdd);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Sunucu> getSunucuList() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Sunucu.class);

		List<Sunucu> list = criteria.list();

		return list;
	}

	public void updateKullanici(Kullanici object) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.merge(object);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Sunucu> getSunucuList(Kullanici kullanici) {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Sunucu.class);

		criteria.add(Restrictions.eq("kullaniciAdi", kullanici.getKullaniciAdi()));

		List<Sunucu> list = criteria.list();

		session.close();
		return list;
	}

	public void kullanicilariSil(List<Kullanici> silinecekKullanicilarList) {
		Kullanici kullanici = new Kullanici();

		for (int i = 0; i < silinecekKullanicilarList.size(); i++) {
			kullanici.setId(silinecekKullanicilarList.get(i).getId());

			Session session = sessionFactory.openSession();
			Transaction tx = null;
			try {

				tx = session.beginTransaction();
				session.delete(kullanici);
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

	}

	public void sunuculariSil(List<Sunucu> silinecekSunucuList) {
		Sunucu sunucu = new Sunucu();

		for (int i = 0; i < silinecekSunucuList.size(); i++) {
			sunucu.setId(silinecekSunucuList.get(i).getId());

			Session session = sessionFactory.openSession();
			Transaction tx = null;
			try {

				tx = session.beginTransaction();
				session.delete(sunucu);
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

	}

	public void updateSunucu(Sunucu sun) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.merge(sun);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
