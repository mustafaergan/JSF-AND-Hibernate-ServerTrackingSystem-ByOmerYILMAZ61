package com.sso.sunucutakip.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.sso.sunucutakip.dao.DAO;
import com.sso.sunucutakip.entitiy.Kullanici;
import com.sso.sunucutakip.entitiy.Personel;
import com.sso.sunucutakip.entitiy.Rol;


@ManagedBean
@SessionScoped
public class KullaniciBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Rol> rolList;
	private List<Rol> selectedRols;
	private Rol rolAdd;
	private Rol secilenRol;
	private List<Kullanici> kullaniciList;
	
	private Kullanici kullaniciAdd;
	private Personel personelAdd;
	

	@PostConstruct
	public void init() {
		this.rolList = DAO.getInstance().rolListele();
		this.rolAdd = new Rol();
		this.secilenRol = new Rol();
		this.rolList=DAO.getInstance().rolListele();
		this.selectedRols= new ArrayList<>();
		this.kullaniciAdd=new Kullanici();
		this.personelAdd= new Personel();
		this.kullaniciList=DAO.getInstance().getKullaniciList();
	}

	public void rolEkle() {

		DAO.getInstance().rolEkle(rolAdd);
		
		this.rolList = DAO.getInstance().rolListele();
	}
	
	public void kullaniciEkle(){
		DAO.getInstance().ekle(personelAdd);
		kullaniciAdd.setPersonel(personelAdd);
		kullaniciAdd.setRol(selectedRols);
		DAO.getInstance().kullaniciEkle(kullaniciAdd);
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		
	}
	
	public void rolSil() {
		
		DAO.getInstance().rolSil(secilenRol);
		this.rolList = DAO.getInstance().rolListele();
	}
	
	

	public Rol getRolAdd() {
		return rolAdd;
	}

	public void setRolAdd(Rol rolAdd) {
		this.rolAdd = rolAdd;
	}

	public Rol getSecilenRol() {
		return secilenRol;
	}

	public void setSecilenRol(Rol secilenRol) {
		this.secilenRol = secilenRol;
	}
	
	
	public void setSelectedRols(List<Rol> selectedRols) {
		this.selectedRols = selectedRols;
	}
	
	public List<Rol> getSelectedRols() {
		return selectedRols;
	}
	
	public void setRolList(List<Rol> rolList) {
		this.rolList = rolList;
	}
	
	public List<Rol> getRolList() {
		return rolList;
	}

	public Kullanici getKulllaniciAdd() {
		return kullaniciAdd;
	}

	public void setKulllaniciAdd(Kullanici kulllaniciAdd) {
		this.kullaniciAdd = kulllaniciAdd;
	}

	public Personel getPersonelAdd() {
		return personelAdd;
	}

	public void setPersonelAdd(Personel personelAdd) {
		this.personelAdd = personelAdd;
	}

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public Kullanici getKullaniciAdd() {
		return kullaniciAdd;
	}

	public void setKullaniciAdd(Kullanici kullaniciAdd) {
		this.kullaniciAdd = kullaniciAdd;
	}
	
	
}
