import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Post.java
 *
 * Post methods used to create, read, add and remove likes and dislikes, and edit post objects
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Mon April 1st, 2024
 */

public class Post implements PostInterface {
    private String username;
    private String text;
    private int likesCount;
    private int dislikesCount;
    private int[] time;
    private ArrayList<String> likesList;
    private ArrayList<String> dislikesList;
    private ArrayList<String> hidden;
    private boolean edited;
    private int postNumber;
    private static int totalPosts;
    private File textFile;
    private File likesFile;
    private File dislikesFile;
    private File hiddenFile;
    private File editedFile;


    public Post() { //(Noah) this is kind of weird but this one just sets the totalPosts.
        try {
        File f = new File("postCount.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        totalPosts = Integer.parseInt(bfr.readLine());
        bfr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Post(String username, String text, String fileName) {
        this.username = username;
        this.text = text;
        likesCount = 0;
        dislikesCount = 0;
        time = getCurrentTime();
        likesList = new ArrayList<String>();
        dislikesList = new ArrayList<String>(); //To files to store
        hidden = new ArrayList<String>();
        edited = false;
        //(Noah) added the part below
        this.textFile = new File(fileName + ".txt");
        this.likesFile = new File(fileName + "_likes.txt");
        this.dislikesFile = new File(fileName + "_dislikes.txt");
        this.hiddenFile = new File(fileName + "_hidden.txt");
        this.editedFile = new File(fileName + "_edited.txt");
        totalPosts++;
        Database.writeFile(new File("postCount.txt"), totalPosts);
    }

    public Post(String username, String fileName) {
        this.username  = username;
        time = getCurrentTime();
        FileReader fr;
        BufferedReader bfr = null;

        this.textFile = new File(fileName + ".txt");
        this.likesFile = new File(fileName + "_likes.txt");
        this.dislikesFile = new File(fileName + "_dislikes.txt");
        this.hiddenFile = new File(fileName + "_hidden.txt");
        this.editedFile = new File(fileName + "_edited.txt");

        try {
            fr = new FileReader(textFile);
            bfr = new BufferedReader(fr);
            this.text = bfr.readLine();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        String line;
        try {
            fr = new FileReader(likesFile);
            bfr = new BufferedReader(fr);
            while((line = bfr.readLine()) != null) {
                this.likesList.add(line);
            }
        } catch(Exception ex) {
            //(Noah) Not printing stack trace because the exception occurs whenever they don't have any likes :(
            ex.printStackTrace();
        }

        try {
            fr = new FileReader(dislikesFile);
            bfr = new BufferedReader(fr);
            while((line = bfr.readLine()) != null) {
                this.dislikesList.add(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } //this will occur whenever they don't have any dislikes :) so no need to print a stack trace.

        try {
            fr = new FileReader(hiddenFile);
            bfr = new BufferedReader(fr);
            while((line = bfr.readLine()) != null) {
                this.hidden.add(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            fr = new FileReader(editedFile);
            bfr = new BufferedReader(fr);
            this.edited = Boolean.parseBoolean(bfr.readLine());
            fr.close();
            bfr.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }


    }

    //GETTERS (Faye)
    public String getUsername() {
        return username;
    }
    public String getText() {
        return text;
    }
    public int getLikesCount() {
        return likesCount;
    }
    public int getDislikesCount() {
        return dislikesCount;
    }
    public int[] getTime() {
        return time;
    }
    public ArrayList<String> getLikesList() {
        return likesList;
    }
    public ArrayList<String> getDislikesList() {
        return dislikesList;
    }
    public ArrayList<String> getHidden() {
        return hidden;
    }
    public boolean isEdited() {
        return edited;
    }

    public int[] getCurrentTime() {//(Tyler) Added Timestamp / getCurrentTime() Method
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        //System.out.println("Timestamp : " + ts); <- Full TimeStamp
        String ts1 = ts.toString();
        int[] time = new int[7];
        time[0] = Integer.parseInt(ts1.substring(0, ts1.indexOf("-"))); //Years
        ts1 = ts1.substring(ts1.indexOf("-") + 1);
        time[1] = Integer.parseInt(ts1.substring(0, ts1.indexOf("-"))); //Months
        ts1 = ts1.substring(ts1.indexOf("-") + 1);
        time[2] = Integer.parseInt(ts1.substring(0, ts1.indexOf(" "))); //Days
        ts1 = ts1.substring(ts1.indexOf(" ") + 2);
        time[3] = Integer.parseInt(ts1.substring(0, ts1.indexOf(":"))); //Hours
        ts1 = ts1.substring(ts1.indexOf(":") + 1);
        time[4] = Integer.parseInt(ts1.substring(0, ts1.indexOf(":"))); //Minutes
        ts1 = ts1.substring(ts1.indexOf(":") + 1);
        time[5] = Integer.parseInt(ts1.substring(0, ts1.indexOf("."))); //Seconds
        ts1 = ts1.substring(ts1.indexOf(".") + 1);
        time[6] = Integer.parseInt(ts1); //Milliseconds
        return time;
    }

    public void editPost(String newText){ //(Noah)
        this.text = newText;
        this.edited = true;
        ArrayList<String> edits = new ArrayList<String>();
        edits.add(Boolean.toString(edited));
        Database.writeFile(editedFile, edits);
        ArrayList<String> texts = new ArrayList<String>();
        texts.add(text);
        Database.writeFile(textFile, texts);
    }

    public boolean hide(String username) {
        if (!hidden.contains(username)) {
            hidden.add(username);
            Database.writeFile(hiddenFile, hidden);
            return true;
        } else if (hidden.contains(username)) {
            hidden.remove(username);
            Database.writeFile(hiddenFile, hidden);
        }
        return false;
    }

    public synchronized boolean like(String username) { //(Noah) so the way it works is a bit confusing
        // but you give the user who's liking it as a parameter.
        if (!likesList.contains(username)) {
            likesList.add(username);
            Database.writeFile(likesFile, likesList);
            likesCount++;
            if (dislikesList.contains(username)) {
                dislikesList.remove(username);
                Database.writeFile(dislikesFile, dislikesList);
                dislikesCount--;
            }
            return true;
        }
        return false;
    }

    public synchronized boolean dislike(String username) { //(Noah)
        if (!dislikesList.contains(username)) {
            dislikesList.add(username);
            dislikesCount++;
            Database.writeFile(dislikesFile, dislikesList);
            if (likesList.contains(username)) {
                likesList.remove(username);
                Database.writeFile(likesFile, likesList);
                likesCount--;
            }
            return true;
        }
        return false;
    }

    public boolean deletePost() {
        try {
            likesFile.delete();
            dislikesFile.delete();
            hiddenFile.delete();
            textFile.delete();
            editedFile.delete();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static int getTotalPosts() {
        return totalPosts;
    }


}
