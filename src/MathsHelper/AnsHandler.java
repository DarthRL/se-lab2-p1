package MathsHelper;

public class AnsHandler {
    public RandQuestion ques;//题目
    public int user_ans;//传入的用户答案
    public int true_ans;//正确答案

    public AnsHandler(RandQuestion ques, int user_ans) {
        this.ques = ques;
        this.user_ans = user_ans;
        this.true_ans = ques.getAns();
    }

    public int getUserAns() {
        return user_ans;
    }

    public int getTrueAns() {
        return true_ans;
    }

    public boolean isRight() {
        return user_ans == true_ans;
    }
}
