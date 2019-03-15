/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Document;
import Util.DBConnection;
import com.mysql.jdbc.util.ResultSetUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author kaleem
 */
public class DocumentDao {

    private DBConnection dbconnection;
    private Connection connection;

    public LinkedList<Document> findAll() {
        LinkedList<Document> dList = new LinkedList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from files");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Document d = new Document();
                d.setId(rs.getLong("id"));
                d.setFilePath(rs.getString("URL"));
                d.setFileName(rs.getString("File_Name"));
                d.setFileType(rs.getString("File_Type"));
                dList.add(d);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dList;
    }

    public LinkedList<Document> findAll(int page, int pageSize) {
        LinkedList<Document> dList = new LinkedList<>();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from files limit ?,?");
            pst.setInt(1, start);
            pst.setInt(2, pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Document d = new Document();
                d.setId(rs.getLong("id"));
                d.setFilePath(rs.getString("URL"));
                d.setFileName(rs.getString("File_Name"));
                d.setFileType(rs.getString("File_Type"));
                dList.add(d);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dList;
    }

    public void delete(Document d) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from files where id =?");
            pst.setLong(1, d.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insert(Document d) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into files (URL,File_Name , File_Type) values (?,?,?)");
            pst.setString(1, d.getFilePath());
            pst.setString(2, d.getFileName());
            pst.setString(3, d.getFileType());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Document find(Long id) {
        Document document = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from files where id= ?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            document = new Document();
            document.setId(rs.getLong("id"));
            document.setFileName(rs.getString("File_Name"));
            document.setFilePath(rs.getString("URL"));
            document.setFileType(rs.getString("File_type"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return document;
    }

    public LinkedList<Document> getFiles(Long id) {
        LinkedList<Document> files = new LinkedList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from district_file where District_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                files.add(this.find(rs.getLong("File_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return files;
    }

    public DBConnection getDbconnection() {
        if (this.dbconnection == null) {
            this.dbconnection = new DBConnection();
        }
        return dbconnection;
    }

    public void setDbconnection(DBConnection dbconnection) {
        this.dbconnection = dbconnection;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getDbconnection().connect();

        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
