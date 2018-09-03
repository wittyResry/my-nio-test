package com.mytest.rpc;

import java.io.IOException;

import org.junit.Test;

import com.mytest.rpc.service.HelloService;
import com.mytest.rpc.service.publish.RpcFrameworkBoot;

/**
 * @author liqingyu
 * @since 2018/09/03
 */
public class RpcFrameworkTest {


    @Test
    public void call() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] args = new String[]{};
                    new RpcFrameworkBoot().main(args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            //等待服务器发布服务
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HelloService service = RpcFramework.call(HelloService.class, "127.0.0.1", 8888);
        String result = service.hello("liqingyu");
        System.out.println(result);
    }
}