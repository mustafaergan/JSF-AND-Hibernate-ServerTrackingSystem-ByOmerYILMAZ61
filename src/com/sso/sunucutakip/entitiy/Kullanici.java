package com.sso.sunucutakip.entitiy;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Kullanici {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "Kullanici_Adi")
	private String kullaniciAdi;
	
	@Column(name = "sifre")
	private String sifre;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="kullanici_rol",joinColumns= @JoinColumn(name = "kullanici_id"),
						inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private  List<Rol>  rol;
	
	@OneToOne
	private Personel personel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public List<Rol> getRol() {
		return rol;
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}

	public Personel getPersonel() {
		return personel;
	}

	public void setPersonel(Personel personel) {
		this.personel = personel;
	}
	
	
	
}
