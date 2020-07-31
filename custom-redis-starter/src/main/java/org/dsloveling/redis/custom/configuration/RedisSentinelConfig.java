package org.dsloveling.redis.custom.configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import java.util.Set;

/**
 * 哨兵模式
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:33
 */
@ConfigurationProperties(prefix = "redis.sentinel")
public class RedisSentinelConfig {
    /**
     * 主节点名称
     */
    private String master;

    /**
     * 连接密码
     */
    private String password;

    /**
     * 哨兵地址
     */
    private Set<String> nodes;

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public void setNodes(Set<String> nodes) {
        this.nodes = nodes;
    }

}
