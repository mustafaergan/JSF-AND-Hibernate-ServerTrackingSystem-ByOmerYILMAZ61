package com.sso.sunucutakip.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.sso.sunucutakip.dao.DAO;
import com.sso.sunucutakip.entitiy.Kullanici;
import com.sso.sunucutakip.entitiy.Personel;
import com.sso.sunucutakip.entitiy.Rol;
import com.sso.sunucutakip.entitiy.Sunucu;

@ManagedBean
@SessionScoped
public class SunucuBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Sunucu sunucuAdd;
	private List<Sunucu> sunucuList;
	private List<Personel> selectedPersonels;
	private List<Sunucu> gosterilcekList;
	private List<Kullanici> kullaniciList;
	private List<Sunucu> silinecekSunucuList;
	private List<Personel> degistirilecekPersonels;
	private Personel degistirilecekSorumlu;

	private static SunucuBean uniqueInstance;

	public static SunucuBean getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new SunucuBean();
		}
		return uniqueInstance;
	}

	@PostConstruct
	public void init() {
		this.sunucuAdd = new Sunucu();
		this.selectedPersonels = new ArrayList<>();
		this.sunucuList = DAO.getInstance().getSunucuList();
		this.gosterilcekList = new ArrayList<>();
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		this.silinecekSunucuList = new ArrayList<>();
		this.degistirilecekPersonels = new ArrayList<>();
		this.degistirilecekSorumlu = new Personel();

	}

	public String sunucuEkle() {
		this.sunucuAdd.setSorumluPersonel(this.selectedPersonels);
		this.sunucuAdd.setSorumlandiranKisi(LoginBean.getInstance().getKullanici().getPersonel());
		DAO.getInstance().sunucuEkle(this.sunucuAdd);
		this.sunucuList = DAO.getInstance().getSunucuList();
		return "sunucuListele.xhtml";

	}

	public void sunuculariSil() {
		DAO.getInstance().sunuculariSil(this.silinecekSunucuList);
		this.sunucuList = DAO.getInstance().getSunucuList();
	}

	public List<Sunucu> gosterilcekList() {

		this.gosterilcekList = new ArrayList<>();
		Kullanici kullanici = kullaniciVer(LoginBean.getInstance().getKullanici());

		for (Rol rol : kullanici.getRol()) {
			if (rol.getName().equals("admin")) {
				return sunucuList;
			}
		}
		for (Sunucu sunucu : sunucuList) {
			for (int i = 0; i < sunucu.getSorumluPersonel().size(); i++) {
				if (sunucu.getSorumluPersonel().get(i).getId() == kullanici.getPersonel().getId()) {
					this.gosterilcekList.add(sunucu);
				}
			}
		}
		return this.gosterilcekList;
	}

	private Kullanici kullaniciVer(Kullanici kullanici) {
		for (Kullanici kul : this.kullaniciList) {
			if (kul.getId() == kullanici.getId()) {
				return kul;
			}
		}
		return null;
	}

	public Sunucu getSunucuAdd() {
		return sunucuAdd;
	}

	public void setSunucuAdd(Sunucu sunucuAdd) {
		this.sunucuAdd = sunucuAdd;
	}


	public List<Personel> getSelectedPersonels() {
		return selectedPersonels;
	}

	public void setSelectedPersonels(List<Personel> selectedPersonels) {
		this.selectedPersonels = selectedPersonels;
	}

	public void setSunucuList(List<Sunucu> sunucuList) {
		this.sunucuList = sunucuList;
	}

	public List<Sunucu> getSunucuList() {
		return sunucuList;
	}


	public List<Sunucu> getSilinecekSunucuList() {
		return silinecekSunucuList;
	}

	public void setSilinecekSunucuList(List<Sunucu> silinecekSunucuList) {
		this.silinecekSunucuList = silinecekSunucuList;
	}

	public void onRowEdit(RowEditEvent event) {

		Sunucu sun = (Sunucu) event.getObject();
		if (degistirilecekPersonels.size() != 0) {
			sun.setSorumluPersonel(degistirilecekPersonels);
		}
		
		if (degistirilecekSorumlu.getName() != null) {
			sun.setSorumlandiranKisi(degistirilecekSorumlu);
		}
		
		DAO.getInstance().updateSunucu(sun);
		this.sunucuList = DAO.getInstance().getSunucuList();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Sunucu) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void setDegistirilecekPersonels(List<Personel> degistirilecekPersonels) {
		this.degistirilecekPersonels = degistirilecekPersonels;
	}

	public List<Personel> getDegistirilecekPersonels() {
		return degistirilecekPersonels;
	}	

	public void setDegistirilecekSorumlu(Personel degistirilecekSorumlu) {
		this.degistirilecekSorumlu = degistirilecekSorumlu;
	}

	public Personel getDegistirilecekSorumlu() {
		return degistirilecekSorumlu;
	}

}
