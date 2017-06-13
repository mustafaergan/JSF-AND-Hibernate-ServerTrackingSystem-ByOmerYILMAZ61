package com.sso.sunucutakip.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.sso.sunucutakip.bean.KullaniciBean;
import com.sso.sunucutakip.bean.SunucuBean;
import com.sso.sunucutakip.entitiy.Kullanici;
import com.sso.sunucutakip.entitiy.Personel;
import com.sso.sunucutakip.entitiy.Rol;

@FacesConverter("KullaniciConverter")
public class KullaniciConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                KullaniciBean service = (KullaniciBean) fc.getExternalContext().getSessionMap().get("kullaniciBean");
                int id = Integer.parseInt(value);
                service.getKullaniciList();
                return  getMyRol(id, service.getKullaniciList());
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid durum."));
            }
        }
        else {
            return null;
        }
    }
 
    private Kullanici getMyRol(int id, List<Kullanici> kullanicilList) {
		for (Kullanici kullanici : kullanicilList) {
			if(kullanici.getId() == id)
				return kullanici;
		}
		return null;
		
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Kullanici) object).getId());
        }
        else {
            return null;
        }
    }   
}         
