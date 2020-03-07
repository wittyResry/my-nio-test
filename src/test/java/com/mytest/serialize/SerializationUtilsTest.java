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
package com.mytest.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mytest.common.utils.KyroSerializationUtils;

/**
 * @author liqingyu
 * @since 2018/12/27
 */
public class SerializationUtilsTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(SerializationUtilsTest.class);

    @Test
    public void test() throws FileNotFoundException {
        System.out.println("asdf");
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);

        SomeClass object = new SomeClass();
        object.value = "Hello Kryo!";

        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, object);
        output.close();

        Input input = new Input(new FileInputStream("file.bin"));
        SomeClass object2 = kryo.readObject(input, SomeClass.class);
        input.close();
    }

    @Test
    public void test2() {
        SomeClass object = new SomeClass();
        object.value = "Hello Kryo!";
        byte[] bytes = KyroSerializationUtils.writeObject(object, SomeClass.class);
        System.out.println(new String(bytes));
        Object object2 = KyroSerializationUtils.readObject(bytes, SomeClass.class);
        if (object2 instanceof SomeClass) {
            SomeClass someClass2 = (SomeClass) object2;
            LOGGER.warn("Y" + someClass2);
        } else {
            LOGGER.warn("N");
        }
    }

    static public class SomeClass {
        String value;
    }
}
