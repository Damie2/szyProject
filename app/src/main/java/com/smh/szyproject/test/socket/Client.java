package com.smh.szyproject.test.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * author : smh
 * date   : 2020/6/16 11:13
 * desc   : 客户端主要功能是把字符串传给服务器
 * https://blog.csdn.net/qq_30092289/article/details/80293123
 */
public class Client {
    public Client(String str){
        this.str=str;
    }
    static String str = null;
    static Scanner scanner = null;
    public static void send(){
        try {
            Socket socket =new Socket("192.168.0.0",9998); //定义socket对象，传入ip地址，和端口，端口尽量定义在1024以上
            OutputStream os=socket.getOutputStream();     //得到outputStream对象
            PrintWriter pw=new PrintWriter(os);          //转换成字符流
            pw.write(str);                              //把字符串传给服务器
            pw.flush();
            socket.shutdownInput();                       //关闭相应的资源
            os.close();
            pw.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
