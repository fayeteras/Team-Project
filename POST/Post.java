import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Post implements PostInterface {
    private final String username;
    private int likes;
    private int dislikes;
    private String text;
    private ArrayList<String> liked;
    private ArrayList<String> disliked;
    private final File textFile;
    private final File likesFile;
    private final File dislikesFile;
    private boolean edited;
    public boolean isHidden;

    public Post(String username, String text, String fileName) {
        this.username = username;
        this.text = text;
        likes = 0;
        dislikes = 0;
        liked = new ArrayList<>();
        disliked = new ArrayList<>();
        edited = false;
        textFile = new File(fileName + ".txt");
        likesFile = new File(fileName + "_likes.txt");
        dislikesFile = new File(fileName + "_dislikes.txt");
    }

    public Post(String username, String fileName) {
        this.username = username;
        FileReader fr;
        BufferedReader bfr;

        textFile = new File(fileName + ".txt");
        likesFile = new File(fileName + "_likes.txt");
        dislikesFile = new File(fileName + "_dislikes.txt");

        try {
            fr = new FileReader(textFile);
            bfr = new BufferedReader(fr);
            this.text = bfr.readLine();
        } catch (Exception ignored) {
            // Handle file reading exception
        }

        liked = readFromFile(likesFile);
        disliked = readFromFile(dislikesFile);
    }


    public int[] getCurrentTime() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
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

    public void editPost(String newText) {
        this.text = newText;
        this.edited = true;
    }

    @Override
    public boolean likes(String username) {
        if (liked.contains(username)) {
            liked.remove(username);
            likes--;
            writeToFile(likesFile, liked);
            return false;
        } else {
            liked.add(username);
            likes++;
            writeToFile(likesFile, liked);
            if (disliked.contains(username)) {
                disliked.remove(username);
                dislikes--;
                writeToFile(dislikesFile, disliked);
            }
            return true;
        }
    }

    @Override
    public boolean dislikes(String username) {
        if (disliked.contains(username)) {
            disliked.remove(username);
            dislikes--;
            writeToFile(dislikesFile, disliked);
            return false;
        } else {
            disliked.add(username);
            dislikes++;
            writeToFile(dislikesFile, disliked);
            if (liked.contains(username)) {
                liked.remove(username);
                likes--;
                writeToFile(likesFile, liked);
            }
            return true;
        }
    }

    public void hidePost() {
        isHidden = true;
    }


    public boolean like(String username) {
        return true;
    }


    public boolean dislike(String username) {
        return true;
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

    private ArrayList<String> readFromFile(File file) {
        ArrayList<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException ignored) {
            // Handle file reading exception
        }
        return list;
    }

    private void writeToFile(File file, ArrayList<String> list) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String item : list) {
                bw.write(item);
                bw.newLine();
            }
            bw.close();
        } catch (IOException ignored) {
            // Handle file writing exception
        }
    }

    public String getText() {
        return text;
    }
}
