import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.sql.*;

//主窗口
//让其继承JFrame类

public class MainWindow extends JFrame implements ActionListener
{
    //类的属性
    int haveJsp = 0;

    JButton btn_show,btn_add,btn_search,btn_sort; //四个按钮
    JTextField jtf_search;  //一个单行文本框
    JMenuBar jmb; //菜单栏，一整行
    JMenu jm_file,jm_edit,jm_help; //菜单，具体每一个可点选的项
    JMenuItem jmi_open,jmi_save,jmi_exit,jmi_selectall,jmi_about;



    MainWindow()  //构造方法
    {

        this.setBounds(500,200,700,500);
        this.setLayout(null);
        //设置窗口不能被用户调整尺寸
        this.setResizable(false);

        //创建文本框、按钮对象
        btn_show = new JButton(("显示所有"));
        btn_show.setBounds(20,20,100,40);
        btn_add = new JButton(("添加信息"));
        btn_add.setBounds(140,20,100,40);
        btn_search = new JButton(("搜索"));
        btn_search.setBounds(440,20,100,40);
        btn_sort = new JButton("排序");
        btn_sort.setBounds(560,20,100,40);

        jtf_search = new JTextField();
        jtf_search.setBounds(260,20,160,40);


        //创建菜单组件
        jmb = new JMenuBar();

        jm_edit = new JMenu("编辑");
        jm_file = new JMenu("文件");
        jm_help = new JMenu("帮助");

        jmi_about = new JMenuItem("关于");
        jmi_exit = new JMenuItem("退出");
        jmi_selectall = new JMenuItem("全选");
        jmi_open = new JMenuItem("打开");
        jmi_save = new JMenuItem("保存");

        //把菜单添加到主界面上
        //把菜单项添加到菜单上
        jm_file.add(jmi_open);
        jm_file.add(jmi_save);
        jm_file.add(jmi_exit);

        jm_edit.add(jmi_selectall);
        jm_help.add(jmi_about);
        //把菜单添加到菜单栏上
        jmb.add(jm_file);
        jmb.add(jm_edit);
        jmb.add(jmi_about);
        //把菜单栏添加到主界面上
        this.setJMenuBar(jmb);

        //把组件添加到窗口中
        this.add(btn_show);
        this.add(btn_add);
        this.add(btn_search);
        this.add(btn_sort);
        this.add(jtf_search);

        //对按钮实现监听
        btn_show.addActionListener(this);
        btn_add.addActionListener(this);
        btn_search.addActionListener(this);
        btn_sort.addActionListener(this);
        //对菜单项添加事件监听器
        jmi_exit.addActionListener(this);

        this.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(e.getActionCommand());
        //当点击显示所有按钮时，调用显示所有的功能
        if(e.getActionCommand().equals("显示所有"))
        {
            showAll();
        }else if(e.getActionCommand().equals("搜索"))
        {
            searchStu();
        }else if(e.getActionCommand().equals("添加信息"))
        {
            new AddWindow();
        }else if(e.getActionCommand().equals("退出"))
        {
            System.exit(0);
        }
    }


    public void searchStu()
    {

        //创建一个表格对象通过Vector动态数组对象来创建JTable
        Vector name = new Vector();  //字段名，一维的动态数组
        name.add("姓名");
        name.add("性别");
        name.add("年龄");
        name.add("国际");

        Vector data = new Vector(); //二维动态数组，用于保存数据


        //获取搜索文本框的内容
        String search_name = jtf_search.getText();

        DBconn db = new DBconn();

        db.prepareStr("SELECT * FROM table_stu WHERE stuname = ?");

        db.setPara(1,jtf_search.getText());

        ResultSet rs = db.querry();

        try {
            while (rs.next()) {
//                System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getInt(4)+rs.getString(5));
                Vector hang = new Vector(); //一维动态数组，用于保存一行的数据
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getInt(4));
                hang.add(rs.getString(5));
                data.add(hang); //把这一行动态数组添加到二维动态数组中
            }
        }catch(SQLException e)
        {
            e.getStackTrace();
            JOptionPane.showMessageDialog(this,"数据库错误");
        }

            JTable jt = new JTable(data,name);

            //通过表格对象创建一个支持鼠标滚轮的面板容器
            JScrollPane jsp = new JScrollPane(jt);
            jsp.setBounds(20,80,660,380);

            //添加表格面板之前先判断之前是否已存在该面板
            //如果有则移除
            if(haveJsp==1)
            {
                this.remove(jsp);
            }
            this.add(jsp);
            haveJsp = 1;
            this.setVisible(true);



    }


    //显示所有同学信息的方法
    public void showAll()
    {
        //创建一个表格对象通过Vector动态数组对象来创建JTable
        Vector name = new Vector();  //字段名，一维的动态数组
        name.add("姓名");
        name.add("性别");
        name.add("年龄");
        name.add("国际");

        Vector data = new Vector(); //二维动态数组，用于保存数据


        //通过数据库连接获得数据
        //加载驱动

        DBconn db = new DBconn();
        db.prepareStr("SELECT * FROM  table_stu");

        ResultSet rs = db.querry();
//        System.out.println(rs.);

        try {
            while (rs.next()) {
//                System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getInt(4)+rs.getString(5));
                Vector hang = new Vector(); //一维动态数组，用于保存一行的数据
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getInt(4));
                hang.add(rs.getString(5));
                data.add(hang); //把这一行动态数组添加到二维动态数组中
            }
        }catch (SQLException ne)
        {
            ne.getStackTrace();
//            JOptionPane.showMessageDialog(this,"数据库错误");
        }





        JTable jt = new JTable(data,name);

        //通过表格对象创建一个支持鼠标滚轮的面板容器
        JScrollPane jsp = new JScrollPane(jt);
        jsp.setBounds(20,80,660,380);

        //添加表格面板之前先判断之前是否已存在该面板
        //如果有则移除
        if(haveJsp==1)
        {
            this.remove(jsp);
        }
        this.add(jsp);
        haveJsp = 1;

        //对表格对象添加监听器
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(jt.getValueAt(jt.getSelectedRow(),0));
                new DetailWindow(jt.getValueAt(jt.getSelectedRow(),0).toString());
            }
        });


        this.setVisible(true);
    }
}



