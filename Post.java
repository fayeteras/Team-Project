public class Post implements PostInterface {
    private final String username;
    private int likes;
    private int dislikes;
    private String text;
    private int[] time;

    //private boolean edited? might be good to have some indicator of that.

    public Post(String username, String text) {
        this.username = username;
        this.text = text;
        likes = 0;
        dislikes = 0;
        //time = don't actually know how to do this
        //edited = false
    }
}
