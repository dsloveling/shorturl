package org.dsloveling.redis.custom.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 单机模式
 * @author dsloveling
 * @version 1.0
 * @date2020-04-03 15:00
 */
@ConfigurationProperties(prefix = "redis.single")
public class RedisSingleConfig {

    /**
     * 主机地址
     */
    private String host;

    /**
     * 数据库编号
     */
    private Integer database;

    /**
     * 连接端口
     */
    private Integer port;

    /**
     * 连接密码
     */
    private String password;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
