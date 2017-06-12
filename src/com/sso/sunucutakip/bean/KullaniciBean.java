package com.sso.sunucutakip.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

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
	private List<Rol> degistirilcekRols;
	private List<Personel> personelList;
	
	

	private Kullanici kullaniciAdd;
	private Personel personelAdd;


	private static KullaniciBean uniqueInstance;

	public static KullaniciBean getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new KullaniciBean();
		}
		return uniqueInstance;
	}
	
	
	@PostConstruct
	public void init() {
		this.degistirilcekRols = new ArrayList<>();
		this.rolList = DAO.getInstance().rolListele();
		this.rolAdd = new Rol();
		this.secilenRol = new Rol();
		this.rolList = DAO.getInstance().rolListele();
		this.selectedRols = new ArrayList<>();
		this.kullaniciAdd = new Kullanici();
		this.personelAdd = new Personel();
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		this.personelList=DAO.getInstance().getPersonelList();
	}

	public void rolEkle() {

		DAO.getInstance().rolEkle(rolAdd);

		this.rolList = DAO.getInstance().rolListele();
	}

	public void kullaniciEkle() {
		DAO.getInstance().ekle(personelAdd);
		this.personelList=DAO.getInstance().getPersonelList();
		kullaniciAdd.setPersonel(personelAdd);
		kullaniciAdd.setRol(selectedRols);
		DAO.getInstance().kullaniciEkle(kullaniciAdd);
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		SunucuBean.getInstance().personelListDuzenle();
		
	}

	public List<Kullanici> kulList() {
		String kontrol = null;
		List<Kullanici> list = new ArrayList<>();
		for (int i = 0; i < this.kullaniciList.size(); i++) {
			if (kontrol != this.kullaniciList.get(i).getKullaniciAdi()) {
				kontrol = this.kullaniciList.get(i).getKullaniciAdi();
				list.add(this.kullaniciList.get(i));
			}
		}
		return list;
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

	public List<Rol> getDegistirilcekRols() {
		return degistirilcekRols;
	}

	public void setDegistirilcekRols(List<Rol> degistirilcekRols) {
		this.degistirilcekRols = degistirilcekRols;
	}

	public void onRowEdit(RowEditEvent event) {
		
		Kullanici kul = (Kullanici) event.getObject();
		kul.setRol(degistirilcekRols);;
		DAO.getInstance().updateKullanici(kul);
		this.kullaniciList=DAO.getInstance().getKullaniciList();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Kullanici) event.getObject()).getKullaniciAdi());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Personel> getPersonelList() {
		return personelList;
	}
	
	public void setPersonelList(List<Personel> personelList) {
		this.personelList = personelList;
	}
	
	
}
