import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static final Scanner INPT = new Scanner(System.in);
    static STATE state = Main.STATE.RUNNING;
    static MODE mode = Main.MODE.DATES;

    private static ArrayList<String> readFile() {
        try {
            //Text format must be in "clue,answer,category;"
            //enter filepath for txt file
            File file = new File("C:\\Users\\DINO BLOOD BATHS\\IdeaProjects\\FlashCards\\src\\SequenceDates");
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            StringBuilder total = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                total.append(line);
            }

            ArrayList<String> sets = new ArrayList<>();
            String[] groups = total.toString().split(";");

            Collections.addAll(sets, groups);

            return sets;
        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void main(String[] args) {
        //-----------------------------------------------Minimum date (if in date mode)
        System.out.print("Enter minimum year for dates ");
        int minD = 0;
        String userMinD = INPT.next();
        if (userMinD.equals("no"))
            mode = Main.MODE.REG;
        else
            minD = Integer.parseInt(userMinD);
        //-----------------------------------------------Should show the right answer?
        System.out.print("Would you like to be shown the correct answer? ");
        boolean showAns = INPT.next().toLowerCase().equals("yes");
        INPT.nextLine();
        //------------------------------------------------Category
        System.out.print("Enter the category of questions ");
        String cat = INPT.nextLine();
        if (cat.contains(" "))
            cat = cat.replaceAll(" ", "");

        Cards c = new Cards(readFile(), minD, cat);
        //------------------------------------------------Cards
        while (state == Main.STATE.RUNNING) {
            if (c.generateQuestion()) {
                System.out.println("Correct!");
            }
            else {
                System.out.println("Sorry, that is incorrect.");
                if (showAns)
                    System.out.println("The answer is " + c.getA());
            }
        }
        System.exit(0);
    }

    public enum STATE {
        RUNNING,
        STOP
    }

    public enum MODE {
        DATES,
        REG
    }
}
