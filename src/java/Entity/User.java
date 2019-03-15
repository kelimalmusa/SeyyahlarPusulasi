/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author kaleem
 */
public class User implements Serializable {

    private Long id;
    private String name;
    private String Surname;
    private String Email;
    private String password;
    private String country;

    private Long phone;
    private Group user_group;

    private LinkedList<City> favoriler;

    public User() {
    }

    public User(Long id, String name, String Surname, String Email, String password, String country, Long phone, Group user_group) {
        this.id = id;
        this.name = name;
        this.Surname = Surname;
        this.Email = Email;
        this.password = password;
        this.country = country;
        this.phone = phone;
        this.user_group = user_group;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public LinkedList<City> getFavoriler() {
        return favoriler;
    }

    public void setFavoriler(LinkedList<City> favoriler) {
        this.favoriler = favoriler;
    }

    public Group getUser_group() {
        return user_group;
    }

    public void setUser_group(Group user_group) {
        this.user_group = user_group;
    }

    @Override
    public String toString() {
        return name;
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
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        return true;
    }

}
