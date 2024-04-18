import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    User user = new User("testUser");

    JFrame homeScreen = new JFrame(); // Initialize the homeScreen frame object
    JPanel banner;
    JButton homeButton;
    JTextField searchField;
    JButton usernameButton;
    JScrollPane postsPanel;
    JPanel feedPanel;

    //Testing for now
    public static void main(String[] args) {
        Post testPost = new Post("John Doe", "This is a test post. It is testing post. This is the text of the test post.", "TestPost");
        Post testPost2 = new Post("Luke Doe", "This is a test. It is testing post. This is the text of the test post.", "TestPost2");
        Post[] testPosts = new Post[2];
        testPosts[0] = testPost;
        testPosts[1] = testPost2;

        testPost.like("james");
        testPost.dislike("lucas");
        testPost.like("Karina");

        SwingUtilities.invokeLater(() -> {
            GUI panel = new GUI(); // Create an instance of Panels
            JFrame frame = panel.HomeScreen(panel.AllPostsPanel(testPosts)); // Create a JFrame and get the home screen panel
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(800, 600); // Set frame size
            frame.setVisible(true); // Make the frame visible
        });
    }

    //HOME SCREEN
    public JFrame HomeScreen(JScrollPane feedPanel) {
        //Frame
        homeScreen.setLayout(new BorderLayout());
        homeScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Banner
        banner = new JPanel(new BorderLayout());
        banner.setBackground(Color.LIGHT_GRAY);
        banner.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));

        //Home Button
        homeButton = new JButton("Home");
        homeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        homeButton.setForeground(Color.BLUE);
        banner.add(homeButton, BorderLayout.WEST);

        //Search
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.LIGHT_GRAY);
        searchField = new JTextField("Search");
        searchField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        searchField.setColumns(20);
        searchPanel.add(searchField, BorderLayout.WEST);
        searchPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        banner.add(searchPanel, BorderLayout.CENTER);

        //Username Button (to view profile)
        usernameButton = new JButton(user.getUsername());
        usernameButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        usernameButton.setForeground(Color.BLUE);
        banner.add(usernameButton, BorderLayout.EAST);

        //Put it all together
        homeScreen.add(banner, BorderLayout.NORTH); // Add the banner panel to the frame
        homeScreen.add(feedPanel, BorderLayout.CENTER);
        return homeScreen;
    }


    //Individual Posts
    public JPanel UserPostPanel(Post post) {
        JPanel postPanel = new JPanel(new BorderLayout());
        postPanel.setPreferredSize(new Dimension(600, 150)); // Smaller preferred height
        postPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        //Username label
        JLabel usernameLabel = new JLabel(" " + post.getUsername());
        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        usernameLabel.setForeground(Color.BLUE);
        postPanel.add(usernameLabel, BorderLayout.NORTH);

        //Post text area
        JLabel postText = new JLabel(post.getText());
        postText.setVerticalAlignment(JLabel.TOP);
        postText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        JScrollPane postScrollPane = new JScrollPane(postText);
        postPanel.add(postScrollPane, BorderLayout.CENTER);

        //Panel for likes, dislikes, and view comments button
        JPanel bottomPanel = new JPanel(new BorderLayout());

        //Likes and dislikes Buttons
        JPanel likesDislikesPanel = new JPanel(new GridLayout());
        JLabel likesLabel = new JLabel(String.format("Likes: " + post.getLikesCount()));
        JButton likeButton = new JButton("+");
        likesLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        JLabel dislikesLabel = new JLabel(String.format("Dislikes: " + post.getDislikesCount()));
        JButton dislikeButton = new JButton("-");
        dislikesLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        likesDislikesPanel.add(likesLabel);
        likesDislikesPanel.add(likeButton);
        likesDislikesPanel.add(dislikesLabel);
        likesDislikesPanel.add(dislikeButton);
        bottomPanel.add(likesDislikesPanel, BorderLayout.WEST);

        //View comments + Edit button
        JPanel commentsAndEdit = new JPanel(new GridLayout());
        JButton viewCommentsButton = new JButton("Comments");
        commentsAndEdit.add(viewCommentsButton);
        if (post.getUsername().equals(user)) {
            JButton editButton = new JButton("Edit");
            bottomPanel.add(editButton, BorderLayout.EAST);
            commentsAndEdit.add(editButton);
        }
        bottomPanel.add(commentsAndEdit, BorderLayout.EAST);
        postPanel.add(bottomPanel, BorderLayout.SOUTH);
        postPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        return postPanel;
    }

    //All Posts Panel
    public JScrollPane AllPostsPanel(Post[] allPosts) {
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        //Get each individual post
        for (int i = 0; i < allPosts.length; i++) {
            JPanel thisPost = UserPostPanel(allPosts[i]);
            postsPanel.add(thisPost);
        }

        //Scroll Panel
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }
}
