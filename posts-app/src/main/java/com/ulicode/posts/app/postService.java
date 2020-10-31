
package com.ulicode.posts.app;

import java.util.Scanner;

/**
 *
 * @author ulises
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
        PostDAO.getPosts();
    }
    public static void deletePost() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the ID to delete");
        int id_post = sc.nextInt();
        PostDAO.deletePost(id_post);
    }
    public static void updatePost() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your new post");
        String post = sc.nextLine();
        System.out.println("Write the ID of post to edit");
        int id_post = sc.nextInt();
        Post update = new Post();
        update.setId_post((id_post));
        update.setPost(post);
        PostDAO.updatePost(update);
    }        
}
