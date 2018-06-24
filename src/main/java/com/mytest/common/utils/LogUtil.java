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
package com.mytest.common.utils;

import java.util.Date;

/**
 * 日志工具类
 *
 * @author liqingyu
 * @since 2018/05/25
 */
public class LogUtil {
    /** 调用栈下标 */
    public static final int INDEX = 2;

    /**
     * 打印下标
     * 
     * @param format
     * @param args
     */
    public static void digest(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    /**
     * 打印调用日志
     */
    public static void digestLog() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        if (ste.length < 3) {
            System.out.println("skip digest method name");
            return;
        }
        System.out.println(String.format("%s.%s,processing,date:%s",
            Thread.currentThread().getStackTrace()[INDEX].getClassName(),
            Thread.currentThread().getStackTrace()[INDEX].getMethodName(),
            DateUtils.getNewFormatDateString(new Date(System.currentTimeMillis()))));
        sleepOneSecond();
    }

    /**
     * 延迟1S
     */
    private static void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }
    }
}
