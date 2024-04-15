import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.testng.AssertJUnit.assertTrue;

public class ClientPostTest {

//    @Test(timeout = 1000)
//    public void testCreatePost() {
//        //
//    }

    @Test (timeout = 1000)
    public void testLike() {
        String systemIn = "filename";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        try {
            boolean tester = Client.like(scan, null, writer);
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testDislike() {
        String systemIn = "filename";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        try {
            boolean tester = Client.dislike(scan, null, writer);
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testHide() {
        String systemIn = "filename";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        try {
            boolean tester = Client.hide(scan, null, writer);
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
