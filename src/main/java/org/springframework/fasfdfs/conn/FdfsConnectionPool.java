package org.springframework.fasfdfs.conn;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.fasfdfs.config.FdfsConfig2Properties;

import java.io.IOException;
import java.util.Properties;

/**
 * FdfsConnectionPool
 *
 * @author zhoujunwen
 * @date 2019-06-12
 * @time 17:34
 * @desc
 */
@Slf4j
public class FdfsConnectionPool implements FdfsConnection, InitializingBean {

    private GenericObjectPool<TrackerServer> trackerServerPool;

//    private FdfsConfig2Properties fdfsConfig2Properties;

    public FdfsConnectionPool(FdfsConfig2Properties fdfsConfig2Properties) {
//        this.fdfsConfig2Properties = fdfsConfig2Properties;

        Properties prop = fdfsConfig2Properties.initByFdfsClientConfig();
        try {
            ClientGlobal.initByProperties(prop);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    public TrackerServer borrowObject() {
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            ProtoCommon.activeTest(trackerServer.getSocket());
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return trackerServer;
    }

    public void returnObject(TrackerServer trackerServer) {
        try {
            trackerServerPool.returnObject(trackerServer);
        } catch (Exception e) {
            throw new RuntimeException("returnObject出现异常", e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        PooledConnectionFactory factory = new PooledConnectionFactory();
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        trackerServerPool = new GenericObjectPool<>(factory, config);
    }
}
