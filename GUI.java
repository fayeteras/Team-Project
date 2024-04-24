import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JPanel {
    User user = new User("testUser");

    JFrame homeScreen = new JFrame(); // Initialize homeScreen frame object
    JPanel banner;
    JPanel bottomBanner;
    JButton homeButton;
    JTextField searchField;
    JButton usernameButton;
    JScrollPane postsPanel;
    JScrollPane panel;



    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create test posts and add them to an array
            Post testPost = new Post("John Doe", "This is a test post. It is testing post. This is the text of the test post.", "TestPost");
            Post testPost2 = new Post("Luke Doe", "This is a test. It is testing post. This is the text of the test post.", "TestPost2");
            Post[] testPosts = new Post[] { testPost, testPost2 };

            // Perform actions on the test posts
            testPost.like("james");
            testPost.dislike("lucas");
            testPost.like("Karina");

            // Create an instance of the GUI class
            GUI gui = new GUI();

            // Create a JScrollPane with all test posts using the GUI instance
            JScrollPane postsPanel = gui.AllPostsPanel(testPosts);

            // Assign the postsPanel to the panel property of the GUI instance
            gui.panel = postsPanel;

            // Add the panel to the homeScreen frame
            gui.homeScreen.add(gui.panel, BorderLayout.CENTER);

            // Set the default close operation
            gui.homeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Make the homeScreen frame visible
            gui.homeScreen.setVisible(true);
        });
    }

    // Constructor for GUI
    public GUI() {
        // Initialize the homeScreen frame object
        homeScreen = new JFrame("Home Screen");

        // Set layout of homeScreen to BorderLayout
        homeScreen.setLayout(new BorderLayout());

        // Set extended state to maximize the homeScreen frame
        homeScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Initialize banner panel
        banner = new JPanel(new BorderLayout());
        banner.setBackground(Color.LIGHT_GRAY);
        banner.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));

        // Initialize bottom panel
        bottomBanner = new JPanel(new BorderLayout());
        bottomBanner.setBackground(Color.LIGHT_GRAY);
        bottomBanner.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));

        // Initialize and configure homeButton
        homeButton = new JButton("Home");
        homeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        homeButton.setForeground(Color.BLUE);
        banner.add(homeButton, BorderLayout.WEST);

        // Initialize and configure search panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.LIGHT_GRAY);
        searchField = new JTextField("Search");
        searchField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        searchField.setColumns(20);
        searchPanel.add(searchField, BorderLayout.WEST);
        searchPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        banner.add(searchPanel, BorderLayout.CENTER);

        // Initialize and configure username button
        usernameButton = new JButton(user.getUsername());
        usernameButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        usernameButton.setForeground(Color.BLUE);
        banner.add(usernameButton, BorderLayout.EAST);

        // Add banner to the top of the homeScreen frame
        homeScreen.add(banner, BorderLayout.NORTH);
    }

    // Individual Post Panel method
    public JPanel UserPostPanel(Post post) {
        JPanel postPanel = new JPanel(new BorderLayout());
        postPanel.setPreferredSize(new Dimension(600, 150));
        postPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Username label
        JLabel usernameLabel = new JLabel(" " + post.getUsername());
        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        usernameLabel.setForeground(Color.BLUE);
        postPanel.add(usernameLabel, BorderLayout.NORTH);

        // Post text area
        JLabel postText = new JLabel(post.getText());
        postText.setVerticalAlignment(JLabel.TOP);
        postText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        JScrollPane postScrollPane = new JScrollPane(postText);
        postPanel.add(postScrollPane, BorderLayout.CENTER);

        // Bottom panel for likes, dislikes, and view comments button
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Likes and dislikes buttons panel
        JPanel likesDislikesPanel = new JPanel(new GridLayout(1, 4));
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

        // View comments and edit buttons
        JPanel commentsAndEdit = new JPanel(new GridLayout(1, 2));
        JButton commentButton = new JButton("Comments");
        commentButton.addActionListener(e -> {
            // Create a pop-up with options to add comment or view comments
            JFrame popup = new JFrame("Comment Options");
            JPanel popupPanel = new JPanel(new GridLayout(0, 1));
            JLabel username = new JLabel("Username: " + user.getUsername());
            popupPanel.add(username);
            JButton addCommentButton = new JButton("Add Comment");
            JButton viewCommentsButton = new JButton("View Comments");

            addCommentButton.addActionListener(ev -> {
                // Create a text field for entering the comment
                JTextField commentField = new JTextField(20);
                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(submitEvent -> {
                    // Get the text from the comment field and process it (e.g., add it to the post)
                    String commentText = commentField.getText();
                    // Here you can add the comment to the post or perform any other action
                    System.out.println("Comment submitted by " + user.getUsername() + ": " + commentText);
                    // Close the popup after submitting the comment
                    popup.dispose();
                });

                // Add comment field and submit button to the popup panel
                popupPanel.removeAll();
                popupPanel.add(usernameLabel);
                popupPanel.add(commentField);
                popupPanel.add(submitButton);
                popup.pack();
            });

            viewCommentsButton.addActionListener(ev -> {
                // Create a dialog to display comments
                JDialog commentsDialog = new JDialog(homeScreen, "Comments for Post", true);
                commentsDialog.setLayout(new BorderLayout());

                // Create a panel to hold comments
                JPanel commentsPanel = new JPanel();
                commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));

                // Add each comment to the panel
                for (String comment : post.getComments()) {
                    JLabel commentLabel = new JLabel(comment);
                    commentsPanel.add(commentLabel);
                }

                // Create a scroll pane for comments
                JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);

                // Add the scroll pane to the dialog
                commentsDialog.add(commentsScrollPane, BorderLayout.CENTER);

                // Set dialog properties
                commentsDialog.setSize(400, 300);
                commentsDialog.setLocationRelativeTo(null);
                commentsDialog.setVisible(true);
            });

            popupPanel.add(addCommentButton);
            popupPanel.add(viewCommentsButton);
            popup.add(popupPanel);
            popup.pack();
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
        });
        commentsAndEdit.add(commentButton);
        if (post.getUsername().equals(user)) {
            JButton editButton = new JButton("Edit");
            commentsAndEdit.add(editButton);
        }
        bottomPanel.add(commentsAndEdit, BorderLayout.EAST);
        postPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Set maximum size of the post panel
        postPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        return postPanel;
    }



    // All Posts Panel method
    public JScrollPane AllPostsPanel(Post[] allPosts) {
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        // Get each individual post and add it to the posts panel
        for (int i = 0; i < allPosts.length; i++) {
            JPanel thisPost = UserPostPanel(allPosts[i]);
            postsPanel.add(thisPost);
        }

        // Create a scroll pane with the posts panel
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

}
