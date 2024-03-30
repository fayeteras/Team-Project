To compile and run the project, download the code with the green button, and 

CLASS SUMMARY

Comment.java

The Comment class contains methods to initialize and use a Comment object. A Comment is a child of Post, and is for the most part the same as a post. The only difference is that a comment has an additional field to store the parent post for that comment.

The data for each comment is stored in the same manner as a post, with the comment number added on after the previous post's comment ID in the filename. So the standard filename for a comment is:
username_parentPostNumber_commentPostNumber.txt
and the likes and dislikes files are the same but with _likes and _dislikes added afterwards.

Comments can be nested, so a comment's file name could hypothetically be something like username_1_5_3.txt. 

CommentInterface.java

This is an interface used to call methods in the Comment class.

Database.java

This class uses a file containing a list of all users and their passwords. When an account is created, its username and password are added to the file it refers to. This can be used to tell whether or not a user exists, and to authenticate a user when they sign in by telling if the password matches the username. 

DatabaseInterface.java

This is an interface used to call methods in the Database class.

Post.java

This class can make a Post object. Each Post object modifies three files, a file containing the text of the post, a file containing all of the users who have liked the post, and a file containing all of the users who have disliked the post. Each post has its own filename, which will be given to it by the server, and that filename is of the format username_postNumber.txt. The username is the user who has posted it, and the post number is the number post. The text of the post is stored in username_postNumber.txt, the likes are stored in username_postNumber_likes.txt, and the dislikes are stored in username_postNumber_dislikes.txt. The post contains methods to retrieve the post, edit the post, like and dislike, hide the post, and to get who has liked and disliked the post. 

PostInterface.java

This is an interface used to call methods in the Post class.

User.java

The primary purpose of User is to keep track of friends and blocked users. It 




