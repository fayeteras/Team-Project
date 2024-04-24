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
        JButton viewCommentsButton = new JButton("Comments");
        commentsAndEdit.add(viewCommentsButton);
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

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // Get the comment text
                String commentText = commentTextArea.getText();
                // Add the comment to the post
                post.addComment(commentText);
                // Close the dialog
                commentDialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // Close the dialog
                commentDialog.dispose();
            }
        });
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        commentDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog size and visibility
        commentDialog.setSize(400, 200);
        commentDialog.setLocationRelativeTo(homeScreen);
        commentDialog.setVisible(true);
    }

    // Method to display comments in a separate dialog
    public void displayComments(Post post) {
        // Create a new dialog to display comments
        JDialog commentsDialog = new JDialog(homeScreen, "Comments for " + post, true);
        commentsDialog.setLayout(new BorderLayout());

        // Create a text area to display comments
        JTextArea commentsTextArea = new JTextArea();
        commentsTextArea.setEditable(false); // Make it non-editable
        JScrollPane commentsScrollPane = new JScrollPane(commentsTextArea);
        commentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        commentsDialog.add(commentsScrollPane, BorderLayout.CENTER);

        // Add each comment to the text area
        for (String comment : post.getComments()) {
            commentsTextArea.append(comment + "\n"); // Add each comment with a newline
        }

        // Set dialog size and visibility
        commentsDialog.setSize(400, 300); // Adjust size as needed
        commentsDialog.setLocationRelativeTo(homeScreen);
        commentsDialog.setVisible(true);
    }
}
