# Commits Per Deadline Calculator
This Software reports how many commits were submitted in a Git repository classified by configured date intervals.

An example configuration is given in file : deadlines.txt


# How to Use it?

1. Download the latest .JAR file or clone the repository and compile the java code into a JAR distributable package
2. Put the .JAR in the folder where the Git repository is located.
3. Put the Joda-time library .JAR in the folder where the Git repository is located. The Joda-time library .JAR can be found in the libs folder
4. Create a file in the same directory where the JAR file was copied with the name: deadlines.txt and edit it according to the model given in the root of this repository
5. Use a Linux/macOS terminal or a Windows Bash (Making sure that Git is properly installed and its path is properly configured) to execute the jar with the following command:

    ```
    java -jar nameOfTheJARFile.jar
    ```

This software uses the ```git log``` command to obtain the required data, so test for the correct execution of that command in your terminal/bash before executing this software