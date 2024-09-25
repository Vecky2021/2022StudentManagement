import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailWindow extends JFrame implements ActionListener {

    JLabel jlb_name,jlb_gender,jlb_age,jlb_nation;
    JTextField jtf_name,jtf_gender,jtf_age,jtf_nation;
    JButton btn_modify,btn_del;

    //构造方法
    DetailWindow(String stu_name)
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
        jtf_name.setEditable(false);
        jtf_age = new JTextField();
        jtf_age.setBounds(250,20,60,20);
        jtf_gender = new JTextField();
        jtf_gender.setBounds(100,80,60,20);
        jtf_nation = new JTextField();
        jtf_nation.setBounds(250,80,60,20);

        btn_modify = new JButton("修改");
        btn_modify.setBounds(180,130,50,20);
        btn_del = new JButton("删除");
        btn_del.setBounds(250,130,50,20);

        this.add(jtf_name);
        this.add(jlb_name);
        this.add(jlb_age);
        this.add(jtf_age);
        this.add(jlb_gender);
        this.add(jtf_gender );
        this.add(jtf_nation);
        this.add(jlb_nation);
        this.add(btn_modify);
        this.add(btn_del);

        //从数据库获取详情页的信息
        DBconn db = new DBconn();
        db.prepareStr("SELECT * FROM table_stu WHERE stuname = ?");
        db.setPara(1,stu_name);
        ResultSet rs = db.querry();

        try {
            rs.next();
            jtf_name.setText(rs.getString(2));
            jtf_gender.setText(rs.getString(3));
            jtf_age.setText(rs.getInt(4)+"");
            jtf_nation.setText(rs.getString(5));


        } catch (SQLException e) {
            e.printStackTrace();
        }


        //监听器
        btn_modify.addActionListener(this);
        btn_del.addActionListener(this);


        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("修改"))
        {
            modify();
        }else if(e.getActionCommand().equals("删除"))
        {
            //弹框提示挽留
            if(JOptionPane.showConfirmDialog(this,"是否真的删除？")==1)
            {
                delelte();
            }
//
        }
    }

    void modify()
    {
        DBconn db = new DBconn();
        db.prepareStr("UPDATE table_stu SET stuage = ?, stunation = ?, stusex = ?WHERE stuname = ?");
        db.setPara(1,Integer.parseInt(jtf_age.getText()));
        db.setPara(2,jtf_nation.getText());
        db.setPara(3,jtf_gender.getText());
        db.setPara(4,jtf_name.getText());

        if(db.update()==1)
        {
            //修改成功,弹框，管窗口
            JOptionPane.showMessageDialog(this,"修改成功");
            this.setVisible(false);

        }
    }

    void delelte()
    {
        DBconn db = new DBconn();
        db.prepareStr("DELETE FROM table_stu WHERE stuname = ?");
        db.setPara(1,jtf_name.getText());

        if(db.update()==1)
        {
            //删除成功,弹框，管窗口
            JOptionPane.showMessageDialog(this,"删除");
            this.setVisible(false);
        }
    }
}
