/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author kaleem
 */
public class District {

    private Long id;
    private String district_Name;
    private City city;
    private LinkedList<Document> documentList;
    private String text;

    public District(Long id, String district_Name, City city, String text) {
        this.id = id;
        this.district_Name = district_Name;
        this.city = city;
        this.text = text;
    }

    public District() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict_Name() {
        return district_Name;
    }

    public void setDistrict_Name(String district_Name) {
        this.district_Name = district_Name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LinkedList<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(LinkedList<Document> documentList) {
        this.documentList = documentList;
    }

    @Override
    public String toString() {
        return "District{" + "id=" + id + ", district_Name=" + district_Name + ", city=" + city + ", text=" + text + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final District other = (District) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
