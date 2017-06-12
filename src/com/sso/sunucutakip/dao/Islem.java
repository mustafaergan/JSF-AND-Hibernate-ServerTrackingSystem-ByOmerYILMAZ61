package com.sso.sunucutakip.dao;

import java.util.ArrayList;
import java.util.List;

import com.sso.sunucutakip.entitiy.Kullanici;
import com.sso.sunucutakip.entitiy.Personel;
import com.sso.sunucutakip.entitiy.Rol;

public class Islem {

	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		Personel personel = new Personel();
		personel.setId(1);
		personel.setName("aqu");
		
		List<Rol> rolList = new ArrayList<>();
		Rol rol = new Rol();
		rol.setId(1);
		rol.setName("Admin");
		rolList.add(rol);
		
		Kullanici kullanici = new Kullanici();
		kullanici.setId(1);
		kullanici.setKullaniciAdi("aqu");
		kullanici.setPersonel(personel);
		kullanici.setRol(rolList);
		kullanici.setSifre("123");
		
	}
}
