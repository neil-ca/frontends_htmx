package com.ulicode.posts.app;

import java.sql.Connection;

/**
 *
 * @author ulises
 */
public class main {
    public static void main(String[] args) {
        Connect connection = new Connect();
        
        try(Connection cn = connection.get_connection()) {
            
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
