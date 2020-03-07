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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author liqingyu
 * @since 2020/03/07
 */
public class KyroSerializationUtils {
    public static byte[] writeObject(Object obj, Class type) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Kryo kryo = new Kryo();
        kryo.register(type);
        Output output = new Output(bos);
        kryo.writeObject(output, obj);
        output.close();
        return bos.toByteArray();
    }
    public static Object readObject(byte[] bytes, Class type) {
        Kryo kryo = new Kryo();
        kryo.register(type);
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        Input input = new Input(bin);
        Object object = kryo.readObject(input, type);
        input.close();
        return object;

    }
}
