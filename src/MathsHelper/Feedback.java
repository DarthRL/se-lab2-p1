package MathsHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Feedback {
    List<String> strings = new ArrayList<String>();
    Random r = new Random();
    public Feedback(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            strings.add("加油！");
            return;
        }
    }

    public String getRandomString() {
        return strings.get(r.nextInt(strings.size()));
    }

}
