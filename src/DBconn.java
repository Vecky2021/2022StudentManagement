import java.sql.*;

public class DBconn {
    Connection conn;
    PreparedStatement ps;

    //数据库工具类
    DBconn()
    { //构造方法，加载驱动，建立连接
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","12345678");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //封装查询语句的方法
    void prepareStr(String sql)
    {
        try {
            ps = conn.prepareStatement(sql);
//            System.out.println(ps);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    //设置查询语句中的问号参数
    void setPara(int index, String value)
    {
        try {
            ps.setString(index,value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   void setPara(int index, int value)
   {
       try {
           ps.setInt(index,value);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   //查询数据库，获取数据结果
    ResultSet querry()
    {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //执行数据库更新，获取受影响的行数
    int update()
    {
        try {
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
