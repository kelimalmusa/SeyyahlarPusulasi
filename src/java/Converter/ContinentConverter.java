/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.ContinentDao;
import Entity.Continent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kaleem
 */
@FacesConverter(value = "continentConverter")
public class ContinentConverter implements Converter {

    private ContinentDao continentDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getContinentDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object arg2) {
        Continent c = (Continent) arg2;
        return c.getId().toString();
    }

    public ContinentDao getContinentDao() {
        if (this.continentDao == null) {
            this.continentDao = new ContinentDao();
        }
        return continentDao;
    }

}
