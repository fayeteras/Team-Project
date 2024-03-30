//(Savni)
public class PostTest {

    public static void main(String[] args) {
        // Create a new post
        Post post = new Post("user123", "This is a test post.", 1);

        // Test getting text
        System.out.println("Post text: " + post.getText());

        // Test liking the post
        System.out.println("Like status before liking: " + post.likes("user456")); // Should return false
        System.out.println("Like status after liking: " + post.like("user456")); // Should return true

        // Test disliking the post
        System.out.println("Dislike status before disliking: " + post.dislikes("user789")); // Should return false
        System.out.println("Dislike status after disliking: " + post.dislike("user789")); // Should return true

        // Test editing the post
        post.editPost("This is an edited post.");
        System.out.println("Edited post text: " + post.getText());

        // Test deleting the post
        System.out.println("Delete status: " + post.deletePost()); // Should return true
    }
}
