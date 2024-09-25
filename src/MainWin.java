import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWin extends JFrame implements ActionListener {
    //声明类的属性（图形界面上的组件）
    JButton btn_show,btn_add,btn_search,btn_sort;
    JTextField jtf_search;

    MainWin()
    {
        //构造方法，用于初始化界面
        this.setBounds(500,200,700,500);
        this.setLayout(null);  //把布局方式设为空
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

        this.add(btn_show);
        this.add(btn_add);
        this.add(btn_search);
        this.add(jtf_search);

        //实现按钮监听
        btn_show.addActionListener(this);
        btn_search.addActionListener(this);
        btn_sort.addActionListener(this);

        //窗口设为可见
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("显示所有"))
        {
            showAll();
        }

    }

    public void showAll()
    {

    }
}
