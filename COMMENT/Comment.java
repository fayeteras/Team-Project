//(Noah) marked as i made this 
public class Comment extends Post implements CommentInterface {
    private final Post parent;

    public Comment(String username, String fileName, Post parent) {
        super(username, fileName);
        this.parent = parent;
    }

    public Post getParent() {
        return parent;
    }
}

