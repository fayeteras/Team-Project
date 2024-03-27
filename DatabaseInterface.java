import java.io.IOException;

public interface DatabaseInterface {
    void saveUserInfo(String userInfo) throws IOException;

    void savePostInfo(String postInfo) throws IOException;

    void saveCommentInfo(String commentInfo) throws IOException;
}
