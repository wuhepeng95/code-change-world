package server;

import javax.xml.ws.Endpoint;

/**
 * Created by whp on 2018/10/23
 */
public class WebServicePublish {
    public static void main(String[] args) {
        String address = "http://localhost:8089/WS_Server/MyWebService";
        Endpoint.publish(address, new MyWebServiceImpl() {
            @Override
            public String sayHello(String arg0) {
                return null;
            }
        });
        System.err.println("发布成功～");
        System.err.println("地址：http://localhost:8089/WS_Server/MyWebService?wsdl");
    }
}
