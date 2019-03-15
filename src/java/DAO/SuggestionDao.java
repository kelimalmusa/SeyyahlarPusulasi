/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Suggestion;
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
public class SuggestionDao {

    private DBConnection dbconnection;
    private Connection connection;
    private DocumentDao documentDo;

    public LinkedList<Suggestion> findAll() {
        LinkedList<Suggestion> suggestionList = new LinkedList();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from Our_Suggestion");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Suggestion tmp = new Suggestion();
                tmp.setId(rs.getLong("id"));
                tmp.setSuggestion(rs.getString("Our_Suggestion"));
                tmp.setComment(rs.getString("Comment"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                suggestionList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return suggestionList;
    }

    public LinkedList<Suggestion> findAll(int page, int pageSize) {
        LinkedList<Suggestion> suggestionList = new LinkedList();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from our_suggestion limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Suggestion tmp = new Suggestion();
                tmp.setId(rs.getLong("id"));
                tmp.setSuggestion(rs.getString("Our_Suggestion"));
                tmp.setComment(rs.getString("Comment"));
                tmp.setDocument(this.getDocumentDo().find(rs.getLong("File_id")));
                suggestionList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return suggestionList;
    }

    public void delete(Suggestion suggestion) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("delete from our_suggestion where id=?");
            pst1.setLong(1, suggestion.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Suggestion suggestion) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("insert into our_suggestion (Our_Suggestion,Comment,File_id) values (?,?,?)");
            pst1.setString(1, suggestion.getSuggestion());
            pst1.setString(2, suggestion.getComment());
            pst1.setLong(3, suggestion.getDocument().getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Suggestion suggestion) {
        try {
            PreparedStatement pst2 = this.getConnection().prepareStatement("UPDATE our_suggestion SET Our_Suggestion=? , Comment=?, File_id=? WHERE  id=?");
            pst2.setString(1, suggestion.getSuggestion());
            pst2.setString(2, suggestion.getComment());
            pst2.setLong(3, suggestion.getDocument().getId());
            pst2.setLong(4, suggestion.getId());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
