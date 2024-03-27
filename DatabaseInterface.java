import java.io.IOException;

//not sure about this but this is one way in which it can be implemented
public interface DatabaseInterface {
    void saveUserInfo(String userInfo) throws IOException; //all_users.txt

    void savePostInfo(String postInfo) throws IOException; //userPosts.txt

    void saveCommentInfo(String commentInfo) throws IOException; //userComments.txt
}
