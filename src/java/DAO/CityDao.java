/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.City;
import Entity.User;
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
public class CityDao {

    private DBConnection dbconnection;
    private Connection connection;

    private CountryDao countrydao;
    private DocumentDao documentDo;
    private UserDao userDoa;

    public LinkedList<City> getCity() {
        LinkedList<City> cityList = new LinkedList();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from cities");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                City tmp = new City();
                tmp.setId(rs.getLong("id"));
                tmp.setCity(rs.getString("City_Name"));
                tmp.setCoountry(this.getCountrydao().find(rs.getLong("Country_id")));
                tmp.setText(rs.getString("Text"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                tmp.setUserList(this.getUserDoa().getUserList(tmp.getId()));

                cityList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cityList;
    }

    public LinkedList<City> getCity(int page, int pageSize) {
        LinkedList<City> cityList = new LinkedList();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from cities limit ?,?");
            pst.setInt(1, start);
            pst.setInt(2, pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                City tmp = new City();
                tmp.setId(rs.getLong("id"));
                tmp.setCity(rs.getString("City_Name"));
                tmp.setCoountry(this.getCountrydao().find(rs.getLong("Country_id")));
                tmp.setText(rs.getString("Text"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                tmp.setUserList(this.getUserDoa().getUserList(tmp.getId()));
                cityList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cityList;
    }

    public void delete(City city) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("delete from cities where id=?");
            pst1.setLong(1, city.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(City city) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("insert into cities (city_name,country_id,Text,File_id) values (?,?,?,?)");
            pst1.setString(1, city.getCity());
            pst1.setLong(2, city.getCoountry().getId());
            pst1.setString(3, city.getText());
            pst1.setLong(4, city.getDocument().getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(City city) {
        try {
            PreparedStatement pst2 = this.getConnection().prepareStatement("UPDATE cities SET City_Name=? , Country_id=? ,Text=?, File_id=? WHERE  id=?");
            pst2.setString(1, city.getCity());
            pst2.setLong(2, city.getCoountry().getId());
            pst2.setString(3, city.getText());
            pst2.setLong(4, city.getDocument().getId());
            pst2.setLong(5, city.getId());

            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public City find(Long id) {
        City city = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from cities where id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            city = new City();
            city.setId(rs.getLong("id"));
            city.setCity(rs.getString("City_Name"));
            city.setText(rs.getString("Text"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return city;
    }

    public LinkedList<City> getFavoriler(Long id) {
        LinkedList<City> favoriler = new LinkedList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from user_city where User_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                favoriler.add(this.find(rs.getLong("City_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return favoriler;
    }

    public CountryDao getCountrydao() {
        if (this.countrydao == null) {
            this.countrydao = new CountryDao();
        }
        return countrydao;
    }

    public void setCountrydao(CountryDao countrydao) {
        this.countrydao = countrydao;
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

    public UserDao getUserDoa() {
        if (this.userDoa == null) {
            this.userDoa = new UserDao();
        }
        return userDoa;
    }

    public void setUserDoa(UserDao userDoa) {
        this.userDoa = userDoa;
    }

}
