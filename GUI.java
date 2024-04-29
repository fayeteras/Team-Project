import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
/**
 * GUI.java
 *
 * GUI that client will use once the user has successfully signed in or up
 * to our social media platform. It functions through many buttons and text fields
 * that allow the user to interact with other posts and people on the platform.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Fri April 26th, 2024
 */

public class GUI extends JPanel implements GUIInterface  {
    User user;
    Client client;

    Post post;

    JFrame homeScreen = new JFrame(); // Initialize homeScreen frame object
    JPanel banner;
    JPanel bottomBanner;
    JButton homeButton;
    JTextField searchField;
    JButton searchButton;
    JButton usernameButton;
    JScrollPane postsPanel;
    JScrollPane panel;

    // Constructor for GUI
    public GUI(String username, Client client) {
        this.user = new User(username);
        this.client = client;
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
        bottomBanner.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
        try {
            BufferedImage image = ImageIO.read(new File("logo.png"));
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel label = new JLabel(imageIcon);
            bottomBanner.add(label);
        } catch (IOException e) {
        }

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
        // Initialize and configure searchButton
        searchButton = new JButton("Search");
        searchButton.addActionListener(searchListener);
        searchButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        searchButton.setForeground(Color.BLUE);
        searchPanel.add(searchButton, BorderLayout.CENTER);
        banner.add(searchPanel, BorderLayout.CENTER);

        // Initialize and configure username button
        usernameButton = new JButton(user.getUsername());
        usernameButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        usernameButton.setForeground(Color.BLUE);
        banner.add(usernameButton, BorderLayout.EAST);

        // Add banner to the top of the homeScreen frame
        homeScreen.add(banner, BorderLayout.NORTH);
        homeScreen.add(bottomBanner, BorderLayout.SOUTH);
    }

    // Individual Post Panel method
    public synchronized void viewPosts(User currentUser, Post post) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("userPosts.txt"))) {
            String line;
            JPanel postsPanel = new JPanel();
            postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
            while ((line = fileReader.readLine()) != null) {
                String[] postParts = line.split("\\|");
                if (postParts.length == 3) { // Check if the post has the correct number of parts
                    // Create a panel to hold the post content
                    JPanel postEntry = new JPanel(new BorderLayout());
                    postEntry.setPreferredSize(new Dimension(600, 70));

                    // Create a JLabel to display the post content
                    JLabel postLabel = new JLabel(postParts[0] + ": " + postParts[2]);
                    postEntry.add(postLabel, BorderLayout.CENTER);

                    // Create a panel to hold the like and dislike buttons
                    JPanel likeDislikePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                    // Create like button for the post
                    JButton likeButton = new JButton("Like");
                    likeButton.addActionListener(e -> {
                        // Handle like action
                        recordLikeDislikePost(postParts[2], "like");
                    });
                    likeDislikePanel.add(likeButton);

                    // Create dislike button for the post
                    JButton dislikeButton = new JButton("Dislike");
                    dislikeButton.addActionListener(e -> {
                        // Handle dislike action
                        recordLikeDislikePost(postParts[2], "dislike");
                    });
                    likeDislikePanel.add(dislikeButton);

                    Post thisPost = new Post(user.getUsername(), Integer.parseInt(postParts[1]));

                    JButton commentButton = new JButton("View Comments");
                    commentButton.addActionListener(e -> {
                        // View comments for the current post
                        try (BufferedReader commentReader = new BufferedReader(new FileReader("userComments.txt"))) {
                            String commentLine;
                            JPanel commentsPanel = new JPanel();
                            commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
                            while ((commentLine = commentReader.readLine()) != null) {
                                String[] commentParts = commentLine.split("\\|");
                                if (commentParts.length == 3 && commentParts[1].equals(String.valueOf(thisPost.getPostID()))) {
                                    // Create a panel to hold the comment content
                                    JPanel commentEntry = new JPanel(new BorderLayout());
                                    commentEntry.setPreferredSize(new Dimension(600, 70));

                                    // Create a JLabel to display the comment content
                                    JLabel commentLabel = new JLabel(commentParts[0] + ": " + commentParts[2]);
                                    commentEntry.add(commentLabel, BorderLayout.CENTER);

                                    // Create a panel to hold like and dislike buttons
                                    JPanel commentLikeDislikePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                                    // Create like button for the comment
                                    JButton commentLikeButton = new JButton("Like");
                                    commentLikeButton.addActionListener(ev -> {
                                        // Handle like action for comment
                                        recordLikeDislike(commentParts[2], "like");
                                    });
                                    commentLikeDislikePanel.add(commentLikeButton);

                                    // Create dislike button for the comment
                                    JButton commentDislikeButton = new JButton("Dislike");
                                    commentDislikeButton.addActionListener(ev -> {
                                        // Handle dislike action for comment
                                        recordLikeDislike(commentParts[2], "dislike");
                                    });
                                    commentLikeDislikePanel.add(commentDislikeButton);

                                    // Check if the current user is the owner of the post or the comment author
                                    if (commentParts[0].equals(user.getUsername()) || postParts [0].equals(user.getUsername())) {
                                        // Create delete button for the comment
                                        JButton deleteButton = new JButton("Delete");
                                        deleteButton.addActionListener(ev -> {
                                            // Handle delete action for comment
                                            if (deleteComment(commentParts[2], commentParts[0])) {
                                                // Remove the comment entry from the panel if deletion is successful
                                                commentsPanel.remove(commentEntry);
                                                commentsPanel.revalidate();
                                                commentsPanel.repaint();
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Failed to delete comment.", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        });
                                        commentLikeDislikePanel.add(deleteButton);
                                    }

                                    commentEntry.add(commentLikeDislikePanel, BorderLayout.SOUTH);

                                    // Add the comment entry panel to the comments panel
                                    commentsPanel.add(commentEntry);
                                }
                            }
                            // Display comments panel in a scrollable dialog
                            JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
                            JOptionPane.showMessageDialog(null, commentsScrollPane, "Comments", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    likeDislikePanel.add(commentButton);

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
                            boolean commentAdded = createComment(commentField.getText(), currentUser.getUsername(), thisPost);
                            if (commentAdded) {
                                JOptionPane.showMessageDialog(null, "Comment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to add comment.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
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
                    likeDislikePanel.add(addCommentButton);

                    postEntry.add(likeDislikePanel, BorderLayout.SOUTH);
                    // Add the post entry panel to the posts panel
                    postsPanel.add(postEntry);
                }
            }
            // Display posts panel in a scrollable dialog
            JScrollPane postsScrollPane = new JScrollPane(postsPanel);
            JOptionPane.showMessageDialog(null, postsScrollPane, "Posts", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public synchronized void recordLikeDislike(String commentText, String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("likedislikeComments.txt", true))) {
            writer.write(commentText + "," + action + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public synchronized boolean deleteComment(String commentText, String currentUser) {
        try {
            File inputFile = new File("userComments.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Split the line to get the username, postID, and comment text
                String[] parts = currentLine.split("\\|");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String postID = parts[1].trim();
                    String text = parts[2].trim();
                    // Check if the current line contains the specified comment text
                    if (text.equals(commentText)) {
                        continue;
                    }
                }
                // Write the current line to the temp file
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            // Close readers and writers
            writer.close();
            reader.close();


            // Delete the original file and rename the temp file to the original file name
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
                return true; // Successfully deleted the comment
            } else {
                return false; // Failed to delete the comment
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public synchronized boolean createComment(String commentText, String username, Post post) {
        File commentsFile = new File("userComments.txt");

        // Write the comment details to the comments file
        try (FileWriter fileWriter = new FileWriter(commentsFile, true)) {
            fileWriter.write(username + "|" + post.getPostID() + "|" + commentText + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

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
        usernameLabel.setForeground(Color.RED);
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
        //commentButton.addActionListener(e -> viewComments(post, user));
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

                boolean commentAdded = createComment(commentField.getText(), user.getUsername(), post);
                if (commentAdded) {
                    JOptionPane.showMessageDialog(null, "Comment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add comment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
        if (post.getUsername().equals(user.getUsername())) {
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
    public JScrollPane AllPostsPanel(ArrayList<String[]> allPosts, Post post) {
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        // Get each individual post and add it to the posts panel
        for (int i = 0; i < allPosts.size(); i++) {
            String[] currentPost = allPosts.get(i);
            JPanel thisPost = UserPostPanel(new Post(currentPost[0], currentPost[1]));
            postsPanel.add(thisPost);
        }

        // Create a scroll pane with the posts panel
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addPostButton = new JButton("Create Post");
        addPostButton.addActionListener(e -> {
            // Create a dialog to add a comment
            JDialog addPostDialog = new JDialog(homeScreen, "Add Post", true);
            addPostDialog.setLayout(new BorderLayout());

            // Text field to enter comment
            JTextField PostField = new JTextField(20);
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(submitEv -> {
                // Get the text from the comment field and add it to the post
                String postText = PostField.getText();
                boolean commentAdded = createPost(PostField.getText(), user.getUsername());
                if (commentAdded) {
                    JOptionPane.showMessageDialog(null, "Post added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add Post.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                addPostDialog.dispose();
            });

            // Add components to the dialog
            JPanel addPostPanel = new JPanel();
            addPostPanel.add(new JLabel("Enter your post: "));
            addPostPanel.add(PostField);
            addPostPanel.add(submitButton);
            addPostDialog.add(addPostPanel, BorderLayout.CENTER);

            // Set dialog properties
            addPostDialog.setSize(300, 150);
            addPostDialog.setLocationRelativeTo(null);
            addPostDialog.setVisible(true);
        });
        postsPanel.add(addPostButton);

        JButton viewPostsButton = new JButton("View Posts");
        viewPostsButton.addActionListener(e -> {

            viewPosts(user, post);
        });
        postsPanel.add(viewPostsButton);

        return scrollPane;
    }


    public JFrame viewProfilePanel(User viewUser) { //(Tyler) The user we are viewing (not finished/tested)
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
        JScrollPane friendScrollPane = new JScrollPane(friendsList);
        profilePanel.add(friendScrollPane, BorderLayout.CENTER);

        // Bottom panel for block and unblock
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Likes and dislikes buttons panel
        JPanel blockPanel = new JPanel(new GridLayout(1, 1));
        String text;
        if (user.isBlocked(viewUser.getUsername())) {
            JButton blockButton = new JButton("Unblock");
            blockPanel.add(blockButton);
            blockButton.addActionListener(block -> {
                user.unblockUser(viewUser.getUsername());
                blockButton.setVisible(false);
                JOptionPane.showMessageDialog(null, "User unblocked successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            });
        } else {
            JButton blockButton = new JButton("Block");
            blockPanel.add(blockButton);
            blockButton.addActionListener(block -> {
                user.blockUser(viewUser.getUsername());
                blockButton.setVisible(false);
                JOptionPane.showMessageDialog(null, "User blocked successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            });
        }

        bottomPanel.add(blockPanel, BorderLayout.WEST);

        // View friends and add friend buttons
        JPanel friendsPanel = new JPanel(new GridLayout(1, 1));
        // JButton viewFriendsButton = new JButton("Friends");
        // friendsPanel.add(viewFriendsButton);

        if (user.isFriend(viewUser.getUsername())) {
            JButton addFriendButton = new JButton("Remove Friend");
            friendsPanel.add(addFriendButton);
            addFriendButton.addActionListener(friend -> {
                user.removeFriend(viewUser.getUsername());
                addFriendButton.setVisible(false);
                JOptionPane.showMessageDialog(null, "Friend removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            });
        } else {
            JButton addFriendButton = new JButton("Add Friend");
            friendsPanel.add(addFriendButton);
            addFriendButton.addActionListener(friend -> {
                user.addFriend(viewUser.getUsername());
                addFriendButton.setVisible(false);
                JOptionPane.showMessageDialog(null, "Friend added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            });
        }


        bottomPanel.add(friendsPanel, BorderLayout.EAST);
        profilePanel.add(bottomPanel, BorderLayout.SOUTH);

        profilePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(profilePanel);
        try (BufferedReader fileReader = new BufferedReader(new FileReader("userPosts.txt"))) {
            String line;
            JPanel postsPanel = new JPanel();
            postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
            //Add header for all the posts
            JPanel postHeaderPanel = new JPanel(new BorderLayout());
            postHeaderPanel.setPreferredSize(new Dimension(600, 70));

            // Create a JLabel to display the post content
            JLabel postHeaderText = new JLabel("These are the user's posts:");
            postHeaderPanel.add(postHeaderText, BorderLayout.CENTER);
            // Add the post entry panel to the posts panel
            postsPanel.add(postHeaderPanel);
            int postCount = 0;
            while ((line = fileReader.readLine()) != null) {
                String[] postParts = line.split("\\|");
                if (postParts.length == 3 && postParts[0].equals(viewUser.getUsername())) { // Check if the post has the correct number of parts
                    // Create a panel to hold the post content
                    JPanel postEntry = new JPanel(new BorderLayout());
                    postEntry.setPreferredSize(new Dimension(600, 70));
                    postCount++;
                    // Create a JLabel to display the post content
                    JLabel postLabel = new JLabel("Post " + postCount + ": " + postParts[2]);
                    postEntry.add(postLabel, BorderLayout.CENTER);
                    // Add the post entry panel to the posts panel
                    postsPanel.add(postEntry);
                }
            }
            // Display posts panel in a scrollable dialog
            JScrollPane postsScrollPane = new JScrollPane(postsPanel);
            mainPanel.add(scrollPane);
            mainPanel.add(postsScrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JFrame viewFrame = new JFrame();
        viewFrame.add(mainPanel, BorderLayout.CENTER);
        viewFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return viewFrame;
    }

    //(Sean) userSearch GUI implementation
    ActionListener searchListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchButton) {
                search();
                searchField.setText("Search");
            }
        }
    };

    public void search() {
        String tempString = searchField.getText();
        Database db = new Database();
        if (db.userExists(tempString)) {
            User searchedUser = new User(tempString);
            //Waiting on viewProfile to be created.

            //homeScreen.setVisible(false);
            viewProfilePanel(searchedUser).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "This username is not in our records!", "HELLo",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public synchronized void recordLikeDislikePost(String postText, String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("likedislikePosts.txt", true))) {
            writer.write(postText + "," + action + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public synchronized boolean createPost(String postText, String username) {
        try {
            File postIDFile = new File("lastPostID.txt");
            int postID;

            // Check if the file exists
            if (postIDFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(postIDFile));
                postID = Integer.parseInt(reader.readLine()); // Read the last used post ID
                reader.close();
            } else {
                postID = 0; // Set initial post ID if the file doesn't exist
            }

            // Increment post ID for each new post
            postID++;

            // Write the updated post ID back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(postIDFile));
            writer.write(String.valueOf(postID));
            writer.flush();
            writer.close();

            // Write the post details to the posts file using the incremented post ID
            try (FileWriter fileWriter = new FileWriter("userPosts.txt", true)) {
                fileWriter.write(username + "|" + postID + "|" + postText + "\n");
                return true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
