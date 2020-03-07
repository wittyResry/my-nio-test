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

import com.mytest.common.utils.ConfigProperties;

/**
 * @author liqingyu
 * @since 2020/03/06
 */
public class NioDemoConfig extends ConfigProperties {


    static ConfigProperties singleton
            = new NioDemoConfig("/system.properties");


    private NioDemoConfig(String fileName)
    {
        super(fileName);
        super.loadFromFile();
    }

    public static final String SOCKET_SEND_FILE
            = singleton.getValue("socket.send.file");
    public static final String SOCKET_RECEIVE_FILE
            = singleton.getValue("socket.receive.file");
    public static final String SOCKET_RECEIVE_PATH
            = singleton.getValue("socket.receive.path");


    public static final int SEND_BUFFER_SIZE
            = singleton.getIntValue("send.buffer.size");

    public static final int SERVER_BUFFER_SIZE
            = singleton.getIntValue("server.buffer.size");

    public static final String SOCKET_SERVER_IP
            = singleton.getValue("socket.server.ip");

    public static final int SOCKET_SERVER_PORT
            = singleton.getIntValue("socket.server.port");
}
