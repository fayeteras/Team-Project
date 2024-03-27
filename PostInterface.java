public interface PostInterface {
    void makePost(String content); //createPost

    void upvotePost(Post post);

    void downvotePost(Post post);
    void hidePost(Post post);

    void deletePost(Post post);


}
