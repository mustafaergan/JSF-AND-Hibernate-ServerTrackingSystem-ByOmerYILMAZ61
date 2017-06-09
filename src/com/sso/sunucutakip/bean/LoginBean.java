package com.sso.sunucutakip.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sso.sunucutakip.dao.DAO;
import com.sso.sunucutakip.entitiy.GirisEntity;
import com.sso.sunucutakip.entitiy.Kullanici;
import com.sso.sunucutakip.entitiy.Rol;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GirisEntity girisEntity;
	private boolean kullaniciIcerde = false;
	private Kullanici kullanici;
	
	
	@PostConstruct
	public void init(){
		this.girisEntity= new GirisEntity();
		
	}
	
	public String login(){
		
		this.kullanici = DAO.getInstance().checkUser(this.girisEntity);
		
		if(kullanici == null)
			return"fail.xhtml";
		else {
			this.kullaniciIcerde=true;
			return "iceri/anasayfa.xhtml?faces-redirect=true";
			}
	}
	

	public boolean kontrolEt(){
		for (Rol rol : this.kullanici.getRol()) {
			if(rol.getName().equals("admin")){
				return true;
			}
		}
	return false;
	}
	
	

	
	public GirisEntity getGirisEntity() {
		return girisEntity;
	}

	public void setGirisEntity(GirisEntity girisEntity) {
		this.girisEntity = girisEntity;
	}

	public boolean isKullaniciIcerde() {
		return kullaniciIcerde;
	}

	public void setKullaniciIcerde(boolean kullaniciIcerde) {
		this.kullaniciIcerde = kullaniciIcerde;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	
	
}
