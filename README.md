**1. INSTRUCTIONS ON HOW TO COMPILE AND RUN OUR PROJECT**

In order to do anything with our code, you must download it all first. In the "<> Code" section of our project, please click the dropdown arrow on the green "<> Code" button. Click on the button titled "Download ZIP" to download all of our project files to place into an editor of your choice. If you are using Vocareum, you will need to enter the command "javac {java class here}" for every one of the java files in our project to compile. Also, multiple java files in our project use JUnit testing, so be sure to add the current JUnit library to the classpath if not already done so.

Server must be run first, and once the server is up and running the Client class can be run. The client class will automatically launch the GUI and enable account creation and sign in. All of our test cases may also be run to ensure functionality of each action.

There are nineteen java classes, six of which are for test cases and seven of which are interfaces that are implemented by the other java classes. Along with these, there are six .txt files and one .png file for our platform's logo. Please be sure that all of these files are included.



**2. MEMBER SUBMISSION TIMESTAMPS**

April 1st, 2024 - Phase 1 Vocareum workspace submitted - Faye Teras
April 15th, 2024 - Phase 2 Vocareum workspace submitted - Faye Teras
April 29th, 2024 - Phase 3 Vocareum workspace submitted - Sean VandenBussche
April 29th, 2024 - Phase 3 project report submitted - Sean VandenBussche
April 29th, 2024 - Phase 3 project presentation submitted - Sean VandenBussche

**3. CLASS SUMMARY**

As of Phase 2, there are seventeen java classes, five of which are for test cases and six of which are interfaces that are implemented by the other java classes. 

**PHASE 1 CLASSES**

_Comment.java_

The Comment class contains methods to initialize and use a Comment object. A Comment is a child of Post, and is for the most part the same as a post. The only difference is that a comment has an additional field to store the parent post for that comment.

The data for each comment is stored in the same manner as a post, with the comment number added on after the previous post's comment ID in the filename. So the standard filename for a comment is:
"username_parentPostNumber_commentPostNumber.txt"
and the likes and dislikes files are the same but with "_likes" and "_dislikes" added afterwards.

Comments can be nested, so a comment's file name could hypothetically be something like "username_1_5_3.txt". 


_CommentInterface.java_

This is an interface used to call methods in the Comment class. It contains a method, getParent(), that is implemented by the Comment class.


_CommentTest.java_

This class uses the JUnit library to ensure that the multiple Comment constructors and getParent() method function as intended. One constructor uses text input to create a comment, so the test file ensures that the input used in the constructor is unchanged. The other constructor reads text input from a file for the contents, so the test file ensure that the file contents used match the expected output. Lastly, Since Comment extends Post, the CommentTest class also makes sure that certain functions like like(username) and dislike(username) still result in the proper modifications for a Comment object as they do for a Post Object.


_Database.java_

This class uses a file, allUsers.txt, containing a list of all users of our social media platform and their passwords. When an account is created it can be added to the allUsers file through the method addUser, which takes a username and password as parameters to write into the allUsers text file. This class can also tell whether or not a user already exists in the allUsers file. This is done through the userExists() method, which uses the username as a parameter to return a boolean result for whether the username exists in the allUsers file or not. For password protected logins on our platforms, the method authenticateUser() is used to match the username and password parameters with a username and password in the allUsers file, returning true if there is a match and false if there is not. Lastly, this class has the writeFile() method, which takes in an ArrayList of String data and writes it to a user specified file name.


_DatabaseInterface.java_

This is an interface used to call methods in the Database class. 

There are three methods specified, including boolean addUser(String username, String password), boolean userExists(String username), and boolean authenticateUser(String username, String password).


_DatabaseTest.java_

This class uses the JUnit library to ensure that the methods specifically in Database.java function as intended. 

The first test makes sure that addUser() adds the parameters into the file, asserting that the boolean method returns true. The second test is for userExists(), adding a test user to the allUsers file using addUser() and asserting that that user does exist in the file using userExists() and asserting that a fake user does not exist using userExists(). The third test ensures proper functionality of the authenticateUser method. It does this by first adding a test user to the allUsers file using addUser(). Then it runs two assertions: the first asserting true for a test user with a matching username and password, and asserting false for a matching username and a non-matching password.


_Post.java_

This class can make a Post object. Each Post object modifies three files, a file containing the text of the post, a file containing all of the users who have liked the post, and a file containing all of the users who have disliked the post. Each post has its own filename, which will be given to it by the server, and that filename is of the format username_postNumber.txt. The username is the user who has posted it, and the post number is the number post. The text of the post is stored in username_postNumber.txt, the likes are stored in username_postNumber_likes.txt, and the dislikes are stored in username_postNumber_dislikes.txt. The post contains methods to retrieve the post, edit the post, like and dislike, hide the post, and to get who has liked and disliked the post. 


_PostInterface.java_

This is an interface used to call methods in the Post class. 

It contains 8 getters for the fields used in the Post class, including String getUsername(), int getLikesCount(), int getDislikesCount(), int[] getTime(), ArrayList<String> getLikesList(), ArrayList<String> getDislikesList(), ArrayList<String> getHidden(), and boolean isEdited(). 

There are also 7 methods to be implemented in the Post class, including boolean like(String username), boolean dislike(String username), String getText(), boolean deletePost(), boolean hide(String username), int[] getCurrentTime(), and void editPost(String newText).


_PostTest.java_

There are 8 tests in this class that use the JUnit library to assert that the Post.java class is functioning how it should. 

The first test, testCreateNewPost(), asserts that a Post Object is successfully created. The second test, testEditPost(), edits the text field of a Post Object using editPost() and asserts that the text field of the Post Object is no longer it's String value prior to editPost(). The third test, testLikes(), adds a test user to the like list of the Post Object and asserts that the user has been properly added. The fourth test, testDislikes(), adds a test user to the dislike list of the Post Object and asserts that the user has been properly added. The fifth test, testDeletePost(), starts by creating a test Post Object. Then, it runs the deletePost() command and asserts that the Post Object no longer exists. The sixth test, testConstructorWithFile(), creates a Post Object that uses a file as a parameter. This test then uses the getText() method from Post.java and asserts that it does not return null. The seventh test, testGetText(), Functions much like the previous test, except instead of asserting that the getText() method does not return null, it tests if the text contents of the Post Object match the parameter value that was used upon object creation. The eighth test, testHidePost(), starts by creating a test Post Object and using the hide() method to add the Post to the hiddenFile. Then, the test asserts that the hiddenFile contains the hidden Post Object.


_User.java_

The primary purpose of User is to keep track of friends and blocked users. To do so, each User Object has an ArrayList of friend Users and blocked Users along with a file for friend Users and blocked Users to read from and write to using readFile() and writeFile() methods. This class contains methods to create files, specifically files for blocked Users and friend Users, using the username of the current User for the beginning of each friend and blocked .txt file. Also, there are two methods to add and unadd User, appending the friend ArrayList and writing to the User's friend file. Similarly,  this class has two methods to block and unblock Users, appending the block ArrayList and writing to the User's blocked file.


_UserInterface.java_

This is our interface for our User class. 

It contains 5 getters for the User.java fields, including String getUsername(), ArrayList<String> getFriendList(), ArrayList<String> getBlockList(), File checkFriendsFile() and File checkBlockedFile(). 

There are also 5 methods to be implemented in the User class, including boolean addFriend(String username), boolean removeFriend(String username), boolean blockUser(String username), boolean unblockUser(String username), and boolean writeFile(File filename, ArrayList<String> array).


_UserTest.java_

This is our testing .java file to ensure that all of the code within User.java is functioning as we expect. There are six tests within this file, each of which will be briefly described here. 

The first test, testCreateUser(), ensures that the User constructor works properly. The test creates a User Object and asserts that the Object is not null. It then asserts that the checkFriendsFile() and checkBlockedFile() methods within User.java made files that exist using .exists(). The second test, testCreateFile(), functions just as testCreateUser(), except that it asserts that the checkFriendsFile() and checkBlockedFile() methods within User.java have created usable Files that have been assigned to the newly created User and that they are not null. 

The next four tests all use a utility method, checkFileContainsUser(), which takes in a File and a username String as parameters to sift through the specified file for the username provided. If the method returns false, the username is not in the file, but if the method returns true, the username does exist in the file.

The third test, testAddFriend(), creates a User Object and adds a friend to it through the addFriend() method. The test then asserts that the friend exists in the friend ArrayList and the friend File of the User Object, printing the friend list to the terminal if the assertions are correct. The fourth test, testDeleteFriend(), creates a User Object and adds a friend to it using the addFriend() method. The test then removes the newly added friend through the removeFriend() method and asserts that checking the friend File and the friend ArrayList returns false. The fifth test, testBlockUser(), creates a User Object and adds a blocked user to it through the blockUser() method. The test then asserts that the blocked user exists in the blocked ArrayList and the blocked File of the User Object, printing the block list to the terminal if the assertions are correct. The sixth test, testDeleteFriend(), creates a User Object and adds a blocked user to it using the blockUser() method. The test then removes the newly added blocked user through the unblockUser() method and asserts that checking the blocked File and ArrayList returns false.  

All of the tests return a success message to the terminal if the test assertions are all correct.


_ClientInterface.java_

This interface is for Client.java.

All of the parameters for every method are the same, (Scanner scan, BufferedReader reader, PrintWriter writer), so I did not write these params in every one of them to save space.

These two methods will be for User creations and sign-ins: void createUser() and void signIn();

The next six methods are for interactions with Users on our social media platform, like searching, friending, and blocking Users. These methods are: void userSearch(), void viewProfile(), boolean friendUser(), boolean unfriendUser(), boolean blockUser(), and boolean unblockUser().

There are also six methods that will be used for the User to interact with Posts and Comments. These include: boolean createPost(), boolean createComment(), boolean like(), boolean dislike(), boolean hide(), and boolean edit().

The final method is used to gather the entire social media feed for the User currently signed in. Its name is boolean getFeed().


_ServerInterface.java_

This interface is for Server.java.

All of the parameters for almost every method are the same, (Database db, BufferedReader reader, PrintWriter writer), so I did not write these params in every one of them to save space. There is one method that has different parameters, so I filled in its parentheses.

These two methods will be for User creations and sign-ins: void createUser() and void signIn();

The next six methods are for interactions with Users on our social media platform, like searching, friending, and blocking Users. These methods are: void userSearch(), boolean viewProfile(Database db, BufferedReader reader, PrintWriter writer, String username), boolean friendUser(), boolean unfriendUser(), boolean blockUser(), and boolean unblockUser().

There are also five methods that will be used for the User to interact with Posts. These include: boolean createPost(), boolean likePost(), boolean dislikePost(), boolean hidePost(), and boolean editPost(). Similarly, there are five methods that will be used for the User to interact with Comments. These include: boolean createComment(), boolean likeComment(), boolean dislikeComment(), boolean hideComment(), and boolean editComment().

The final method is used to gather the entire social media feed for the User currently signed in. Its name is boolean getFeed().


**PHASE 2 & 3 CLASSES**

_Client.java_

This is the class for the client that connects to the social media server. The client can respond to several commands from the GUI to be sent to the Server class and processed. The client can communicate requests to friend and block Users, like and dislike posts and comments, and create posts and comments. These are a few of the many options availible for the current connected user to do.

_Server.java_

This is the class for the Server that permanently runs to accept the multiple clients that will interact with the social media platform. This class uses the methods from the previous classes (Database, User, Post, Comment) to help the user interact with the platform. This server houses methods to create Users and request they sign in, along with calculating the Users, Posts, and Comments in the Database to respond to the many prompts possible from Client.

_GUI.java_

The GUI class serves as the user interface for the platform, enabling users to interact with posts and other users on the platform. It provides a home screen with a top banner featuring a search bar, a home button, and a username panel, and it contains methods for viewing and interacting with posts, such as adding comments, liking, and disliking. The class also includes methods for handling user interaction that communicates with Client, such as creating and deleting posts and comments, and managing user profiles.

_ClientTest.java_

To make sure our client-to-server interaction is working as intended, this test class will test several commands to ensure that the many possible inputs from the User can be handled by our code.

The first four tests in this document deal with User permissions and display setup. The first test, testCreateUser(), will attempt to create a test User to be test added to the allUsers.txt file. If the output matches, the test is successful. The second test, testSignIn(), will create a test user using createUser() and then attempt to signIn using the username and password of the newly created user. If the output matches, the test is successful. The third test, testUserSearch(), will create a test user using createUser() and then attempt to lookup using the username of the newly created user. If the output matches, the test is successful. The fourth test, testGetFeed(), will call the getFeed() method and assert that the method returns true, meaning it ran successfully. It then asserts that the output of the method matches the expected output, then the test is completed. 

Two tests in this class concern themselves with creation of Posts and Comments. The fifth test, testCreatePost(), will attempt to create a test post using the createPost() method, then assert the boolean result of that method. Similarly, the tenth test in the class, testCreateComment(), will attempt to create a test comment using the createComment() method, then assert the boolean result of that method. 

Four of the test cases have to deal with post and comment modifications, and all tests function in the same way. The sixth test, testLike(), will attempt to simulate liking a post or comment using the like() method, then assert the boolean result of that method. The seventh test, testDislike(), will attempt to simulate disliking a post or comment using the dislike() method, then assert the boolean result of that method. The eighth test, testHide(), will attempt to simulate hiding a post or comment using the hide() method, then assert the boolean result of that method. The ninth test, testEdit(), will attempt to simulate editing a post or comment using the dislike() method, then assert the boolean result of that method.

