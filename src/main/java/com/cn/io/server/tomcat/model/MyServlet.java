package com.cn.io.server.tomcat.model;

import java.io.IOException;

public class MyServlet {

    public void doService(MyRequest myRequest, MyResponse myResponse) throws IOException {
        if (myRequest.getMethod().equals("GET")) {
            doGet(myRequest, myResponse);
        } else {
            doPost(myRequest, myResponse);
        }
    }

    public void doGet(MyRequest myRequest, MyResponse myResponse) throws IOException {
        myResponse.write("this is Get method!".getBytes());
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) throws IOException {
        myResponse.write("this is Post method!".getBytes());
    }

}
