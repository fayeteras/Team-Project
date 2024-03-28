public interface CommentInterface {
    //(Savni) Takes a comment from the user and adds it to the post specified by the user
    public void addComment(Post post, Comment comment);
    
    //(Savni) Takes a comment from the user and deletes it from the post specified by the user
    //Be aware that only a post owner or comment owner can do this
    public void deleteComment(Post post, Comment comment);
    
    //(Savni) Takes a comment and increments the likes of it
    public void likeComment(Comment comment);

    //(Savni) Takes a comment and increments the dislikes of it
    public void dislikeComment(Comment comment);
}
