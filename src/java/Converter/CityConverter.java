/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import DAO.CityDao;
import Entity.City;
import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kaleem
 */
@FacesConverter(value = "cityConverter")
public class CityConverter implements Converter {

    private CityDao citydao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getCitydao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object arg2) {
        City c = (City) arg2;
        return c.getId().toString();
    }

    public CityDao getCitydao() {
        if (this.citydao == null) {
            this.citydao = new CityDao();
        }
        return citydao;
    }

}
