import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIDemo extends JFrame {
    Choice c;
    JLabel jlb;
    JTextField jtf;
    JButton jbtn;



    GUIDemo()
    {

        setLayout(null);
        setBounds(200,100,500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jlb = new JLabel("性别：");
        jlb.setBounds(100,200,80,50);

        c = new Choice();
        c.add("男");
        c.add("女");
        c.setBounds(200,200,90,50);

        c.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Toolkit.getDefaultToolkit().beep();
                System.out.println(c.getSelectedIndex());
            }
        });

        jtf = new JTextField();
        jtf.setBounds(100,300,200,50);

        jtf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e )
            {
                System.out.println(e.getKeyCode());
                if(e.getKeyCode()==10)
                {
                    System.out.println(jtf.getText());
                }
            }
        });

        jbtn = new JButton("确定");
        jbtn.setBounds(350,300,100,50);

        add(c);
        add(jlb);
        add(jtf);
        add(jbtn);
        setVisible(true);
    }
}
