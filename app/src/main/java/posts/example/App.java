package posts.example;
import java.sql.Connection;
import java.util.Scanner;

/** 
 * @author Neil
 * */
public class App {
    public static void main(String[] args) {
        //Post post = new Post();
        //post.setId(1);
        //System.out.println(post.getId());
        Scanner sc = new Scanner(System.in);
        // int option;
        Connect connection = new Connect();
        try(Connection cn = connection.get_connection()) {
        }catch(Exception e) {
            System.out.println(e);
        }
        int option = 0;
        while(true) {
            System.out.println("-------------------");
            System.out.println(" 1. Create a post");
            System.out.println(" 2. Get all posts");
            System.out.println(" 3. Delete a post");
            System.out.println(" 4. Update a post");
            System.out.println(" 5. Exit");
            System.out.println("-------------------");
            if(sc.hasNextInt()) {
                option = sc.nextInt();
            } 
            int choice = option;
            switch (choice) {
                case 1:
                    PostService.createPost();
                    break;
                case 2:
                    PostService.getPosts();
                    break;
                case 3: 
                    PostService.deletePost();
                    break;
                case 4:
                    PostService.updatePost();
                    break;
                case 5:
                    System.exit(0);
                default: 
                    System.out.println("Try again");
            }
        }
    }
}
