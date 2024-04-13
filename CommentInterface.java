/**
 * CommentInterface.java
 *
 * This is an interface used to call methods in the Comment class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2 - Noah Withers
 * @version Fri March 29th, 2024
 */
public interface CommentInterface {

    //(Noah) Comment is a child of post, so there is only one new method that is not inherited
    public Post getParent();
}
