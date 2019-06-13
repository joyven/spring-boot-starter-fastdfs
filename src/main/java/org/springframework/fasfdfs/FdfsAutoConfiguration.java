package org.springframework.fasfdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.TrackerClient;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.fasfdfs.config.FdfsClientConfig;
import org.springframework.fasfdfs.config.FdfsConfig2Properties;
import org.springframework.fasfdfs.conn.ConnectionPoolConfig;
import org.springframework.fasfdfs.conn.FdfsConnectionPool;
import org.springframework.fasfdfs.server.FastDFSClient;

/**
 * FastDFSAutoConfiguration
 *
 * @author zhoujunwen
 * @date 2019-06-12
 * @time 16:53
 * @desc
 */
@Configuration
@EnableConfigurationProperties(FdfsProperties.class)
@ConditionalOnClass(TrackerClient.class)
@Slf4j
public class FdfsAutoConfiguration {

    private final FdfsProperties properties;

    public FdfsAutoConfiguration(FdfsProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConfigurationProperties(prefix = "fastdfs.pool")
    public ConnectionPoolConfig connectionPoolConfig() {
        return new ConnectionPoolConfig();
    }

    @Bean
    public FdfsClientConfig fdfsClientConfig() {
        FdfsClientConfig clientConfig = new FdfsClientConfig();
        log.info("Fastdfs properties: {}", properties.toString());
        BeanUtils.copyProperties(properties, clientConfig);
        if (CollectionUtils.isNotEmpty(properties.getTrackerServerList())) {
            String servers = StringUtils.join(properties.getTrackerServerList(), ",");
            clientConfig.setTrackerServers(servers);
        } else {
            clientConfig.setTrackerServers("127.0.0.1:22122");
        }
        log.info("Fastdfs client config: {}", clientConfig.toString());
        return clientConfig;
    }

    @Bean
    public FdfsConfig2Properties fdfsConfig2Properties() {
        return new FdfsConfig2Properties(fdfsClientConfig());
    }

    @Bean
    public FdfsConnectionPool fdfsConnectionPool() {
        return new FdfsConnectionPool(fdfsConfig2Properties());
    }

    @Bean
    public FastDFSClient fastDFSClient(FdfsConnectionPool pool) {
        return new FastDFSClient(pool);
    }
}
