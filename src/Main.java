import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
//        int numOfCustomer=0;
//        int accountTypeChoice;
//        Checking check= new Checking();
//        Customer[] c=check.getCustomer();
//        Saving save= new Saving();
//        Customer [] c2=save.getCustomer();
//        Properties properties = new Properties();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//        Scanner in = new Scanner(System.in);
        SystemMenu systemMenu = new SystemMenu();
        String ch;
        do {
            systemMenu.menu();
            System.out.println("\nDo you want continue to the home screen menu [y/n]?: ");
            ch = in.readLine();
        } while (ch.equalsIgnoreCase("y") || ch.equalsIgnoreCase("Y"));


    }
}

