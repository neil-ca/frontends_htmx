package com.ulicode.posts.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ulises
 */
public class PostDAO {
    public static void createPost(Post post) {
        Connect db_connect = new Connect();
        
        try(Connection connecttion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query="INSERT INTO posts (post, author_post) VALUES (?, ?);"; 
                ps = connecttion.prepareStatement(query);
                ps.setString(1, post.getPost());
                ps.setString(2, post.getAuthor_post());
                ps.executeUpdate();
                System.out.println("post created");
            }catch(SQLException ex) {
                System.out.println(ex);
            }
        }catch(SQLException e) {
            System.out.println(e);
        }
        
    }
    
    public static void getPosts() {
        Connect db_connect = new Connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try(Connection connecttion = db_connect.get_connection()) {
             String query = "SELECT * FROM posts";
             ps = connecttion.prepareStatement(query);
             rs = ps.executeQuery();
             while(rs.next()) {
                 System.out.println("ID: " + rs.getInt("id_post"));
                 System.out.println("Post: " + rs.getString("post"));
                 System.out.println("Author: "+ rs.getString("author_post"));
                 System.out.println("Date: "+ rs.getString("date_post"));
             }
        }catch(SQLException e) {
            System.out.println(e);
        }
    }
    public static void deletePost(int id_post) {
         Connect db_connect = new Connect();
         
         try(Connection connection = db_connect.get_connection()) {
             PreparedStatement ps = null;
             try {
                 String query = "DELETE FROM posts WHERE id_post = ?";
                 ps = connection.prepareStatement(query);
                 ps.setInt(1, id_post);
                 ps.executeUpdate();
                 System.out.println("Post deleted");
             }catch(SQLException e) {
                 System.out.println(e);
             }
         }catch(SQLException e) {
             System.out.println(e);
         }
    }
    public static void updatePost(Post post) {
        Connect db_connect = new Connect();
         
         try(Connection connection = db_connect.get_connection()) {
             PreparedStatement ps = null;
             try {
                String query="UPDATE posts SET post = ? WHERE id_post = ?"; 
                ps = connection.prepareStatement(query);
                ps.setString(1, post.getPost());
                ps.setInt(2, post.getId_post());
                ps.executeUpdate();
                 System.out.println("post updated!");
             }catch(SQLException ex) {
                 System.out.println(ex);
             }
         }catch(SQLException e) {
             System.out.println(e);
         }
    }
}
