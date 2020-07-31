package org.dsloveling.redis.custom.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;



/**
 * 集群模式
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:33
 */
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisClusterConfig {
    /**
     * redis密码，非必填
     */
    private String password;

    /**
     * 集群模式下的节点地址
     */
    private List<String> nodes;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

}
