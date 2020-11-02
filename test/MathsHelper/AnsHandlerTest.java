package MathsHelper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnsHandlerTest {

    @Test
    void test() {
        RandQuestion question = new RandQuestion();
        int right_answer = question.getAns();
        int wrong_answer = question.getAns() + 1;
        AnsHandler handler = new AnsHandler(question, right_answer);
        assertTrue(handler.isRight());
        assertEquals(right_answer, handler.getUserAns());
        assertEquals(right_answer, handler.getTrueAns());
        handler = new AnsHandler(question, wrong_answer);
        assertFalse(handler.isRight());
        assertEquals(wrong_answer, handler.getUserAns());
        assertEquals(right_answer, handler.getTrueAns());
    }
}