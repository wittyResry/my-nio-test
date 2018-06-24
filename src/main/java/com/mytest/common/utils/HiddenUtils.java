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

import org.apache.commons.lang.StringUtils;

/**
 * @author liqingyu
 * @since 2018/06/02
 */
public class HiddenUtils {

    /**
     * 获取隐藏后的手机地址
     *
     * <li>mobile为空或者非法，则原样返回</li>
     * <li>隐藏规则：保留前面3位和后面4位</li>
     *
     * @param mobile
     * @return
     */
    public static String getHiddenMobile(String mobile) {
        //去掉头尾空白字符
        mobile = StringUtils.trim(mobile);

        //空白字符返回空字符串
        if (StringUtils.isBlank(mobile)) {
            return StringUtils.EMPTY;
        }

        if (ValidateUtils.isValidMobile(mobile)) {
            //如果是手机号码，大陆手机，海外手机，含+号的手机
            return StringUtils.overlay(mobile, "****", 3, 7);
        }
        //如果格式不是邮件地址或手机号码，返回，不进行隐藏
        return mobile;
    }

    /**
     * 获取隐藏后的邮箱地址
     *
     * <li>email为空或者非法，则原样返回</li>
     * <li>隐藏规则：隐藏@前面3位,如果@前面少于3位则取第一位+“***”</li>
     *
     * @param email
     * @return
     */
    public static String getHiddenEmail(String email) {
        email = StringUtils.trim(email);
        if (ValidateUtils.isValidEmail(email)) {
            int index = StringUtils.indexOf(email, "@");
            if (index == -1) {
                return email;
            }
            // @前面少于3位，则取第一+“***”
            StringBuilder sb = new StringBuilder();
            if (index <= 3) {
                sb.append(StringUtils.substring(email, 0, index)).append("***");
            } else {
                sb.append(StringUtils.substring(email, 0, 3)).append("***");
            }
            return sb.append(StringUtils.substring(email, index)).toString();
        }
        return email;
    }
}
