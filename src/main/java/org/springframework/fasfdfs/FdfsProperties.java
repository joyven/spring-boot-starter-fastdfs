package org.springframework.fasfdfs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.codec.CharEncoding.UTF_8;
import static org.csource.fastdfs.ClientGlobal.DEFAULT_CONNECT_TIMEOUT;
import static org.csource.fastdfs.ClientGlobal.DEFAULT_NETWORK_TIMEOUT;

/**
 * FdfsProperties
 *
 * @author zhoujunwen
 * @date 2019-06-12
 * @time 16:54
 * @desc
 */
@ConfigurationProperties(prefix = "fastdfs")
@Setter
@Getter
@ToString
public class FdfsProperties {
    /**
     * Tracker 地址列表
     */
    private List<String> trackerServerList = new ArrayList<>();

    /**
     * 编码
     */
    private String charset = UTF_8;

    /**
     * 防盗链token
     */
    private String httpAntiStealToken;

    /**
     * 密钥
     */
    private String httpSecretKey;

    /**
     * TrackerServer port
     */
    private Integer httpTrackerHttpPort = 80;

    /**
     * 连接超时时间
     */
    private Integer connectTimeoutInSeconds = DEFAULT_CONNECT_TIMEOUT;

    /**
     * 网络超时时间
     */
    private Integer networkTimeoutInSeconds = DEFAULT_NETWORK_TIMEOUT;


}
