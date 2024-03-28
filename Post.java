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
    //private boolean edited? might be good to have some indicator of that.

    public Post(String username, String text) {
        this.username = username;
        this.text = text;
        likes = 0;
        dislikes = 0;
        time = getCurrentTime(); //This Too <-
        liked = new ArrayList<String>(); //(Tyler) These still need to be written
        disliked = new ArrayList<String>(); //To files to store (probably in Post Method)
        //edited = false
    }
    
    public Int[] getCurrentTime() {//(Tyler) Added Timestamp / getCurrentTime() Method
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
    }
    public boolean liking(String username) { //(Tyler)
    // New Like System (possibly) Uses arraylist to store the usernames
    // of people that have liked the post, removes there username if already in list (removing like)
    // or adds them if not (liking post)
        //Also not sure when to return what -- (to be discussed)
        if (liked.contains(username) {
            liked.remove(username)
            likes--;
        } else {
            liked.add(username)
            likes++;
        }
    //Increments likes count here to 
    //not use up resources with .size()
    }
    public boolean disliking(String username) { //(Tyler)
    // New Dislike System (possibly) Uses arraylist to store the usernames
    // of people that have disliked the post, removes there username if already in list (removing dislike)
    // or adds them if not (disliking post)
        //Also not sure when to return what -- (to be discussed)
        if (disliked.contains(username) {
            disliked.remove(username)
            dislikes--;
        } else {
            disliked.add(username)
            dislikes++;
        }
    //Increments dislikes count here to 
    //not use up resources with .size()
    }
}
    
