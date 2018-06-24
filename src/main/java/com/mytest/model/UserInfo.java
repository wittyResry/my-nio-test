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
package com.mytest.model;

import java.io.Serializable;

import com.mytest.common.annotation.SensitiveField;
import com.mytest.common.builder.ToSensitiveStringBuilder;
import com.mytest.common.enums.SensitiveTypeEnum;

/**
 * @author liqingyu
 * @since 2018/06/01
 */
public class UserInfo implements Serializable {

    /** <code>serialVersionUID</code> */
    private static final long serialVersionUID = -6633929996858184680L;

    /** 用户ID */
    private String            userId;

    /** 邮箱 */
    @SensitiveField(hiddenType = SensitiveTypeEnum.EMAIL)
    private String            email;

    /** 手机号 */
    @SensitiveField(hiddenType = SensitiveTypeEnum.MOBILE)
    private String            mobile;

    /**
     * Getter method for property <tt>userId</tt>.
     *
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     *
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>email</tt>.
     *
     * @return property value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for property <tt>email</tt>.
     *
     * @param email value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for property <tt>mobile</tt>.
     *
     * @return property value of mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Setter method for property <tt>mobile</tt>.
     *
     * @param mobile value to be assigned to property mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToSensitiveStringBuilder.reflectionToString(this);
    }
}
