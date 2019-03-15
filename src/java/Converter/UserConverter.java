/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.UserDao;
import Entity.User;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kaleem
 */
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

    private UserDao userdao;
    private User user;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getUserDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        User c = (User) value;
        return c.getId().toString();
    }

    public UserDao getUserDao() {
        if (this.userdao == null) {
            this.userdao = new UserDao();
        }
        return userdao;
    }


    public User getUser() {
        if(this.user==null)
            this.user=new User();
        return user;
    }
  
}
