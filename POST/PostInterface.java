public interface PostInterface {
    //(Savni) Creates a post that contains a string of content
    public void makePost(String content);

    //(Savni) Takes a post and increments the like total of it
    public void likePost(Post post);

    //(Savni) Takes a post and increments the dislike total of it
    public void downvotePost(Post post);

    //(Savni) Takes a post and hides it from the user's feed
    public void hidePost(Post post);

    //(Savni) Takes a post and deletes it
    //Note that this should only be done by the post owner
    public void deletePost(Post post);

    //(Sean) Stringafies the fields of the Post class
    //Formatting can be seen in userPosts.txt
    public String toString();
    
}
