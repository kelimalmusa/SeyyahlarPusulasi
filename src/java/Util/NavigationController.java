/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author kaleem
 */
@Named
@RequestScoped
public class NavigationController implements Serializable{
    public String goToPage (String page){
        return "/Admin/"+page+"/"+page+"?faces-reirect=true";
    }
    
}
