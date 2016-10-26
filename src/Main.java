import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static HashMap<String, Integer> daysOfWeek = new HashMap<>();
    static HashMap<String, Integer> monthsOfYear = new HashMap<>();
    static ArrayList<int[]> deadlines = new ArrayList<int[]>();

    public static void main(String[] args) {

        initialize();
        int totalRepoCommits = 0;
        int totalRepoCommits2 = 0;
        //Obtain Working Directory
        String repoNameCommand = "pwd";
        ArrayList repoName = executeCommand(repoNameCommand);
        System.out.println("--------------------------------------------------------");
        System.out.println("Working Directory: " + repoName.get(0));


        //Obtain Git Commit Dates
        String command = "git log --pretty=%cd";
        ArrayList<String> output = executeCommand(command);
        ArrayList<Integer> commitTracking = new ArrayList<Integer>();

        for (int i = 0; i < output.size(); i++) {
            commitTracking.add(0);
        }

        for (int[] deadline : deadlines) {


            int[] commits = {0, 0, 0, 0, 0, 0, 0};
            int totalCommits = 0;
            int initialDateDayOfWeek = deadline[0];
            int initialDateMonth = deadline[1];
            int initialDateDayOfMonth = deadline[2];
            int finalDateDayOfWeek = deadline[3];
            int finalDateMonth = deadline[4];
            int finalDateDayOfMonth = deadline[5];

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.MONTH, initialDateMonth - 1);
            calendar.set(Calendar.DATE, initialDateDayOfMonth);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date initialDate = calendar.getTime();

            calendar.set(Calendar.MONTH, finalDateMonth - 1);
            calendar.set(Calendar.DATE, finalDateDayOfMonth);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            Date finalDate = calendar.getTime();

            //Each line is one commit
            for (String line : output) {

                if (deadlines.indexOf(deadline) == 0) {
                    totalRepoCommits++;
                }

                //Get the day of the week
                String dayOfWeek = line.substring(0, line.indexOf(" ", 0)).toLowerCase();
                int dayOfWeekInt = daysOfWeek.get(dayOfWeek);

                //Get the day of the week
                String monthOfYear = line.substring(line.indexOf(" ", 0) + 1, line.indexOf(" ", 4)).toLowerCase();
                int monthOfYearInt = monthsOfYear.get(monthOfYear);

                int dayOfMonth = Integer.valueOf(line.substring(line.indexOf(" ", 4) + 1, line.indexOf(" ", 8)).toLowerCase());

                //Inside DeathLine

                calendar.set(Calendar.MONTH, monthOfYearInt - 1);
                calendar.set(Calendar.DATE, dayOfMonth);
                calendar.set(Calendar.SECOND, 1);
                Date commitDate = calendar.getTime();

                if (commitDate.getTime() >= initialDate.getTime() && commitDate.getTime() < finalDate.getTime()) {
                    totalCommits++;
                    commits[dayOfWeekInt - 1] = commits[dayOfWeekInt - 1] + 1;
                    commitTracking.set(output.indexOf(line), 1);
                }

            }

            System.out.println("________________________________________________________________________________________ ");
            System.out.println("Deathline " + deadlines.indexOf(deadline) + " (" + initialDate + " to " + finalDate + "): ");
            System.out.println();
            System.out.println("Monday: " + commits[0]);
            System.out.println("Tuesday: " + commits[1]);
            System.out.println("Wednesday: " + commits[2]);
            System.out.println("Thursday: " + commits[3]);
            System.out.println("Friday: " + commits[4]);
            System.out.println("Saturday: " + commits[5]);
            System.out.println("Sunday: " + commits[6]);
            System.out.println("Total Commits: " + totalCommits);
            System.out.println("________________________________________________________________________________________ ");
            totalRepoCommits2 += totalCommits;
        }

        for (Integer a : commitTracking) {
            if (a == 0) {
                System.out.println("Commit Faltante: " + output.get(commitTracking.indexOf(a)));
            }
        }


        System.out.println("Repository Commits 1: " + totalRepoCommits);
        System.out.println("Repository Commits 2: " + totalRepoCommits2);

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

        int[] deadline1 = {1, 8, 31, 7, 9, 13};
        int[] deadline2 = {1, 9, 14, 7, 10, 4};
        int[] deadline3 = {1, 10, 5, 7, 10, 18};
        int[] deadline4 = {1, 10, 19, 7, 11, 1};
        int[] deadline5 = {1, 11, 2, 7, 11, 15};
        int[] deadline6 = {1, 11, 16, 7, 12, 29};

        deadlines.add(deadline1);
        deadlines.add(deadline2);
        deadlines.add(deadline3);
        deadlines.add(deadline4);
        deadlines.add(deadline5);
        deadlines.add(deadline6);
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
