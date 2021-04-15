package posts.example.model;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private int id;
    private String post;
    private String author_post;
    private String date_post;
    public Post() {}
    public Post(String post, String author_post, String date_post) {
        this.post = post;
        this.author_post = author_post;
        this.date_post = date_post;
    }
=======
public class Post {
    int id_post;
    String post;
    String author_post;
    String date_post;
    public Post() {}
>>>>>>> 00134bbd6c0022244367e55b118b6572eb2e8208
}

