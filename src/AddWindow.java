import jdk.nashorn.internal.scripts.JO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class AddWindow extends JFrame implements ActionListener {
    JLabel jlb_name,jlb_gender,jlb_age,jlb_nation;
    JTextField jtf_name,jtf_gender,jtf_age,jtf_nation;
    JButton btn_add,btn_clear;

    //构造方法
    AddWindow()
    {
        this.setSize(400,200);
        this.setLocation(400,300);
        this.setLayout(null);

        jlb_name  = new JLabel("姓名：");
        jlb_name.setBounds(20,20,60,20);
        jlb_age = new JLabel("年龄");
        jlb_age.setBounds(200,20,60,20);
        jlb_gender = new JLabel("性别");
        jlb_gender.setBounds(20,80,60,20);
        jlb_nation = new JLabel("国籍");
        jlb_nation.setBounds(200,80,60,20);

        jtf_name = new JTextField();
        jtf_name.setBounds(100,20,60,20);
        jtf_age = new JTextField();
        jtf_age.setBounds(250,20,60,20);
        jtf_gender = new JTextField();
        jtf_gender.setBounds(100,80,60,20);
        jtf_nation = new JTextField();
        jtf_nation.setBounds(250,80,60,20);

        btn_add = new JButton("添加");
        btn_add.setBounds(180,130,50,20);
        btn_clear = new JButton("清空");
        btn_clear.setBounds(250,130,50,20);

        this.add(jtf_name);
        this.add(jlb_name);
        this.add(jlb_age);
        this.add(jtf_age);
        this.add(jlb_gender);
        this.add(jtf_gender );
        this.add(jtf_nation);
        this.add(jlb_nation);
        this.add(btn_add);
        this.add(btn_clear);

        //监听器
        btn_add.addActionListener(this);
        btn_clear.addActionListener(this);


        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("添加"))
        {
            addStu();
        }else if(e.getActionCommand().equals("清空"))
        {
            //把四个文本框清空
            jtf_name.setText("");
            jtf_age.setText("");
            jtf_gender.setText("");
            jtf_nation.setText("");
        }
    }

    void addStu()
    {


            DBconn db = new DBconn();

            String sql = "INSERT INTO table_stu(stuname,stusex,stuage,stunation) VALUES(?,?,?,?)";

            db.prepareStr(sql);

            db.setPara(1,jtf_name.getText());
            db.setPara(2,jtf_gender.getText());
            db.setPara(3,Integer.parseInt(jtf_age.getText()));
            db.setPara(4,jtf_nation.getText());

            if(db.update()!=-1)
            {
                JOptionPane.showConfirmDialog(this,"添加信息成功");
                this.setVisible(false);
            }else
            {
                JOptionPane.showConfirmDialog(this,"添加失败");
            }

    }
}
