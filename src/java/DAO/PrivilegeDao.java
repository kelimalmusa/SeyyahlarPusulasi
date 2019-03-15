/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Group;
import Entity.Privilege;
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
public class PrivilegeDao {

    private DBConnection dbconnection;
    private Connection connection;

    public Privilege find(Long id) {
        Privilege d = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from `privilege` where `id` =?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                d = new Privilege();
                d.setId(rs.getLong("id"));
                d.setC(rs.getBoolean("c"));
                d.setR(rs.getBoolean("r"));
                d.setU(rs.getBoolean("u"));
                d.setD(rs.getBoolean("d"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }


    public boolean createPrivi(Long group_id) {
        boolean yetki = false;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from `privilege` where `Group` = ? ");
            pst.setLong(1, group_id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            yetki = rs.getBoolean("C");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yetki;

    }

    public boolean updatePrivi(Long group_id) {
        boolean yetki = false;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from `privilege` where `Group` = ? ");
            pst.setLong(1, group_id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            yetki = rs.getBoolean("U");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yetki;

    }

    public boolean deletePrivi(Long group_id) {
        boolean yetki = false;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from `privilege` where `Group` = ? ");
            pst.setLong(1, group_id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            yetki = rs.getBoolean("D");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yetki;
    }

    public boolean readPrivi(Long group_id) {
        boolean yetki = false;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("Select * from `privilege` where `Group` = ? ");
            pst.setLong(1, group_id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            yetki = rs.getBoolean("R");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yetki;
    }

    public Group findGroup(Long id) {
        Group g = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from `group` where `id` =?");
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

    public LinkedList<Privilege> findAll() {
        LinkedList<Privilege> pList = new LinkedList<>();
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from `privilege`");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Privilege temp = new Privilege();
                temp.setId(rs.getLong("id"));
                temp.setGroup(this.findGroup(rs.getLong("Group")));
                temp.setC(rs.getBoolean("c"));
                temp.setR(rs.getBoolean("r"));
                temp.setU(rs.getBoolean("u"));
                temp.setD(rs.getBoolean("d"));
                pList.add(temp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pList;

    }

    public LinkedList<Privilege> findAll(int page, int pageSize) {
        LinkedList<Privilege> pList = new LinkedList<>();
        try {
            int start = (page - 1) * pageSize;
            PreparedStatement pst = this.getConnection().prepareStatement("select * from `privilege` limit ?,?");
            pst.setInt(1, start);
            pst.setInt(2, pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Privilege temp = new Privilege();
                temp.setId(rs.getLong("id"));
                temp.setGroup(this.findGroup(rs.getLong("Group")));
                temp.setC(rs.getBoolean("c"));
                temp.setR(rs.getBoolean("r"));
                temp.setU(rs.getBoolean("u"));
                temp.setD(rs.getBoolean("d"));
                pList.add(temp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pList;
    }

    public void create(Privilege privilege) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("insert into `privilege` (`Group` , `C` , `R` , `U` , `D`) values (?,?,?,?,?)");
            pst1.setObject(1, privilege.getGroup().getId());
            pst1.setBoolean(2, privilege.isC());
            pst1.setBoolean(3, privilege.isR());
            pst1.setBoolean(4, privilege.isU());
            pst1.setBoolean(5, privilege.isD());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Privilege privilege) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("update `privilege` set `Group` = ? , `C` = ? , `R` = ? , `U` = ? , `D` = ? where `id` = ?");
            pst1.setLong(1, privilege.getGroup().getId());
            pst1.setBoolean(2, privilege.isC());
            pst1.setBoolean(3, privilege.isR());
            pst1.setBoolean(4, privilege.isU());
            pst1.setBoolean(5, privilege.isD());
            pst1.setLong(6, privilege.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Privilege privilege) {
        try {
            PreparedStatement pst1 = this.getConnection().prepareStatement("delete from `privilege` where `id` = ?");
            pst1.setLong(1, privilege.getId());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setDbconnection(DBConnection dbconnection) {
        this.dbconnection = dbconnection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
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
}
