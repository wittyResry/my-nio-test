package com.mytest.rpc;

import java.io.IOException;

import org.junit.Test;

/**
 * @author liqingyu
 * @since 2018/09/03
 */
public class RpcFrameworkTest {

    public void publish() throws IOException {
        HelloService helloService = new HelloServiceImpl();
        RpcFramework.publish(helloService, 8888);
    }


    @Test
    public void call() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new RpcFrameworkTest().publish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = RpcFramework.call(HelloService.class, "127.0.0.1", 8888);
        String result = service.hello("liqingyu");
        System.out.println(result);
    }

    public interface HelloService {
        String hello(String name);
    }

    public class HelloServiceImpl implements HelloService {

        @Override
        public String hello(String name) {
            return "RPC test, hello " + name;
        }
    }
}