package com.ulicode.posts.app;

import java.sql.Connection;
import java.util.Scanner;

/**
 *
 * @author ulises
 */
public class main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("-------------------");
            System.out.println(" 1. Create a post");
            System.out.println(" 2. Get all posts");
            System.out.println(" 3. Delete a post");
            System.out.println(" 4. Update a post");
            System.out.println(" 5. Exit");
            System.out.println("-------------------");
            
            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    postService.createPost();
                    break;
                case 2:
                    postService.getPosts();
                    break;
                case 3: 
                    postService.deletePost();
                    break;
                case 4:
                    postService.updatePost();
                    break;
                default: 
                    break;
            }
        }while(option != 5);
        Connect connection = new Connect();
        
        try(Connection cn = connection.get_connection()) {
            
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
