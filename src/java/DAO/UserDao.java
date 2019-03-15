/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.City;
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
public class UserDao {

    private DBConnection dbconnection;
    private Connection connection;
    private CityDao citydao;
    private Group group;
    private GroupDao groupDao;

    public User loginControl(String name, String password) {
        User user = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from Users where Name=? and Password=?");
            pst.setString(1, name);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            rs.next();
            user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("Name"));
            user.setSurname(rs.getString("Surname"));
            user.setEmail(rs.getString("Email"));
            user.setPassword(rs.getString("Password"));
            user.setCountry(rs.getString("Country"));
            user.setPhone(rs.getLong("Phone_Number"));
            user.setUser_group(this.getGroupDao().find(rs.getLong("Group_id")));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public LinkedList<User> getUsers() {
        LinkedList<User> userList = new LinkedList();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from users");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User tmp = new User();
                tmp.setId(rs.getLong("id"));
                tmp.setName(rs.getString("Name"));
                tmp.setSurname(rs.getString("Surname"));
                tmp.setEmail(rs.getString("Email"));
                tmp.setPassword(rs.getString("Password"));
                tmp.setCountry(rs.getString("Country"));
                tmp.setPhone(rs.getLong("Phone_Number"));
                tmp.setFavoriler(this.getCitydao().getFavoriler(tmp.getId()));
                tmp.setUser_group(this.getGroupDao().find(rs.getLong("Group_id")));
                userList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }
    public LinkedList<User> getUserList(Long city_id) {
        LinkedList<User> userList = new LinkedList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from user_city where City_id=?");
            pst.setLong(1, city_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                userList.add(this.find(rs.getLong("User_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public LinkedList<User> getUsers(int page, int pageSize) {
        LinkedList<User> userList = new LinkedList();
        int start = 0;
        start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from users limit ? ,?");
            pst.setInt(1, start);
            pst.setInt(2, pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User tmp = new User();
                tmp.setId(rs.getLong("id"));
                tmp.setName(rs.getString("Name"));
                tmp.setSurname(rs.getString("Surname"));
                tmp.setEmail(rs.getString("Email"));
                tmp.setPassword(rs.getString("Password"));
                tmp.setCountry(rs.getString("Country"));
                tmp.setPhone(rs.getLong("Phone_Number"));
                tmp.setFavoriler(this.getCitydao().getFavoriler(tmp.getId()));
                tmp.setUser_group(this.getGroupDao().find(rs.getLong("Group_id")));
                userList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public void delete(User user) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("delete from users where id=?");
            pst1.setLong(1, user.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(User user) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("UPDATE users SET Name=? , Surname=? , Phone_Number=? , Email=? , Country=? , Password=?,Group_id=? Where id=?");
            pst.setString(1, user.getName());
            pst.setString(2, user.getSurname());
            pst.setLong(3, user.getPhone());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getCountry());
            pst.setString(6, user.getPassword());
            pst.setLong(7, user.getUser_group().getId());
            pst.setLong(8, user.getId());
            pst.executeUpdate();

            pst = this.getConnection().prepareStatement("delete from user_city where User_id=?");
            pst.setLong(1, user.getId());
            pst.executeUpdate();
            for (City city : user.getFavoriler()) {
                pst = this.getConnection().prepareStatement("insert into user_city (User_id,City_id) values (?,?)");
                pst.setLong(1, user.getId());
                pst.setLong(2, city.getId());

                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User find(Long id) {
        User user = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from Users where id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("Name"));
            user.setSurname(rs.getString("Surname"));
            user.setEmail(rs.getString("Email"));
            user.setPassword(rs.getString("Password"));
            user.setCountry(rs.getString("Country"));
            user.setPhone(rs.getLong("Phone_Number"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public void create(User user) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into users (Name,Surname,Email,Password,Country,Phone_Number) values (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getName());
            pst.setString(2, user.getSurname());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getCountry());
            pst.setLong(6, user.getPhone());
            pst.executeUpdate();

            Long user_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                user_id = gk.getLong(1);
            }
            for (City city : user.getFavoriler()) {
                pst = this.getConnection().prepareStatement("insert into user_city (User_id,City_id) values (?,?)");
                pst.setLong(1, user_id);
                pst.setLong(2, city.getId());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public GroupDao getGroupDao() {
        if (this.groupDao == null) {
            this.groupDao = new GroupDao();
        }
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Group getGroup() {
        if (this.group == null) {
            this.group = new Group();
        }
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public CityDao getCitydao() {
        if (this.citydao == null) {
            this.citydao = new CityDao();
        }
        return citydao;
    }

}
