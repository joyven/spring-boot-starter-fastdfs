package org.springframework.fasfdfs.conn;

import org.csource.fastdfs.TrackerServer;

/**
 * FdfsConnection
 *
 * @author zhoujunwen
 * @date 2019-06-13
 * @time 14:24
 * @desc
 */
public interface FdfsConnection {
    /**
     * 从连接池里获取一个TrackerServer对象
     *
     * @return
     */
    TrackerServer borrowObject();

    /**
     * 将一个TrackerServer对象放回连接池
     *
     * @param trackerServer
     */
    void returnObject(TrackerServer trackerServer);
}
