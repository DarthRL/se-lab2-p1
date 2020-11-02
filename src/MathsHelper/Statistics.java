package MathsHelper;

public class Statistics {
    int tot = 0;
    int right = 0;

    public void incTot() {
        tot++;
    }

    public void incRight() {
        right++;
    }
    public void refresh() {
        tot = 0;
        right = 0;
    }
    public int getRight() {
        return right;
    }

    public int getTot() {
        return tot;
    }
}