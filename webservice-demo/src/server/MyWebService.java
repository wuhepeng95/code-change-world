package server;

import javax.jws.WebMethod;

/**
 * Created by whp on 2018/10/23
 */
@javax.jws.WebService
public interface MyWebService {

    @WebMethod
//@WebMethod注解声明了这个方法是要发布出去的方法
    String sayHello(String message);
}
