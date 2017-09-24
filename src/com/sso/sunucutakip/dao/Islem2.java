package com.sso.sunucutakip.dao;

import java.util.List;

import com.sso.sunucutakip.entitiy.Kullanici;
import com.sso.sunucutakip.entitiy.Rol;
import com.sso.sunucutakip.entitiy.Sunucu;

public class Islem2 {

	public static void main(String[] args) {

		DAO dao = new DAO();
		List<Kullanici> list = dao.getKullaniciList();

		for (Kullanici kullanici : list) {
			System.out.println(kullanici.getKullaniciAdi());
			for (Rol rol : kullanici.getRol()) {
				System.out.println(rol.getName());
			}
			System.out.println("*/*/**/*/*/*//*/*/*/*/*/*/*/**/*/");
		}

		List<Sunucu> listSun = dao.getSunucuList();

		for (Sunucu sunucu : listSun) {
			System.out.println(sunucu.getName());
			sunucu.getSorumluPersonel().get(0).getName();

		}

	}
}
