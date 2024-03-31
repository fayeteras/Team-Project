public interface clientInterface {
    public boolean connect();
    public boolean disconnect();
    public User createUser(String username);

    //Sign in -- will authenticate through database and create a new serversocket for the user
    public boolean signIn(String username, String password);

    //Search Methods
    public boolean userSearch(String search);

    //User Methods - sends username to server
    public boolean viewProfile(String username);
    public boolean friendUser(String username);
    public boolean unfriendUser(String username);
    public boolean blockUser(String username);
    public boolean unblockUser(String username);
    
    //Feed Methods
    public void showFeed();

    //Post Methods - Post Class
    public boolean createPost(String postText);
    public boolean likePost(Post post);
    public boolean dislikePost(Post post);
    public boolean hidePost(Post post);

    //Comment Methods - Comment Class
    public boolean createComment(Post post, String commentText);
    public boolean likeComment(Comment comment);
    public boolean dislikeComment(Comment comment);
    public boolean hideComment(Comment comment);
}
