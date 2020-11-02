package MathsHelper;

import java.util.Random;

public class RandQuestion {
    int num1;//运算元一
    int num2;//运算元二
    int ans;//结果
    char opt;//运算符

    public RandQuestion() {
        //要求运算元和结果均在[0, 99]内，运算符为加或减
        Random r = new Random();
        if (r.nextBoolean()) {
            num1 = r.nextInt(99);
            num2 = r.nextInt(99 - num1);
            opt = '+';
            ans = num1 + num2;
        } else {
            num1 = r.nextInt(99);
            num2 = r.nextInt(num1);
            opt = '-';
            ans = num1 - num2;
        }
    }

    public void refresh() {
        Random r = new Random();
        if (r.nextBoolean()) {
            num1 = r.nextInt(99);
            num2 = r.nextInt(99 - num1);
            opt = '+';
            ans = num1 + num2;
        } else {
            num1 = r.nextInt(99);
            num2 = r.nextInt(num1);
            opt = '−';
            ans = num1 - num2;
        }
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public char getOpt() {
        return opt;
    }

    public int getAns() {
        return ans;
    }

}
