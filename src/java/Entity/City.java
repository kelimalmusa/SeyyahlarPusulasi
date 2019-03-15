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
public class City {

    private Long id;
    private String city;
    private Country Coountry;
    private Document document;
    private LinkedList<User> userList;
    private String text;

    public City() {
    }

    public City(Long id, String city, Country Coountry, String text) {
        this.id = id;
        this.city = city;
        this.Coountry = Coountry;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCoountry() {
        return Coountry;
    }

    public void setCoountry(Country Coountry) {
        this.Coountry = Coountry;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public LinkedList<User> getUserList() {
        return userList;
    }
    

    public void setUserList(LinkedList<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return city;
    }

    



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final City other = (City) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
