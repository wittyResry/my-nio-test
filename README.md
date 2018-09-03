### 发布RPC服务
```bash
$ mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

### 本地发布服务
$ java -Djava.ext.dirs=target com.mytest.rpc.service.publish.RpcFrameworkBoot main
或者
$ java -cp target/my-nio-test-1.0-SNAPSHOT.jar com.mytest.rpc.service.publish.RpcFrameworkBoot
或者
$ java -classpath target/my-nio-test-1.0-SNAPSHOT.jar com.mytest.rpc.service.publish.RpcFrameworkBoot
增加了MENIFEST.MF后，可以直接用
$ java -jar target/my-nio-test-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### 排查过程通过下面命令看jar中的内容
```
$ jar tf target/my-nio-test-1.0-SNAPSHOT.jar
```


