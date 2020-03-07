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
package com.mytest.iodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.mytest.common.utils.LogUtil;

/**
 * @author liqingyu
 * @since 2020/03/06
 */
public class NioDiscardServer {
    public static void startServer() throws IOException {
        //1.selector open: ��ȡSelectorѡ����
        Selector selector = Selector.open();
        //2. ��ȡͨ��
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.1. ����ͨ��Ϊ������
        serverSocketChannel.configureBlocking(false);
        //2.2. ���ö˿ںţ�������
        serverSocketChannel.bind(new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_PORT));
        LogUtil.digest("server start");

        //3. ��selectorע�ᵽchannel�ϣ�����OP_ACCEPT����ʾ�½�������
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //4. ��ѯ
        while (selector.select() > 0) {
            //5. ��ȡѡ��������
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                //6. ��ȡ����ѡ���������
                SelectionKey selectionKey = selectedKeys.next();
                if (selectionKey.isAcceptable()) {
                    //7. OP_ACCEPT״̬����
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //7.1�л�Ϊ������
                    socketChannel.configureBlocking(false);
                    //12. ע�ᵽselector
                    socketChannel.register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()) {
                    //8. OP_READ״̬����
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        LogUtil.digest("read:" + new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();
                    }
                    //socketChannel.close();
                }
                //�������ɾ��
                selectedKeys.remove();
            }

        }

        //�ر�����
        serverSocketChannel.close();

    }

    public static void main(String[] args) throws IOException {
        LogUtil.digest("asdf��");
        System.out.println(NioDemoConfig.SOCKET_SERVER_PORT);
        startServer();
    }
}
