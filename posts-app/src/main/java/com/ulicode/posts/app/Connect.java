package com.ulicode.posts.app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ulises
 */
public class Connect {
    public Connection get_connection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","140216");
            if(connection != null)
            {
                System.out.println("Connection Successfully");
            }
        }catch(SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}