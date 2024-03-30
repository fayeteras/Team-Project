public interface PostInterface {
    //(Savni) Creates a post that contains a string of content
    //public void makePost(String content);

    //(Savni) Takes a post and increments the like total of it
    public boolean like(String username);

    //(Savni) Takes a post and increments the dislike total of it
    public boolean dislike(String username);

    //(Savni) Takes a post and hides it from the user's feed
    //public void hidePost(Post post);

    //(Savni) Takes a post and deletes it
    //Note that this should only be done by the post owner
    public boolean deletePost();

    //(Sean) Stringafies the fields of the Post class
    //Formatting can be seen in userPosts.txt
    //public String toString();

    //(Faye) gets current time stamp (method written by Tyler)
    public int[] getCurrentTime();

    //(Faye) replaces old comment with new text (method written by Noah)
    public void editPost(String newText);

    //(Faye) adds or removes username from array of likes (method written by Tyler)
    public boolean likes(String username);
    
    //(Faye) adds or removes username from array of dislikes (method written by Tyler)
    public boolean dislikes(String username);

}