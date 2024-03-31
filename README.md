1. INSTRUCTIONS ON HOW TO COMPILE AND RUN OUR PROJECT

In order to do anything with our code, you must download it all first. In the "<> Code" section of our project, please click the dropdown arrow on the green "<> Code" button. Click on the button titled "Download ZIP" to download all of our project files to place into an editor of your choice. If you are using Vocareum, you will need to enter the command "javac {java class here}" for every one of the java files in our project to compile. Also, multiple java files in our project use JUnit testing, so be sure to add the current JUnit library to the classpath if not already done so. 

At Phase 1, our project is not yet designed to run in its fullest, as client and server communication is to be set up in Phase 2, and GUI will be added in on Phase 3. As such, the current way to run our project is to run each of our four test case files and observe the inputs and outputs for future functionality that we will cover in later phases.

As of Phase 1, there are fourteen java classes, four of which are for test cases and six of which are interfaces that are implemented or will be implemented by the other java classes. 



2. MEMBER SUBMISSION TIMESTAMPS

April 1st, 2024 - Phase 1 Vocareum workspace submitted - Faye Teras



3. CLASS SUMMARY

As of Phase 1, there are fourteen java classes, four of which are for test cases and six of which are interfaces that are implemented or will be implemented by the other java classes.


Comment.java

The Comment class contains methods to initialize and use a Comment object. A Comment is a child of Post, and is for the most part the same as a post. The only difference is that a comment has an additional field to store the parent post for that comment.

The data for each comment is stored in the same manner as a post, with the comment number added on after the previous post's comment ID in the filename. So the standard filename for a comment is:
"username_parentPostNumber_commentPostNumber.txt"
and the likes and dislikes files are the same but with "_likes" and "_dislikes" added afterwards.

Comments can be nested, so a comment's file name could hypothetically be something like "username_1_5_3.txt". 


CommentInterface.java

This is an interface used to call methods in the Comment class. It contains a method, getParent(), that is implemented by the Comment class.


CommentTest.java

This class uses the JUnit library to ensure that the multiple Comment constructors and getParent() method function as intended. One constructor uses text input to create a comment, so the test file ensures that the input used in the constructor is unchanged. The other constructor reads text input from a file for the contents, so the test file ensure that the file contents used match the expected output. Lastly, Since Comment extends Post, the CommentTest class also makes sure that certain functions like like(username) and dislike(username) still result in the proper modifications for a Comment object as they do for a Post Object.


Database.java

This class uses a file, allUsers.txt, containing a list of all users of our social media platform and their passwords. When an account is created it can be added to the allUsers file through the method addUser, which takes a username and password as parameters to write into the allUsers text file. This class can also tell whether or not a user already exists in the allUsers file. This is done through the userExists() method, which uses the username as a parameter to return a boolean result for whether the username exists in the allUsers file or not. For password protected logins on our platforms, the method authenticateUser() is used to match the username and password parameters with a username and password in the allUsers file, returning true if there is a match and false if there is not. Lastly, this class has the writeFile() method, which takes in an ArrayList of String data and writes it to a user specified file name.


DatabaseInterface.java

This is an interface used to call methods in the Database class. 

There are three methods specified, including boolean addUser(String username, String password), boolean userExists(String username), and boolean authenticateUser(String username, String password).


DatabaseTest.java

This class uses the JUnit library to ensure that the methods specifically in Database.java function as intended. 

The first test makes sure that addUser() adds the parameters into the file, asserting that the boolean method returns true. The second test is for userExists(), adding a test user to the allUsers file using addUser() and asserting that that user does exist in the file using userExists() and asserting that a fake user does not exist using userExists(). The third test ensures proper functionality of the authenticateUser method. It does this by first adding a test user to the allUsers file using addUser(). Then it runs two assertions: the first asserting true for a test user with a matching username and password, and asserting false for a matching username and a non-matching password.


Post.java

This class can make a Post object. Each Post object modifies three files, a file containing the text of the post, a file containing all of the users who have liked the post, and a file containing all of the users who have disliked the post. Each post has its own filename, which will be given to it by the server, and that filename is of the format username_postNumber.txt. The username is the user who has posted it, and the post number is the number post. The text of the post is stored in username_postNumber.txt, the likes are stored in username_postNumber_likes.txt, and the dislikes are stored in username_postNumber_dislikes.txt. The post contains methods to retrieve the post, edit the post, like and dislike, hide the post, and to get who has liked and disliked the post. 


PostInterface.java

This is an interface used to call methods in the Post class. 

It contains 8 getters for the fields used in the Post class, including String getUsername(), int getLikesCount(), int getDislikesCount(), int[] getTime(), ArrayList<String> getLikesList(), ArrayList<String> getDislikesList(), ArrayList<String> getHidden(), and boolean isEdited(). 

There are also 7 methods to be implemented in the Post class, including boolean like(String username), boolean dislike(String username), String getText(), boolean deletePost(), boolean hide(String username), int[] getCurrentTime(), and void editPost(String newText).


PostTest.java

There are 8 tests in this class that use the JUnit library to assert that the Post.java class is functioning how it should. 

The first test, testCreateNewPost(), asserts that a Post Object is successfully created. The second test, testEditPost(), edits the text field of a Post Object using editPost() and asserts that the text field of the Post Object is no longer it's String value prior to editPost(). The third test, testLikes(), adds a test user to the like list of the Post Object and asserts that the user has been properly added. The fourth test, testDislikes(), adds a test user to the dislike list of the Post Object and asserts that the user has been properly added. The fifth test, testDeletePost(), starts by creating a test Post Object. Then, it runs the deletePost() command and asserts that the Post Object no longer exists. The sixth test, testConstructorWithFile(), creates a Post Object that uses a file as a parameter. This test then uses the getText() method from Post.java and asserts that it does not return null. The seventh test, testGetText(), Functions much like the previous test, except instead of asserting that the getText() method does not return null, it tests if the text contents of the Post Object match the parameter value that was used upon object creation. The eighth test, testHidePost(), starts by creating a test Post Object and using the hide() method to add the Post to the hiddenFile. Then, the test asserts that the hiddenFile contains the hidden Post Object.


User.java

The primary purpose of User is to keep track of friends and blocked users. To do so, each User Object has an ArrayList of friend Users and blocked Users along with a file for friend Users and blocked Users to read from and write to using readFile() and writeFile() methods. This class contains methods to create files, specifically files for blocked Users and friend Users, using the username of the current User for the beginning of each friend and blocked .txt file. Also, there are two methods to add and unadd User, appending the friend ArrayList and writing to the User's friend file. Similarly,  this class has two methods to block and unblock Users, appending the block ArrayList and writing to the User's blocked file.


UserInterface.java

This is our interface for our User class. 

It contains 5 getters for the User.java fields, including String getUsername(), ArrayList<String> getFriendList(), ArrayList<String> getBlockList(), File checkFriendsFile() and File checkBlockedFile(). 

There are also 5 methods to be implemented in the User class, including boolean addFriend(String username), boolean removeFriend(String username), boolean blockUser(String username), boolean unblockUser(String username), and boolean writeFile(File filename, ArrayList<String> array).


UserTest.java

This is our testing .java file to ensure that all of the code within User.java is functioning as we expect. There are six tests within this file, each of which will be briefly described here. 

The first test, testCreateUser(), ensures that the User constructor works properly. The test creates a User Object and asserts that the Object is not null. It then asserts that the checkFriendsFile() and checkBlockedFile() methods within User.java made files that exist using .exists(). The second test, testCreateFile(), functions just as testCreateUser(), except that it asserts that the checkFriendsFile() and checkBlockedFile() methods within User.java have created usable Files that have been assigned to the newly created User and that they are not null. 

The next four tests all use a utility method, checkFileContainsUser(), which takes in a File and a username String as parameters to sift through the specified file for the username provided. If the method returns false, the username is not in the file, but if the method returns true, the username does exist in the file.

The third test, testAddFriend(), creates a User Object and adds a friend to it through the addFriend() method. The test then asserts that the friend exists in the friend ArrayList and the friend File of the User Object, printing the friend list to the terminal if the assertions are correct. The fourth test, testDeleteFriend(), creates a User Object and adds a friend to it using the addFriend() method. The test then removes the newly added friend through the removeFriend() method and asserts that checking the friend File and the friend ArrayList returns false. The fifth test, testBlockUser(), creates a User Object and adds a blocked user to it through the blockUser() method. The test then asserts that the blocked user exists in the blocked ArrayList and the blocked File of the User Object, printing the block list to the terminal if the assertions are correct. The sixth test, testDeleteFriend(), creates a User Object and adds a blocked user to it using the blockUser() method. The test then removes the newly added blocked user through the unblockUser() method and asserts that checking the blocked File and ArrayList returns false.  

All of the tests return a success message to the terminal if the test assertions are all correct.


clientInterface.java

This interface is for Client.java, which will not be created until Phase 2. 

These four methods will be for User connections and sign-ins: boolean connect(), boolean disconnect(), User createUser(String username), and boolean signIn(String username, String password). 

These next methods will be for the scrolling aspect of our social media platform. This will be all of the things a User can do to interact with other Users, like friending and blocking User Profiles, liking and disliking their posts, and even the User creating their own posts and comments.  There are quite a lot, but we want to make sure we cover everything just in case. Our current names for these methods are:  boolean userSearch(String search), boolean viewProfile(String username), boolean friendUser(String username), boolean unfriendUser(String username), boolean blockUser(String username), boolean unblockUser(String username), void showFeed(), boolean createPost(String postText), boolean likePost(Post post), boolean dislikePost(Post post), boolean createComment(Post post, String commentText), boolean likeComment(Comment comment), and boolean dislikeComment(Comment comment).


serverInterface.java

This interface is for Server.java, which will not be created until Phase 2. Since all of the data will be processed in the server side, we have many methods planned for the creation of this class in Phase 2, as it does not hurt to be over-prepared. 

The first two methods specified in this interface are boolean start() and boolean stop(), which are for starting and stopping the server. 

The next methods involved all of the input processing for the User's interactions with the server, including, but not limited to, their requests for posting and commenting, liking and disliking posts and comments, viewing, adding, and blocking Users, and more. Our names for these methods are: User createUser(String username), boolean signIn(String username, String password), boolean userSearch(String search), boolean viewProfile(String username), boolean friendUser(String username), boolean unfriendUser(String username), boolean blockUser(String username), boolean unblockUser(String username), void showFeed(), boolean createPost(String postText), boolean likePost(Post post), boolean dislikePost(Post post), boolean createComment(Post post, String commentText), boolean likeComment(Comment comment), and boolean dislikeComment(Comment comment).

