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
package com.mytest.common.builder;

import java.lang.reflect.Field;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mytest.common.annotation.SensitiveField;
import com.mytest.common.utils.HiddenUtils;

/**
 * @author liqingyu
 * @since 2018/06/01
 */
public class ToSensitiveStringBuilder extends ReflectionToStringBuilder {

    /**
     * @see org.apache.commons.lang.builder.ReflectionToStringBuilder#getValue(java.lang.reflect.Field)
     */
    @Override
    protected Object getValue(Field field) throws IllegalArgumentException, IllegalAccessException {
        Object object = super.getValue(field);
        SensitiveField annotation = field.getAnnotation(SensitiveField.class);
        if (object instanceof String && annotation != null) {
            switch (annotation.hiddenType()) {
                case EMAIL:
                    return HiddenUtils.getHiddenEmail((String) object);
                case MOBILE:
                    return HiddenUtils.getHiddenMobile((String) object);
                default:
                    return object;
            }
        }
        return object;
    }

    /**
     * 与<code>com.mytest.common.annotation.SensitiveField</code>一起使用
     *
     * @param object the Object to be output
     * @return the String result
     * @throws IllegalArgumentException
     *             if the Object is <code>null</code>
     */
    public static String reflectionToString(Object object) {
        return myToString(object, ToStringStyle.SHORT_PREFIX_STYLE, false, false, null);
    }

    /**
     * @param object
     *            the Object to be output
     * @param style
     *            the style of the <code>toString</code> to create, may be <code>null</code>
     * @param outputTransients
     *            whether to include transient fields
     * @param outputStatics
     *            whether to include transient fields
     * @return the String result
     * @param reflectUpToClass
     *            the superclass to reflect up to (inclusive), may be <code>null</code>
     * @return the String result
     * @throws IllegalArgumentException
     *             if the Object is <code>null</code>
     */
    private static String myToString(Object object, ToStringStyle style, boolean outputTransients,
                                     boolean outputStatics, Class reflectUpToClass) {
        return new ToSensitiveStringBuilder(object, style, null, reflectUpToClass, outputTransients,
            outputStatics).toString();
    }

    /**
     * Constructor.
     *
     * @param object
     *            the Object to build a <code>toString</code> for
     * @param style
     *            the style of the <code>toString</code> to create, may be <code>null</code>
     * @param buffer
     *            the <code>StringBuffer</code> to populate, may be <code>null</code>
     * @param reflectUpToClass
     *            the superclass to reflect up to (inclusive), may be <code>null</code>
     * @param outputTransients
     *            whether to include transient fields
     * @param outputStatics
     *            whether to include static fields
     */
    public ToSensitiveStringBuilder(Object object, ToStringStyle style, StringBuffer buffer,
                                    Class reflectUpToClass, boolean outputTransients,
                                    boolean outputStatics) {
        super(object, style, buffer, reflectUpToClass, outputTransients, outputStatics);
    }
}
