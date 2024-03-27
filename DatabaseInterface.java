public interface DatabaseInterface {

        void createUser(String username, String password);

        void searchUser(String username);

        boolean authenticateUser(String username, String password);

        void addFriend(String username);

        void removeFriend(String username);

        void blockUser(String username);

        void unblockUser(String username);

        void makePost(String content);

        Post[] getFeed(); // displaying all friends feed

        void upvotePost(Post post); 

        void addComment(Post post, Comment comment); // Add comment to a post

        void deleteComment(Post post, Comment comment); // Delete comment from a post

        void downvotePost(Post post);

        void upvoteComment(Comment comment);

        void downvoteComment(Comment comment);

        void hidePost(Post post);

        void deletePost(Post post);
  
//some methods might require or work better with boolean

    }