/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.District;
import Entity.Document;
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
public class DistrictDao {

    private DBConnection dbconnection;
    private Connection connection;
    private DocumentDao documentDo;

    private CityDao citydao;

    public LinkedList<District> findDistricts() {
        LinkedList<District> districtList = new LinkedList();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from districts");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                District tmp = new District();
                tmp.setId(rs.getLong("id"));
                tmp.setDistrict_Name(rs.getString("District_Name"));
                tmp.setCity(this.getCitydao().find(rs.getLong("City_id")));
                tmp.setText(rs.getString("Text"));
                tmp.setDocumentList(this.getDocumentDo().getFiles(tmp.getId()));
                districtList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return districtList;
    }

    public LinkedList<District> findDistricts(int page, int pageSize) {
        LinkedList<District> districtList = new LinkedList();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from districts limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                District tmp = new District();
                tmp.setId(rs.getLong("id"));
                tmp.setDistrict_Name(rs.getString("District_Name"));
                tmp.setCity(this.getCitydao().find(rs.getLong("City_id")));
                tmp.setText(rs.getString("Text"));
                tmp.setDocumentList(this.getDocumentDo().getFiles(tmp.getId()));
                districtList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return districtList;
    }

    public void create(District district) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into districts (District_Name,City_id,Text) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, district.getDistrict_Name());
            pst.setLong(2, district.getCity().getId());
            pst.setString(3, district.getText());
            pst.executeUpdate();
            Long district_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                district_id = gk.getLong(1);
            }
            for (Document doc : district.getDocumentList()) {
                pst = this.getConnection().prepareStatement("insert into district_file (District_id,File_id) values (?,?)");
                pst.setLong(1, district_id);
                pst.setLong(2, doc.getId());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(District district) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("delete from districts where id=?");
            pst1.setLong(1, district.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(District district) {
        try {
            PreparedStatement pst2 = this.getConnection().prepareStatement("UPDATE districts SET District_Name= ? , City_id= ? ,Text=? WHERE  id= ? ");
            pst2.setString(1, district.getDistrict_Name());
            pst2.setLong(2, district.getCity().getId());
            pst2.setString(3, district.getText());
            pst2.setLong(4, district.getId());
            pst2.executeUpdate();

            pst2 = this.getConnection().prepareStatement("delete from district_file where District_id=?");
            pst2.setLong(1, district.getId());
            pst2.executeUpdate();
            for (Document doc : district.getDocumentList()) {
                pst2 = this.getConnection().prepareStatement("insert into district_file (District_id,File_id) values (?,?)");
                pst2.setLong(1, district.getId());
                pst2.setLong(2, doc.getId());

                pst2.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public CityDao getCitydao() {
        if (this.citydao == null) {
            this.citydao = new CityDao();
        }
        return citydao;
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
