package com.sso.sunucutakip.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	private List<Personel> personelList;
	private List<Personel> selectedPersonels;
	private List<Sunucu> gosterilcekList;
	private List<Kullanici> kullaniciList;
	
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
		this.personelList = DAO.getInstance().getPersonelList();
		this.selectedPersonels = new ArrayList<>();
		this.sunucuList = DAO.getInstance().getSunucuList();
		this.gosterilcekList = new ArrayList<>();
		this.kullaniciList=DAO.getInstance().getKullaniciList();
	}

	public void sunucuEkle() {
		this.sunucuAdd.setSorumluPersonel(this.selectedPersonels);
		this.sunucuAdd.setSorumlandiranKisi(LoginBean.getInstance().getKullanici().getPersonel());
		DAO.getInstance().sunucuEkle(this.sunucuAdd);
		this.sunucuList = DAO.getInstance().getSunucuList();

	}

	public List<Sunucu> gosterilcekList() {

		Kullanici kullanici = kullaniciVer(LoginBean.getInstance().getKullanici());
		
		for (Rol rol : kullanici.getRol()) {
			if (rol.getName().equals("admin")) {
				return sunucuList;
			}
		}
		for (Sunucu sunucu : sunucuList) {
			for (int i = 0; i < sunucu.getSorumluPersonel().size() ; i++) {
				if(sunucu.getSorumluPersonel().get(i).getId()==kullanici.getPersonel().getId()){
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

	public List<Sunucu> sunList(List<Sunucu> gosterilcekList) {
		String kontrol = null;
		List<Sunucu> list = new ArrayList<>();
		for (int i = 0; i < gosterilcekList.size(); i++) {
			if (kontrol != gosterilcekList.get(i).getName()) {
				kontrol = gosterilcekList.get(i).getName();
				list.add(gosterilcekList.get(i));
			}
		}
		return list;
	}
	
	

	public Sunucu getSunucuAdd() {
		return sunucuAdd;
	}

	public void setSunucuAdd(Sunucu sunucuAdd) {
		this.sunucuAdd = sunucuAdd;
	}

	public List<Personel> getPersonelList() {
		return personelList;
	}

	public void setPersonelList(List<Personel> personelList) {
		this.personelList = personelList;
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

	public void personelListDuzenle() {
		this.personelList=DAO.getInstance().getPersonelList();
		
	}
 
}
