public interface CommentInterface {
    void addComment(Post post, Comment comment); // Add comment to a post

    void deleteComment(Post post, Comment comment); // Delete comment from a post
    void upvoteComment(Comment comment);

    void downvoteComment(Comment comment);

}
