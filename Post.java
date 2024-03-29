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
    private final int postNumber; //(Noah) this is a bit odd, but i realized we need to have a way to separate each post's like/dislike files from each other.
    //with users we used the usernames to do that but posts don't have a name so I added a number to use to separate each one.
    private File likesFile;
    private File dislikesFile;
    private boolean edited;

    public Post(String username, String text, int postNumber) {
        this.username = username;
        this.text = text;
        likes = 0;
        dislikes = 0;
        time = getCurrentTime(); //This Too <-
        liked = new ArrayList<String>(); //(Tyler) These still need to be written
        disliked = new ArrayList<String>(); //To files to store (probably in Post Method)
        edited = false;
        //(Noah) added the part below, mostly just copy pasted from the User constructor because it's mostly the same thing.
        this.likesFile = new File(username + "_" + postNumber + "_likes.txt");
        this.dislikesFile = new File(username + "_" + postNumber + "_dislikes.txt");

        try {
            FileReader fr = new FileReader(likesFile);
            BufferedReader bfr = new BufferedReader(fr);
            String line;
            while(true) {
                line = bfr.readLine();
    
                if (line == null)
                    break;
                this.liked.add(line);
            }
        } catch (Exception ex) {
            continue;
        } //(Noah) Not printing stack trace because the exception occurs whenever they don't have any likes :(

        try {
            fr = new FileReader(dislikesFile);
            bfr = new BufferedReader(fr);
    
            while(true) {
                line = bfr.readLine();
    
                if (line == null)
                    break;
                this.disliked.add(line);
            }
        } catch (Exception ex) {
            continue;
        } //this will occur whenever they don't have any dislikes :) so no need to print a stack trace.      

        bfr.close();
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
    }
    public boolean liking(String username) { //(Tyler)
    // New Like System (possibly) Uses arraylist to store the usernames
    // of people that have liked the post, removes there username if already in list (removing like)
    // or adds them if not (liking post)
        //Also not sure when to return what -- (to be discussed)
        if (liked.contains(username)) {
            liked.remove(username);
            likes--;
        } else {
            liked.add(username);
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
            disliked.remove(username);
            dislikes--;
        } else {
            disliked.add(username);
            dislikes++;
        }
    //Increments dislikes count here to 
    //not use up resources with .size()
    }
    //Still need Methods to Write and Read Likes, Dislikes, 
    //and timestamp to store and retrieve them

    public boolean editPost(String newText){ //(Noah)
        this.text = newText;
        this.edited = true;
    }

    public boolean like(String username) { //(Noah)
        if (!liked.contains(username)) {
            liked.add(username);
            writeFile(likesFile, liked);
            if (disliked.contains(username)) {
                disliked.remove(username);
                writeFile(dislikesFile, disliked);
            }
            return true;
        }
        return false;
    }

    public boolean dislike(String username) { //(Noah)
        if (!disliked.contains(username)) {
            disliked.add(username);
            writeFile(dislikesFile, disliked);
            if (liked.contains(username)) {
                liked.remove(username);
                writeFile(likesFile, liked);
            }
            return true;
        }
        return false;
    }

    
    
}
    
