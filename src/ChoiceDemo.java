import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChoiceDemo extends JFrame {
    Choice myChoice;
    JLabel jlb;

    JTextField jtf;

    JButton jbtn;

    ChoiceDemo()
    {
        this.setBounds(300,200,500,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        jlb = new JLabel("选择班级：");
        jlb.setBounds(100,100,100,50);

        jbtn = new JButton("确定");
        jbtn.setBounds(300,250,60,40);

        jtf = new JTextField();
        jtf.setBounds(100,20,200,50);

        //创建Choice下拉选框类对象
        myChoice = new Choice();
        myChoice.setBounds(250,100,200,50);
        //添加选项
        myChoice.add("安卓一班");
        myChoice.add("安卓二班");
        myChoice.add("安卓三班");

        //对下拉选框添加事件监听器，通过选择直接获取选项
        myChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println(myChoice.getSelectedItem());
            }
        });

        this.add(myChoice);
        this.add(jlb);
        this.add(jbtn);
        this.add(jtf);

        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(myChoice.getSelectedItem());
            }
        });

        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });

        this.setVisible(true);


    }
}
