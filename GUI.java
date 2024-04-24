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

        // View comments, add comment, and edit buttons
        JPanel commentsAddEdit = new JPanel(new GridLayout(1, 3));
        JButton commentButton = new JButton("View Comments");
        commentButton.addActionListener(e -> {
            // Create a dialog to display comments
            JDialog commentsDialog = new JDialog(homeScreen, "Comments for Post", true);
            commentsDialog.setLayout(new BorderLayout());

            // Create a panel to hold comments
            JPanel commentsPanel = new JPanel();
            commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));

            // Add each comment to the panel
            for (String comment : post.getComments()) {
                JPanel commentEntry = new JPanel(new BorderLayout());
                JLabel commentLabel = new JLabel(comment);
                commentEntry.add(commentLabel, BorderLayout.CENTER);

                // Create delete button for the comment (visible only to the user who wrote it or the post owner)
                if (comment.startsWith(user.getUsername()) || post.getUsername().equals(user.getUsername())) {
                    JButton deleteButton = new JButton("Delete");
                    deleteButton.addActionListener(deleteEv -> {
                        // Perform delete operation here
                        post.getComments().remove(comment); // Assuming post.getComments() returns a modifiable list
                        commentsPanel.remove(commentEntry);
                        commentsDialog.pack();
                    });
                    commentEntry.add(deleteButton, BorderLayout.EAST);
                }

                commentsPanel.add(commentEntry);
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
        commentsAddEdit.add(commentButton);

        // Add comment button
        JButton addCommentButton = new JButton("Add Comment");
        addCommentButton.addActionListener(e -> {
            // Create a dialog to add a comment
            JDialog addCommentDialog = new JDialog(homeScreen, "Add Comment", true);
            addCommentDialog.setLayout(new BorderLayout());

            // Text field to enter comment
            JTextField commentField = new JTextField(20);
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(submitEv -> {
                // Get the text from the comment field and add it to the post
                String commentText = commentField.getText();
                post.addComment(user.getUsername() + ": " + commentText); // Assuming addComment method appends username to comment
                addCommentDialog.dispose();
            });

            // Add components to the dialog
            JPanel addCommentPanel = new JPanel();
            addCommentPanel.add(new JLabel("Enter your comment: "));
            addCommentPanel.add(commentField);
            addCommentPanel.add(submitButton);
            addCommentDialog.add(addCommentPanel, BorderLayout.CENTER);

            // Set dialog properties
            addCommentDialog.setSize(300, 150);
            addCommentDialog.setLocationRelativeTo(null);
            addCommentDialog.setVisible(true);
        });
        commentsAddEdit.add(addCommentButton);

        // Edit button (visible only to the owner of the post)
        if (post.getUsername().equals(user)) {
            JButton editButton = new JButton("Edit");
            commentsAddEdit.add(editButton);
        }
        bottomPanel.add(commentsAddEdit, BorderLayout.EAST);
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

    public JPanel viewProfilePanel(User viewUser) { //The user we are viewing (not finished/tested)
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setPreferredSize(new Dimension(600, 150));
        profilePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Username label
        JLabel usernameLabel = new JLabel(" " + viewUser.getUsername());
        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        usernameLabel.setForeground(Color.BLUE);
        profilePanel.add(usernameLabel, BorderLayout.NORTH);

        // Friends list
        JLabel friendsList = new JLabel(viewUser.getFriendList().toString());
        friendsList.setVerticalAlignment(JLabel.TOP);
        friendsList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        JScrollPane postScrollPane = new JScrollPane(friendsList);
        profilePanel.add(postScrollPane, BorderLayout.CENTER);

        // Bottom panel for block and unblock
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Likes and dislikes buttons panel
        JPanel blockPanel = new JPanel(new GridLayout(1, 1));
        String text;
        if (user.isBlocked(viewUser.getUsername())) {
            text = "Unblock";
        } else {
            text = "Block";
        }
        JButton blockButton = new JButton(text);
        
        blockPanel.add(blockButton);
        
        bottomPanel.add(blockPanel, BorderLayout.WEST);

        // View friends and add friend buttons 
        JPanel friendsPanel = new JPanel(new GridLayout(1, 2));
        JButton viewFriendsButton = new JButton("Friends");
        friendsPanel.add(viewFriendsButton);

        if (user.isFriend(viewUser.getUsername())) { //(TODO)
            text = "Remove Friend";
        } else {
            text = "Add Friend";
        }

        JButton addFriendButton = new JButton(text);
            friendsPanel.add(addFriendButton);
        bottomPanel.add(friendsPanel, BorderLayout.EAST);
        profilePanel.add(bottomPanel, BorderLayout.SOUTH);

        // Set maximum size of the post panel
        profilePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        return profilePanel;
    }
}
