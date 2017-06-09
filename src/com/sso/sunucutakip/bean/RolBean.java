package com.sso.sunucutakip.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sso.sunucutakip.dao.DAO;
import com.sso.sunucutakip.entitiy.Rol;

@ManagedBean
@SessionScoped
public class RolBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rol rolAdd;
	private List<Rol> rolList;
	private Rol secilenRol;

	@PostConstruct
	public void init() {
		this.rolList = DAO.getInstance().rolListele();
		this.rolAdd = new Rol();
		this.secilenRol = new Rol();
		
		
		
	}

	public void rolEkle() {

		DAO.getInstance().rolEkle(rolAdd);
		
		this.rolList = DAO.getInstance().rolListele();
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

	public List<Rol> getRolList() {
		return rolList;
	}

	public void setRolList(List<Rol> rolList) {
		this.rolList = rolList;
	}

	public Rol getSecilenRol() {
		return secilenRol;
	}

	public void setSecilenRol(Rol secilenRol) {
		this.secilenRol = secilenRol;
	}

	
	
}
