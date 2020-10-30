
package com.ulicode.posts.app;

import java.util.Scanner;

/**
 *
 * @author erick
 */
public class postService {
    public static void createPost() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your post");
        String post = sc.nextLine();
        
        System.out.println("Author: ");
        String author = sc.nextLine();
        
        Post register = new Post();
        register.setPost(post);
        register.setAuthor_post(author);
        PostDAO.createPost(register);
    }
    public static void getPosts() {
        
    }
    public static void deletePost() {
        
    }
    public static void updatePost() {
        
    }        
}
