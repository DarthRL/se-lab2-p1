package MathsHelper;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    //测试是否能正确取出字符串
    @Test
    void getRandomString() {
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        File file = new File("test.txt");
        try {
            file.createNewFile();
            file.deleteOnExit();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(String s : strings) {
                writer.write(s + '\n');
            }
            writer.close();
            Feedback feedback = new Feedback(file);
            assertTrue(strings.contains(feedback.getRandomString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}