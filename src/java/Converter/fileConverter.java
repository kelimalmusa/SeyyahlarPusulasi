/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.DocumentDao;
import Entity.Document;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kaleem
 */
@FacesConverter (value = "fileConverter")
public class fileConverter implements Converter{

     private DocumentDao docdao;
     private Document doc;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDocdao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Document c = (Document) value;
        return c.getId().toString();
    }

    public DocumentDao getDocdao() {
        if(this.docdao==null)
            this.docdao=new DocumentDao();
        return docdao;
    }

    public void setDocdao(DocumentDao docdao) {
        this.docdao = docdao;
    }

    public Document getDoc() {
        if(this.doc==null)
            this.doc=new Document();
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    
}
