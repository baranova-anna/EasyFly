package edu.bionic.easyfly.presentation;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
 


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.context.annotation.Scope;
 
@ManagedBean(name="language")
@SessionScoped 
public class LanguageBean implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private final static Locale RUSSIAN = new Locale("ru");
	private Locale localedef = Locale.ENGLISH;
	private String localeCode;
 
	private static Map<String,Object> countries;
	
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Русский", RUSSIAN);
	}
	
	public Locale getLocaledef() {
		return localedef;
	}

	public void setLocaledef(Locale localedef) {
		this.localedef = localedef;
	}


	public Map<String, Object> getCountriesInMap() {
		return countries;
	}
 
 
	public String getLocaleCode() {
		return localeCode;
	}
 
 
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
 
	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e){
 
		String newLocaleValue = e.getNewValue().toString();
             for (Map.Entry<String, Object> entry : countries.entrySet()) {
        	   if(entry.getValue().toString().equals(newLocaleValue)){ 
        		FacesContext.getCurrentInstance()
        			.getViewRoot().setLocale((Locale)entry.getValue());
        		        		localedef= (Locale) entry.getValue();	
        	   }
             }
	}
}
