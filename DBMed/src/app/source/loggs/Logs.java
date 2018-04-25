package loggs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logs {
    private static Logs ourInstance = new Logs();

    public static Logs getInstance() {
        return ourInstance;
    }

    private static final String path = System.getProperty("user.dir");
    private static final StringBuilder logSB = new StringBuilder();

    public synchronized void logIn(String log){

        logSB.append(log + "\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/src/app/source/loggs/logs.txt"))){
            writer.write(logSB.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Logs() { }
}
