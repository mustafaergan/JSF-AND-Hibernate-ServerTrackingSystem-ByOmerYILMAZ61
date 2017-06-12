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
import com.sso.sunucutakip.entitiy.Personel;
import com.sso.sunucutakip.entitiy.Rol;

@FacesConverter("PersonelConverter")
public class PersonelConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                SunucuBean service = (SunucuBean) fc.getExternalContext().getSessionMap().get("sunucuBean");
                int id = Integer.parseInt(value);
                service.getPersonelList();
                return  getMyRol(id, service.getPersonelList());
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid durum."));
            }
        }
        else {
            return null;
        }
    }
 
    private Personel getMyRol(int id, List<Personel> personelList) {
		for (Personel personel : personelList) {
			if(personel.getId() == id)
				return personel;
		}
		return null;
		
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Personel) object).getId());
        }
        else {
            return null;
        }
    }   
}         
