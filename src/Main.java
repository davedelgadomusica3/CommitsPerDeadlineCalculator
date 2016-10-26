import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static int totalCommits = 0;

    public static void main(String[] args) {

        //Obtain Working Directory
        String repoNameCommand = "pwd";
        ArrayList repoName = executeCommand(repoNameCommand);
        System.out.println("--------------------------------------------------------");
        System.out.println("Working Directory: " + repoName.get(0));


        //Obtain Git Commit Dates
        String command = "git log --pretty=%cd";
        ArrayList<String> output = executeCommand(command);


        //Each line is from the output of commit
        for (String line : output) {
            totalCommits++;

        }

        System.out.println("Repository");
        System.out.println("Total Commits: " + totalCommits);

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
