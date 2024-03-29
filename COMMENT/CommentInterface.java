public interface CommentInterface {
    /*
    //(Savni) Takes a comment from the user and adds it to the post specified by the user
    public void addComment(Post post, Comment comment);

    //(Savni) Takes a comment from the user and deletes it from the post specified by the user
    //Be aware that only a post owner or comment owner can do this
    public void deleteComment(Post post, Comment comment);

    //(Savni) Takes a comment and increments the likes of it
    public void likeComment(Comment comment);

    //(Savni) Takes a comment and increments the dislikes of it
    public void dislikeComment(Comment comment);

    //(Sean) Stringafies the fields of the Comment class
    //Formatting can be seen in userComments.txt
    public String toString();
    */

    //(Noah) Comment is a child of post, so these would be inherited. That or they don't make sense. I added the only one in the comment class rn below.

    public Post getParent();
}
