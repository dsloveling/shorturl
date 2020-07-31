package org.dsloveling.redis.custom.constant;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-05-10 21:54
 */
public enum RedisModel {
    /**
     * 单机
     */
    SINGLE("single"),
    /**
     * 主从哨兵
     */
    SENTINEL("sentinel"),
    /**
     * 集群
     */
    CLUSTER("cluster");


    public String getModel() {
        return model;
    }

    private final String model;

    RedisModel(final String model) {
        this.model = model;
    }
}
