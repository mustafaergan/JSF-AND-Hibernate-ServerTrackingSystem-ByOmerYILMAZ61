package com.sso.sunucutakip.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

	private static LoginBean uniqueInstance;

	public static LoginBean getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new LoginBean();
		}
		return uniqueInstance;
	}

	List<Kullanici> kullaniciList;

	@PostConstruct
	public void init() {
		this.girisEntity = new GirisEntity();
		this.kullaniciList = DAO.getInstance().getKullaniciList();
	}

	public String login() {

		this.kullanici = DAO.getInstance().checkUser(this.girisEntity);
		LoginBean.getInstance().setKullanici(this.kullanici);

		if (kullanici == null)
			return "fail.xhtml";
		else {
			this.kullaniciIcerde = true;
			return "iceri/anasayfa.xhtml?faces-redirect=true";
		}
	}
	
	public String cikisYap(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.kullaniciIcerde = false;
		return "/index.xhtml?faces-redirect=true";
	}
	

	public boolean adminMi() {
		for (Kullanici kullanici : kullaniciList) {
			if (kullanici.getId() == this.kullanici.getId()) {
				for (Rol rol : kullanici.getRol()) {
					if (rol.getId() == 1) {
						return true;
					}
				}
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
