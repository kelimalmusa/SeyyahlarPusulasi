/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Group;
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
public class GroupDao {

    private DBConnection dbconnection;
    private Connection coneection;
    private UserDao userdao;

    public LinkedList<Group> getGroups() {
        LinkedList<Group> groupList = new LinkedList<>();
        try {
            PreparedStatement pst = this.getConeection().prepareStatement("select *  from `Group`");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Group tmp = new Group();
                tmp.setId(rs.getLong("id"));
                tmp.setUser_group(rs.getString("User_Group"));
                groupList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return groupList;
    }

    public LinkedList<Group> getGroups(int page, int pageSize) {
        LinkedList<Group> groupList = new LinkedList<>();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConeection().prepareStatement("select *  from `group` limit ?,?");
            pst.setLong(1, start);
            pst.setLong(2, pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Group tmp = new Group();
                tmp.setId(rs.getLong("id"));
                tmp.setUser_group(rs.getString("User_Group"));
                groupList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return groupList;
    }

    public void delete(Group group) {
        try {
            PreparedStatement pst1 = this.getConeection().prepareStatement("delete from `group` where `id`=?");
            pst1.setLong(1, group.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Group group) {
        try {
            PreparedStatement pst1 = this.getConeection().prepareStatement("insert into `group` (`User_group`) values (?)");
            pst1.setString(1, group.getUser_group());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(Group group) {
        try {
            PreparedStatement pst2 = this.getConeection().prepareStatement("UPDATE `group` SET `User_Group`=? WHERE  `id`=?");
            pst2.setString(1, group.getUser_group());
            pst2.setLong(2, group.getId());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Group find(Long id) {
        Group g = null;
        try {
            PreparedStatement pst = this.getConeection().prepareStatement("select * from `group` where `id` =?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                g = new Group();
                g.setId(rs.getLong("id"));
                g.setUser_group(rs.getString("User_Group"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return g;
    }

    public UserDao getUserdao() {
        return userdao;
    }

    public void setUserdao(UserDao userdao) {
        this.userdao = userdao;
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

    public Connection getConeection() {
        if (this.coneection == null) {
            this.coneection = this.getDbconnection().connect();
        }

        return coneection;
    }

    public void setConeection(Connection coneection) {
        this.coneection = coneection;
    }

}
