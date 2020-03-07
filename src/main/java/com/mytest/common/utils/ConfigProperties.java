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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @author liqingyu
 * @since 2020/03/06
 */
public class ConfigProperties {

    private String     properiesName = "";
    private Properties properties    = new Properties();

    public ConfigProperties() {
    }

    public ConfigProperties(String fileName) {
        this.properiesName = fileName;
    }

    protected void loadFromFile() {
        InputStream in = null;
        InputStreamReader ireader = null;
        try {
            String filePath = getResourcePath(properiesName);
            in = new FileInputStream(filePath);
            //解决读非UTF-8编码的配置文件时，出现的中文乱码问题
            ireader = new InputStreamReader(in, "utf-8");
            properties.load(ireader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ireader != null) {
                try {
                    ireader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 取得当前类路径下的 resName资源的完整路径
     * url.getPath()获取到的路径被utf-8编码了
     * 需要用URLDecoder.decode(path, "UTF-8")解码
     *
     * @param resName 需要获取完整路径的资源,需要以/打头
     * @return 完整路径
     */
    private static String getResourcePath(String resName) throws UnsupportedEncodingException {
        URL url = Object.class.getResource(resName);
        String path = url.getPath();
        String decodePath = null;
        decodePath = URLDecoder.decode(path, "UTF-8");
        return decodePath;
    }

    /**
     * 按key获取值
     *
     * @param key
     * @return
     */
    public String readProperty(String key) {
        return properties.getProperty(key);
    }

    public String getValue(String key) {
        return readProperty(key);
    }

    public int getIntValue(String key) {
        return Integer.parseInt(readProperty(key));
    }

}
