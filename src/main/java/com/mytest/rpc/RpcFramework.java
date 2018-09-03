/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mytest.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liqingyu
 * @since 2018/09/03
 */
public class RpcFramework {
    public static void publish(final Object service, int port) throws IOException {
        if (service == null) {
            throw new IllegalArgumentException("发布服务不能为空");
        }
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("端口不合法,port=" + port);
        }
        ServerSocket server = new ServerSocket(port);
        while (true) {
            final Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        String methodName = input.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                        Object[] arguments = (Object[]) input.readObject();
                        ObjectOutputStream output = new ObjectOutputStream(
                            socket.getOutputStream());
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, arguments);
                        output.writeObject(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public static <T> T call(final Class<T> interfaceClass, String host, int port) {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("调用服务不能为空");
        }
        if (host == null) {
            throw new IllegalArgumentException("主机为空");
        }
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("端口不合法,port=" + port);
        }
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
            new Class<?>[] { interfaceClass }, new CallHandler(host, port));
    }

    static class CallHandler implements InvocationHandler {
        private String host;
        private int    port;

        public CallHandler(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            Socket socket = new Socket(host, port);
            try {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                try {
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(arguments);
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    try {
                        Object result = input.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }
                        return result;
                    } finally {
                        input.close();
                    }
                } finally {
                    output.close();
                }
            } finally {
                socket.close();
            }
        }
    }
}
