package MathsHelper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandQuestionTest {

    @Test
    void test() {
        RandQuestion question = new RandQuestion();
        assertTrue(question.getOpt() == '+' || question.getOpt() == '-');
        assertTrue(0 <= question.getNum1() && 100 > question.getNum1());
        assertTrue(0 <= question.getNum2() && 100 > question.getNum2());
        if (question.getOpt() == '+') {
            assertEquals(question.getAns(), question.getNum1() + question.getNum2());
        }
        if (question.getOpt() == '-') {
            assertEquals(question.getAns(), question.getNum1() - question.getNum2());
        }
        assertTrue(question.getAns() >= 0 && question.getAns() < 100);

        question.refresh();
        assertTrue(question.getOpt() == '+' || question.getOpt() == '-');
        assertTrue(0 <= question.getNum1() && 100 > question.getNum1());
        assertTrue(0 <= question.getNum2() && 100 > question.getNum2());
        if (question.getOpt() == '+') {
            assertEquals(question.getAns(), question.getNum1() + question.getNum2());
        }
        if (question.getOpt() == '-') {
            assertEquals(question.getAns(), question.getNum1() - question.getNum2());
        }
        assertTrue(question.getAns() >= 0 && question.getAns() < 100);
    }
}