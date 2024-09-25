//第一步，导包
import java.sql.*;

public class Main {

    public static void main(String[] args)
    {

        new MainWindow();
//        connDB();
    }

    //通过java连接MySQL数据库
    public static void connDB()
    {
       try
       {
           //2. 加载驱动
           Class.forName("com.mysql.cj.jdbc.Driver");

           //3. 建立连接
           //getConnection()三个参数：连接地址、数据库用户名、登录密码
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","12345678");

           //4. 封装查询语句
           String SQL = "select * from table_stu where stunation = ? ";
           PreparedStatement pst = conn.prepareStatement(SQL);

           //设置查询语句对象中的？参数
           pst.setString(1,"阿根廷");

           //5. 执行查询语句
           //得到一个记录集对象ResultSet
           ResultSet rs = pst.executeQuery();

           //读取rs的内容
           while(rs.next())
           {
               System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getInt(4)+rs.getString(5));
           }


       }catch (ClassNotFoundException | SQLException e)
       {
           System.out.println(e.toString());
       }
    }
}
