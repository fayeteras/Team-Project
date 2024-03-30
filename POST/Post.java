import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Post implements PostInterface {
    private final String username;
    private int likes;
    private int dislikes;
    private String text;
    private int[] time;
    private ArrayList<String> liked;
    private ArrayList<String> disliked;
    private final File textFile;
    private final File likesFile;
    private final File dislikesFile;
    private ArrayList<Comment> comments;
    private boolean edited;

    public Post(String username, String text, String fileName) {
        this.username = username;
        this.text = text;
        likes = 0;
        dislikes = 0;
        time = getCurrentTime(); //This Too <-
        liked = new ArrayList<String>(); //(Tyler) These still need to be written
        disliked = new ArrayList<String>(); //To files to store (probably in Post Method)
        edited = false;
        //(Noah) added the part below, mostly just copy pasted from the User constructor because it's mostly the same thing.
        this.textFile = new File(fileName + ".txt");
        this.likesFile = new File(fileName + "_likes.txt");
        this.dislikesFile = new File(fileName + "_dislikes.txt");
    }

    public Post(String username, String fileName) {
        this.username  =username;
        time = getCurrentTime();
        FileReader fr;
        BufferedReader bfr = null;

        this.textFile = new File(fileName + ".txt");
        this.likesFile = new File(fileName + "_likes.txt");
        this.dislikesFile = new File(fileName + "_dislikes.txt");

        try {
            fr = new FileReader(textFile);
            bfr = new BufferedReader(fr);
            this.text = bfr.readLine();
        } catch(Exception ex) {
            //(Noah) ok idk the best way to do this but it doesn't need to do a single thing here. feels weird having
            //an empty thing here but i mean this should do it.
            //(Noah) Not printing stack trace because the exception occurs whenever they don't have any likes :(
        }

        try {
            fr = new FileReader(likesFile);
            bfr = new BufferedReader(fr);
            String line;
            while(true) {
                line = bfr.readLine();

                if (line == null)
                    break;
                this.liked.add(line);
            }
        } catch(Exception ex) {
            //(Noah) ok idk the best way to do this but it doesn't need to do a single thing here. feels weird having
            //an empty thing here but i mean this should do it.
            //(Noah) Not printing stack trace because the exception occurs whenever they don't have any likes :(
        }

        try {
            fr = new FileReader(dislikesFile);
            bfr = new BufferedReader(fr);

            while(true) {
                String line = bfr.readLine();

                if (line == null)
                    break;
                this.disliked.add(line);
            }
        } catch (Exception ex) {
        } //this will occur whenever they don't have any dislikes :) so no need to print a stack trace.
    }
    //(savni)
    public String getText() {
        return text;
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
    // public boolean likes(String username) { //(Tyler)
    // // New Like System (possibly) Uses arraylist to store the usernames
    // // of people that have liked the post, removes there username if already in list (removing like)
    // // or adds them if not (liking post)
    //     //Also not sure when to return what -- (to be discussed)
    //     /*
    //     if (liked.contains(username)) {
    //         liked.remove(username);
    //         likes--;
    //     } else {
    //         liked.add(username);
    //         likes++;
    //     } */
    //     //(Noah) see below comment ope
    //     return liked.contains(username);
    // //Increments likes count here to
    // //not use up resources with .size()
    // }
    // public boolean dislikes(String username) { //(Tyler)
    // // New Dislike System (possibly) Uses arraylist to store the usernames
    // // of people that have disliked the post, removes there username if already in list (removing dislike)
    // // or adds them if not (disliking post)
    //     //Also not sure when to return what -- (to be discussed)
    //     /*
    //     if (disliked.contains(username)) {
    //         disliked.remove(username);
    //         dislikes--;
    //     } else {
    //         disliked.add(username);
    //         dislikes++;
    //     }
    //      */
    //     return disliked.contains(username); //(Noah)ok so i didn't realize that this was doing that sorry. but i
    //     //think it does make more sense for adding a dislike and returning dislikes to be separate things so i just
    //     //did that.
    // //Increments dislikes count here to
    // //not use up resources with .size()
    // }
    //Still need Methods to Write and Read Likes, Dislikes,
    //and timestamp to store and retrieve them

    public void editPost(String newText){ //(Noah)
        this.text = newText;
        this.edited = true;
    }

    public boolean like(String username) { //(Noah) so the way it works is a bit confusing but you give the user
        //who's liking it as a parameter.
        if (!liked.contains(username)) {
            liked.add(username);
            Database.writeFile(likesFile, liked);
            if (disliked.contains(username)) {
                disliked.remove(username);
                Database.writeFile(dislikesFile, disliked);
            }
            return true;
        }
        return false;
    }

    public boolean dislike(String username) { //(Noah)
        if (!disliked.contains(username)) {
            disliked.add(username);
            Database.writeFile(dislikesFile, disliked);
            if (liked.contains(username)) {
                liked.remove(username);
                Database.writeFile(likesFile, liked);
            }
            return true;
        }
        return false;
    }

    public boolean deletePost() {
        try {
            likesFile.delete();
            dislikesFile.delete();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //need a delete post method

}

