package com.cn.io.server.tomcat;

import com.cn.io.Constants;
import com.cn.io.server.tomcat.model.MyRequest;
import com.cn.io.server.tomcat.model.MyResponse;
import com.cn.io.server.tomcat.model.MyServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TomcatServer {

    /*
    1.  配置启动端口和IP ServerSocket
    2.  读取配置，获取自己的Servlet继承自HttpServlet
    3.  读取配置，获取Url和Servlet的Mapping关系
    4.  获取http请求，字符串，http协议
    5.  从协议中拿到url把对应的servlet进行实例化或者反射
    6.  调用对象的service方法执行具体的doGet或者doPost方法
    7.  通过Request/Response返回
     */

    private int port;
    private ServerSocket serverSocket;
    private Map<String, MyServlet> urlMapping;
    private final String CONFIG_FILE = "web.properties";
    private ExecutorService executorService;

    public TomcatServer(int port) throws Exception {
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
        this.urlMapping = initUrlMapping();
        executorService = Executors.newFixedThreadPool(10);
        System.out.println("system is listening port: " + this.port);
    }

    private Map<String, MyServlet> initUrlMapping() throws Exception {
        urlMapping = new HashMap<>();
        Properties properties = new Properties();
        String webInfo = this.getClass().getResource("/").getPath();
        properties.load(new FileInputStream(webInfo + "/" + CONFIG_FILE));
        for (Object k : properties.keySet()) {
            String key = k.toString();
            if (key.endsWith(".url")) {
                String url = properties.get(key).toString();

                key = key.replaceAll("\\.url$", "");
                String className = properties.getProperty(key + ".className");
                MyServlet httpServlet = (MyServlet)Class.forName(className).newInstance();
                urlMapping.put(url, httpServlet);
            }

        }
        return urlMapping;
    }

    public void listen() {
        try {
            while (true){
                Socket socket= serverSocket.accept();
                executorService.submit(new WorkThread(socket));
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }


    public static void main(String[] args) throws Exception{
        new TomcatServer(Constants.PORT).listen();
    }


    private class WorkThread implements Runnable{

        private Socket socket;

        public WorkThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try{
                InputStream in = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                MyRequest myRequest = new MyRequest(in);
                MyResponse myResponse = new MyResponse(outputStream);

                urlMapping.get(myRequest.getUrl()).doService(myRequest, myResponse);
                in.close();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
