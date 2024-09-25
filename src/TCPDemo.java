import java.io.*;
import java.net.*;

public class TCPDemo {
    public static void main(String[] args)
    {
        //多线程执行服务器方法
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                tcpServer();
            }
        }.start();


        tcpClient();
    }


    public static void tcpServer()  //tcp服务端
    {

        try {
            //创建服务器对象
            ServerSocket ss = new ServerSocket(10000);

            //等待连接
            Socket s = ss.accept();
            //打印对方的ip地址
            System.out.println(s.getInetAddress().getHostAddress());

            //服务器开始接收消息
            InputStream ins = s.getInputStream();

            //创建文件，获取文件输出流（保存文件到硬盘）
            File f = new File("aa.jpeg");
            OutputStream ous = new FileOutputStream(f);

            //解析消息
            byte[] buf = new byte[1024];
            int len = 0; //整型获取每次读取的数据长度

            while((len = ins.read(buf,0,buf.length))!=-1)
            {
                ous.write(buf,0,len);
            }
//            while((len=ins.read(buf,0,buf.length))!=-1)
//            {
//                System.out.println(new String(buf,0,len));
//            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tcpClient() //tcp客户端
    {
        try {
            Socket s = new Socket("127.0.0.1",10000);

            OutputStream ous = s.getOutputStream();

            File f = new File("WechatIMG3413.jpeg");
            InputStream ins = new FileInputStream(f);

            byte[] buf = new byte[1024];
            int len = 0;

            while((len = ins.read(buf,0,buf.length))!=-1)
            {
                ous.write(buf,0,len);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
