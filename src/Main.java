import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static int totalCommits = 0;

    static HashMap<String, Integer> daysOfWeek = new HashMap<>();
    static HashMap<String, Integer> monthsOfYear = new HashMap<>();

    public static void main(String[] args) {

        initialize();


        //Obtain Working Directory
        String repoNameCommand = "pwd";
        ArrayList repoName = executeCommand(repoNameCommand);
        System.out.println("--------------------------------------------------------");
        System.out.println("Working Directory: " + repoName.get(0));


        //Obtain Git Commit Dates
        String command = "git log --pretty=%cd";
        ArrayList<String> output = executeCommand(command);


        //Each line is one commit
        for (String line : output) {

            //Get the day of the week
            String dayOfWeek = line.substring(0, line.indexOf(" ",0)).toLowerCase();
            int dayOfWeekInt = daysOfWeek.get(dayOfWeek);


            //Get the day of the week
            String monthOfYear = line.substring(line.indexOf(" ",0)+1, line.indexOf(" ",4)).toLowerCase();
            int monthOfYearInt = monthsOfYear.get(monthOfYear);

            int dayOfMonth = Integer.valueOf(line.substring(line.indexOf(" ",4)+1,line.indexOf(" ",8)).toLowerCase());

            
            totalCommits++;


        }

        System.out.println("Repository");
        System.out.println("Total Commits: " + totalCommits);

    }


    public static void initialize() {
        daysOfWeek.put("mon", 1);
        daysOfWeek.put("tue", 2);
        daysOfWeek.put("wed", 3);
        daysOfWeek.put("thu", 4);
        daysOfWeek.put("fri", 5);
        daysOfWeek.put("sat", 6);
        daysOfWeek.put("sun", 7);

        monthsOfYear.put("jan", 1);
        monthsOfYear.put("feb", 2);
        monthsOfYear.put("mar", 3);
        monthsOfYear.put("apr", 4);
        monthsOfYear.put("may", 5);
        monthsOfYear.put("jun", 6);
        monthsOfYear.put("jul", 7);
        monthsOfYear.put("ago", 8);
        monthsOfYear.put("sep", 9);
        monthsOfYear.put("oct", 10);
        monthsOfYear.put("nov", 11);
        monthsOfYear.put("dec", 12);
    }

    private static ArrayList executeCommand(String command) {

        ArrayList<String> output = new ArrayList<String>();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.add(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;

    }

}
