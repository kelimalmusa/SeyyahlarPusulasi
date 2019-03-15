/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.DistrictDao;
import Entity.District;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kaleem
 */
@FacesConverter(value = "districtConverter")
public class DistrictConverter implements Converter {

    private DistrictDao districtdao;
    private District District;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDistrictDao().findDistricts();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        District c = (District) value;
        return c.getId().toString();
    }

    public DistrictDao getDistrictDao() {
        if (this.districtdao == null) {
            this.districtdao = new DistrictDao();
        }
        return districtdao;
    }


    public District getDistrict() {
        if(this.District==null)
            this.District=new District();
        return District;
    }
    

}
