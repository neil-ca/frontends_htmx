package com.ulicode.posts.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        
    }
    public static void deletePost(int id_post) {
        
    }
    public static void updatePost(Post post) {
        
    }
}
