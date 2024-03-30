public class CommentTest {

    public static void main(String[] args) {
        // Create a parent post
        Post parentPost = new Post("user123", "This is a parent post.", 1);

        // Create a comment
        Comment comment = new Comment("user456", "This is a comment.", 2, parentPost);

        // Test getting text
        System.out.println("Comment text: " + comment.getText());

        // Test getting parent post
        System.out.println("Parent post: " + comment.getParent().getText());

        // Test liking the comment
        System.out.println("Like status before liking: " + comment.likes("user789")); // Should return false
        System.out.println("Like status after liking: " + comment.like("user789")); // Should return true

        // Test disliking the comment
        System.out.println("Dislike status before disliking: " + comment.dislikes("user101")); // Should return false
        System.out.println("Dislike status after disliking: " + comment.dislike("user101")); // Should return true

        // Test editing the comment
        comment.editPost("This is an edited comment.");
        System.out.println("Edited comment text: " + comment.getText());

        // Test deleting the comment
        System.out.println("Delete status: " + comment.deletePost()); // Should return true
    }
}
