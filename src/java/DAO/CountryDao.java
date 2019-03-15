/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Country;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author kaleem
 */
public class CountryDao {

    private DBConnection dbconnection;
    private Connection connection;
    private DocumentDao documentDo;
    private ContinentDao continentdao;

    public LinkedList<Country> getCountries() {
        LinkedList<Country> countryList = new LinkedList();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from countries");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Country tmp = new Country();
                tmp.setId(rs.getLong("id"));
                tmp.setCountry_Name(rs.getString("Country_name"));
                tmp.setContinent(this.getContinentdao().find(rs.getLong("Continent_id")));
                tmp.setText(rs.getString("Text"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                countryList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return countryList;
    }

    public LinkedList<Country> getCountries(int page, int pageSize) {
        LinkedList<Country> countryList = new LinkedList();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from countries limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Country tmp = new Country();
                tmp.setId(rs.getLong("id"));
                tmp.setCountry_Name(rs.getString("Country_Name"));
                tmp.setContinent(this.getContinentdao().find(rs.getLong("Continent_id")));
                tmp.setText(rs.getString("Text"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                countryList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return countryList;
    }

    public void delete(Country country) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("delete from countries where id=?");
            pst1.setLong(1, country.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Country country) {
        try {
            PreparedStatement pst2 = this.getConnection().prepareStatement("UPDATE countries SET Country_Name= ? , Continent_id= ? ,Text=?, File_id=? WHERE  id=? ");
            pst2.setString(1, country.getCountry_Name());
            pst2.setLong(2, country.getContinent().getId());
            pst2.setString(3, country.getText());
            pst2.setLong(4, country.getDocument().getId());
            pst2.setLong(5, country.getId());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Country find(Long id) {
        Country country = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from countries where id= ?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            country = new Country();
            country.setId(rs.getLong("id"));
            country.setCountry_Name(rs.getString("Country_Name"));
            country.setText(rs.getString("Text"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return country;
    }

    public void create(Country country) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into countries (Country_Name,Continent_id,Text,File_id) values (?,?,?,?)");
            pst.setString(1, country.getCountry_Name());
            pst.setLong(2, country.getContinent().getId());
            pst.setString(3, country.getText());
            pst.setLong(4, country.getDocument().getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ContinentDao getContinentdao() {
        if (this.continentdao == null) {
            this.continentdao = new ContinentDao();
        }
        return continentdao;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getDbconnection().connect();

        }
        return connection;
    }

    public DBConnection getDbconnection() {
        if (this.dbconnection == null) {
            this.dbconnection = new DBConnection();
        }
        return dbconnection;
    }

    public DocumentDao getDocumentDo() {
        if (this.documentDo == null) {
            this.documentDo = new DocumentDao();
        }
        return documentDo;
    }

    public void setDocumentDo(DocumentDao documentDo) {

        this.documentDo = documentDo;
    }
}
