package io.github.xingchuan.query.provider.processor.query.connection;

import io.github.xingchuan.query.api.domain.error.CommonError;
import io.github.xingchuan.query.api.processor.query.SqlConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 连接池的管理类
 *
 * @author xingchuan.qxc
 * @since 1.0
 */
public class SqlConnectionPoolManager {

    private Logger logger = LoggerFactory.getLogger(SqlConnectionPoolManager.class);

    private Map<String, SqlConnectionPool> connectionPoolMap = new HashMap<>();

    /**
     * 注册连接池
     *
     * @param pool
     */
    public void registerSqlConnectionPool(SqlConnectionPool pool) {
        if (pool == null) {
            return;
        }
        String poolType = pool.poolType();
        if (connectionPoolMap.containsKey(poolType)) {
            logger.error("pool type {} exist. ", poolType);
            throw CommonError.NOT_SUPPORT_OPERATION().withParam("operation", "注册已存在的连接池类型").newException();
        }
        connectionPoolMap.put(poolType, pool);
    }

    /**
     * 通过类型获得一个连接池
     *
     * @param poolType
     * @return
     */
    public SqlConnectionPool fetchSqlConnectionPool(String poolType) {
        return connectionPoolMap.get(poolType);
    }

}
