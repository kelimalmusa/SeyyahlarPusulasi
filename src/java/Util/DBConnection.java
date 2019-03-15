/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaleem
 */
public class DBConnection {

    public Connection connect() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/seyyahlarpusulasi?user=root&password=root");
            System.out.println("Baglanmaya calısıyor");
            System.out.println("---------------------------");
            System.out.println("---------------------------");
            System.out.println("---------------------------");
            System.out.println("---------------------------");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("---------------------------");
            System.out.println("---------------------------");
            System.out.println("baglanamadı");
            System.out.println("---------------------------");
            System.out.println("---------------------------");
        }
        return c;
    }

}
