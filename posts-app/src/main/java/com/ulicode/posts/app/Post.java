/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulicode.posts.app;

/**
 *
 * @author erick
 */
public class Post {
    int id_post;
    String post;
    String author_post;
    String date_post;
    
    public Post() {
        
    }

    public Post(String post, String author_post, String date_post) {
        this.post = post;
        this.author_post = author_post;
        this.date_post = date_post;
    }
    

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAuthor_post() {
        return author_post;
    }

    public void setAuthor_post(String author_post) {
        this.author_post = author_post;
    }

    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
        this.date_post = date_post;
    }
    
}
