public interface DatabaseInterface {

        void createUser(String username, String password); //userprofile creation

        void searchUser(String username); //viewing/searching users

        boolean authenticateUser(String username, String password); // checking pw and username (equal)

        void addFriend(String username); //add

        void removeFriend(String username); //remove

        void blockUser(String username);

        void unblockUser(String username);

        void makePost(String content); //createPost

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
        //if yall implement the methods in the correspoding classes the databaseinterface will still have - 
        
        // method to save the information to all_users.txt
  //public void saveToFile(String fileName) {
        //try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
           // writer.println(username + "|" + password + "|" + String.join("|", friends) + "|" + String.join("|", blockedUsers));
       // } catch (IOException e) {
         //   e.printStackTrace();
        //}
   // } (same for user_posts.txt and user_comments.txt)

    }
