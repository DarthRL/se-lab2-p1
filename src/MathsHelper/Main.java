package MathsHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String [] args) {
        //初始化统计器
        Statistics statistics = new Statistics();
        //字体设置
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 20);
        Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, font);
            }
        }
        //窗体
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("一百以内加减法练习软件");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 300));
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);
        //界面上边栏，显示结果统计
        JPanel count_panel = new JPanel();
        JLabel count_label = new JLabel("总题数：0   正确数：0   错误数：0   ");
        count_panel.add(count_label);
        panel.add(count_panel, BorderLayout.NORTH);
        //主界面包括题目面板和按钮面板，共同使用一个GridBagLayout
        JPanel question_panel = new JPanel();
        question_panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //题目面板设计
        //运算元1
        gbc.gridx = 1; gbc.gridy = 0;
        JTextField line1_num = new JTextField();
        line1_num.setEditable(false);
        line1_num.setHorizontalAlignment(JTextField.RIGHT);
        line1_num.setPreferredSize(new Dimension(50, 30));
        question_panel.add(line1_num, gbc);
        //符号及运算元2
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel line2_opr = new JLabel();
        line2_opr.setHorizontalAlignment(JLabel.RIGHT);
        question_panel.add(line2_opr, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        JTextField line2_num = new JTextField();
        line2_num.setEditable(false);
        line2_num.setHorizontalAlignment(JTextField.RIGHT);
        line2_num.setPreferredSize(new Dimension(50, 30));
        question_panel.add(line2_num, gbc);
        //横线
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        question_panel.add(new JLabel("----------", JLabel.RIGHT), gbc);
        //结果输入框
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 1;
        JTextField line3_num = new JTextField();
        line3_num.setHorizontalAlignment(JTextField.RIGHT);
        line3_num.setPreferredSize(new Dimension(50, 30));
        question_panel.add(line3_num, gbc);
        //按钮面板界面设计
        gbc.insets = new Insets(0, 20, 0, 0);
        //提交按钮
        gbc.gridx = 2; gbc.gridy = 3;
        JButton confirm = new JButton("确定");
        confirm.setPreferredSize(new Dimension(100, 30));
        confirm.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        question_panel.add(confirm, gbc);
        panel.add(question_panel);
        //计时按钮
        gbc.gridx = 2; gbc.gridy = 0;
        JButton timing = new JButton("开始计时");
        timing.setPreferredSize(new Dimension(100, 30));
        timing.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        question_panel.add(timing, gbc);
        //计时窗格
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.gridheight = 2;
        JTextArea time_display = new JTextArea("00:00");
        time_display.setEditable(false);
        question_panel.add(time_display, gbc);
        panel.add(question_panel, BorderLayout.CENTER);

        //底端祝福语区域
        JTextArea feedback_area = new JTextArea("加油！");
        JScrollPane bottom_panel = new JScrollPane(feedback_area);
        bottom_panel.setPreferredSize(new Dimension(300, 60));
        feedback_area.setEditable(false);
        feedback_area.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        feedback_area.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        feedback_area.setLineWrap(true);
        //feedback_area.setMinimumSize(new Dimension(100, 50));
        Feedback right_feedback = new Feedback(new File("1.txt"));
        Feedback wrong_feedback = new Feedback(new File("2.txt"));
        panel.add(bottom_panel, BorderLayout.SOUTH);


        RandQuestion question = new RandQuestion();
        line1_num.setText("" + question.getNum1());
        line2_opr.setText(" " + question.getOpt() + "  ");
        line2_num.setText("" + question.getNum2());
        //设置提交答案事件
        confirm.addActionListener(actionEvent -> {
            //判断答案框中是否为数字
            if (line3_num.getText().length() > 2 || line3_num.getText().length() < 1)
                return;
            for (char c : line3_num.getText().toCharArray()) {
                if (!Character.isDigit(c))
                    return;
            }
            //判断答案是否正确并刷新界面
            AnsHandler ans_handler = new AnsHandler(question, Integer.parseInt(line3_num.getText()));
            if (ans_handler.isRight()) {
                statistics.incRight();
                feedback_area.setText(right_feedback.getRandomString());
            }
            else {
                feedback_area.setText(wrong_feedback.getRandomString());
            }
            statistics.incTot();
            count_label.setText("总题数："+statistics.getTot()+"   正确数:"+statistics.getRight()+"   错误数:"+(statistics.getTot()-statistics.getRight()));
            question.refresh();
            line1_num.setText("" + question.getNum1());
            line2_opr.setText(" " + question.getOpt() + "");
            line2_num.setText("" + question.getNum2());
            line3_num.setText("");
            line3_num.requestFocusInWindow();
        });
        //在输入框中回车也可提交
        line3_num.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirm.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });

        //设置计时器事件
        AtomicInteger sec_counter = new AtomicInteger();
        //计时器执行的任务
        class Ticker implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //增加计数器
                sec_counter.incrementAndGet();
                //超出显示范围则显示1h+
                if (sec_counter.get() / 60 > 59) {
                    time_display.setText("1h+");
                    return;
                }
                //显示分:秒
                time_display.setText(String.format("%02d:%02d", sec_counter.get() / 60, sec_counter.get() % 60));
            }
        }
        Timer t = new Timer(1000, new Ticker());
        timing.addActionListener(actionEvent -> {
            if (timing.getText().equals("开始计时")) {
                timing.setText("停止计时");
                //计时器清零
                sec_counter.set(0);
                time_display.setText(String.format("%02d:%02d", sec_counter.get() / 60, sec_counter.get() % 60));
                t.start();
            }
            else {
                timing.setText("开始计时");
                t.stop();
            }
        });

        frame.setVisible(true);
    }
}
