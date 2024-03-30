//(Noah) marked as i made this 
public class Comment extends Post implements CommentInterface {
    private final Post parent;

    public Comment(String username, String text, int postNumber, Post parent) {
        super(username, text, postNumber);
        this.parent = parent;
    }

    public Post getParent() {
        return parent;
    }
}

