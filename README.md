# spring-boot-starter-fastdfs

## 使用(Usage)

### maven引入
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-fastdfs</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

### 配置文件

``` properties
## fastdfs为前缀的是FastDFS的配置
fastdfs.connect-timeout-in-seconds=10
fastdfs.network-timeout-in-seconds=30
fastdfs.charset=UTF-8
# token 防盗链功能
fastdfs.http-anti-steal-token=false
# 密钥
fastdfs.http-secret-key=FastDFS1234567890
# TrackerServer port
fastdfs.http-tracker-http-port=80

# tracker服务列表
fastdfs.tracker-server-list[0]=10.22.10.22:22122
#################### FastDFS-Client End ####################
```

### 使用

```java
@Autowired
private FastDFSClient fastDFSClient;
```

## 解决问题(Solve some problems)

