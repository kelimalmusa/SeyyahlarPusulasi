/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Continent;
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
public class ContinentDao {

    private DBConnection dbconnection;
    private Connection connection;
    private DocumentDao documentDo;

    public LinkedList<Continent> findAll() {
        LinkedList<Continent> continentList = new LinkedList();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from continents");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Continent tmp = new Continent();
                tmp.setId(rs.getLong("id"));
                tmp.setContinent_name(rs.getString("Continent_Name"));
                tmp.setText(rs.getString("Text"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                continentList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return continentList;
    }

    public Continent find(Long id) {
        Continent cont = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from continents where id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            cont = new Continent();
            cont.setId(rs.getLong("id"));
            cont.setContinent_name(rs.getString("continent_name"));
            cont.setText(rs.getString("Text"));
            cont.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cont;
    }

    public void delete(Continent continent) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("delete from continents where id=?");
            pst1.setLong(1, continent.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Continent continent) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("insert into continents (continent_name,Text,File_id) values (?,?,?)");
            pst1.setString(1, continent.getContinent_name());
            pst1.setString(2, continent.getText());
            pst1.setLong(3, continent.getDocument().getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(Continent continent) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("UPDATE continents SET continent_name=? , Text=?, File_id=?  WHERE  id=?");
            pst1.setString(1, continent.getContinent_name());
            pst1.setString(2, continent.getText());
            pst1.setLong(3, continent.getDocument().getId());
            pst1.setLong(4, continent.getId());

            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LinkedList<Continent> findAll(int page, int pageSize) {
        LinkedList<Continent> continentList = new LinkedList();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from continents limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Continent tmp = new Continent();
                tmp.setId(rs.getLong("id"));
                tmp.setContinent_name(rs.getString("Continent_Name"));
                tmp.setText(rs.getString("Text"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                continentList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return continentList;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getDbconnection().connect();

        }
        return connection;
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

    public DBConnection getDbconnection() {
        if (this.dbconnection == null) {
            this.dbconnection = new DBConnection();
        }
        return dbconnection;
    }
}
