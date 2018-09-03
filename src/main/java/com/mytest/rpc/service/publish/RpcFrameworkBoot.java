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
package com.mytest.rpc.service.publish;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mytest.rpc.RpcFramework;
import com.mytest.rpc.service.HelloService;
import com.mytest.rpc.service.impl.HelloServiceImpl;

/**
 * @author liqingyu
 * @since 2018/09/03
 */
public class RpcFrameworkBoot {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcFrameworkBoot.class);

    public static void main(String[] args) throws IOException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("publish helloService on port:8888");
        }

        HelloService helloService = new HelloServiceImpl();
        RpcFramework.publish(helloService, 8888);
    }
}
