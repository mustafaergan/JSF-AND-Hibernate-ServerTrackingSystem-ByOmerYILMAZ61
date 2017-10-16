package com.sso.sunucutakip.entitiy;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sunucu {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "name",unique=true)
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="sunucu_personel",joinColumns= @JoinColumn(name = "sunucu_id"),
			inverseJoinColumns = @JoinColumn(name = "personel_id"))
	
	private List<Personel> sorumluPersonel;
	
	@Column(name = "Ram_Miktari")
	private int ramMiktari;
	
	@Column(name = "Cpu_Miktari")
	private int cpuMiktari;
	
	@Column(name = "Isletim_Sistemi")
	private String isletimSistemi;
	
	@Column(name = "Rdp_Adresi")
	private String rdpAdresi;
	
	@Column(name = "Aciklama")
	private String aciklama;
	
	@OneToOne
	private Personel sorumlandiranKisi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Personel> getSorumluPersonel() {
		return sorumluPersonel;
	}

	public void setSorumluPersonel(List<Personel> sorumluPersonel) {
		this.sorumluPersonel = sorumluPersonel;
	}

	public int getRamMiktari() {
		return ramMiktari;
	}

	public void setRamMiktari(int ramMiktari) {
		this.ramMiktari = ramMiktari;
	}

	public int getCpuMiktari() {
		return cpuMiktari;
	}

	public void setCpuMiktari(int cpuMiktari) {
		this.cpuMiktari = cpuMiktari;
	}

	public String getIsletimSistemi() {
		return isletimSistemi;
	}

	public void setIsletimSistemi(String isletimSistemi) {
		this.isletimSistemi = isletimSistemi;
	}

	public String getRdpAdresi() {
		return rdpAdresi;
	}

	public void setRdpAdresi(String rdpAdresi) {
		this.rdpAdresi = rdpAdresi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Personel getSorumlandiranKisi() {
		return sorumlandiranKisi;
	}


	public void setSorumlandiranKisi(Personel sorumlandiranKisi) {
		this.sorumlandiranKisi = sorumlandiranKisi;
	}
	
	
	
	
}
