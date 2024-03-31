public interface serverInterface {
        
    //Start and stop server
    //start will create a new port for each client as well as a thread
    public boolean start();
    public boolean stop();
    
    //Create new user -- Will communicate with User and database
    public User createUser(String username);

    //Sign in -- will authenticate through database and create a new serversocket for the user
    public boolean signIn(String username, String password);

    //Search Methods
    public boolean userSearch(String search);

    //User Methods - User + Database
    public boolean viewProfile(User user);
    public boolean friendUser(User user);
    public boolean unfriendUser(User user);
    public boolean blockUser(User user);
    public boolean unblockUser(User user);
    
    //Feed Methods
    public void showFeed();

    //Post Methods - Post Class
    public boolean createPost(String postText);
    public boolean likePost(Post post);
    public boolean dislikePost(Post post);
    public boolean hidePost(Post post);
    public boolean editPost(Post post, String newText);

    //Comment Methods - Comment Class
    public boolean createComment(Post post, String commentText);
    public boolean likeComment(Comment comment);
    public boolean dislikeComment(Comment comment);
    public boolean hideComment(Comment comment);
    public boolean editComment(Comment comment, String newText);

}
