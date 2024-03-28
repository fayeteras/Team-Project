public class Comment extends Post implements CommentInterface {
    private final Post parent;

    public Comment(String username, String text, Post parent) {
        super(username, text);
        this.parent = parent;
    }
}

