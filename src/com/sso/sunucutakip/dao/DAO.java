package com.sso.sunucutakip.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Kullanici> cq = cb.createQuery(Kullanici.class);
		Root<Kullanici> kullaniciRoot = cq.from(Kullanici.class);

		Predicate kulPre = cb.and(cb.equal(kullaniciRoot.get("kullaniciAdi"), girisEntity.getUserName()),
				cb.equal(kullaniciRoot.get("sifre"), girisEntity.getPassWord()));
        cq.where(kulPre);

		Query<Kullanici> q = session.createQuery(cq);

		kullanici = q.getSingleResult();
		
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

		CriteriaQuery<Rol> cq = session.getCriteriaBuilder().createQuery(Rol.class);
		cq.from(Rol.class);
		List<Rol> list = session.createQuery(cq).getResultList();

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

		CriteriaQuery<Kullanici> cq = session.getCriteriaBuilder().createQuery(Kullanici.class);
		cq.from(Kullanici.class);
		List<Kullanici> list = session.createQuery(cq).getResultList();

		session.close();
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

		CriteriaQuery<Personel> cq = session.getCriteriaBuilder().createQuery(Personel.class);
		cq.from(Personel.class);
		List<Personel> list = session.createQuery(cq).getResultList();

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

		CriteriaQuery<Sunucu> cq = session.getCriteriaBuilder().createQuery(Sunucu.class);
		cq.from(Sunucu.class);
		List<Sunucu> list = session.createQuery(cq).getResultList();

		session.close();
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

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Sunucu> cq = cb.createQuery(Sunucu.class);
		Root<Sunucu> kullaniciRoot = cq.from(Sunucu.class);

		Predicate sunucuPre = cb.equal(kullaniciRoot.get("kullaniciAdi"), kullanici.getKullaniciAdi());
        cq.where(sunucuPre);

		Query<Sunucu> q = session.createQuery(cq);

		List<Sunucu> list = q.getResultList();

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
