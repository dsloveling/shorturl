package com.dsloveling.shorturl.convert.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.crypto.SecureUtil;
import com.dsloveling.shorturl.convert.dao.ShortUrlStorage;
import com.dsloveling.shorturl.convert.entity.ShortUrlRelation;
import com.dsloveling.shorturl.convert.util.ConversionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-03 15:04
 */
@Service
@Slf4j
public class ShortUrlService {
    @Autowired
    private Snowflake snowflake;

    @Autowired
    private ShortUrlStorage shortUrlStorage;

    @Value("${domain}")
    private String domain;

    /**
     * 转换为62进制串
     * @param sourceUrl
     * @return
     */
    private ShortUrlRelation shortUrl(String sourceUrl) {
        String key = SecureUtil.sha1(sourceUrl);
        ShortUrlRelation targetFromStorage = shortUrlStorage.getTargetUrlByMd5Str(key);
        if(targetFromStorage != null) {
            return targetFromStorage;
        }
        long sequenceId = snowflake.nextId();
        String shortUrl = ConversionUtils.convert(sequenceId);
        ShortUrlRelation shortUrlRelation = ShortUrlRelation.builder().key(key)
                .sourceUrl(sourceUrl).shortUrl(shortUrl).updatetime(new Date()).build();
        shortUrlStorage.updateTargetUrlByMd5Str(key,shortUrlRelation);
        shortUrlStorage.insertShortUrl(shortUrlRelation);
        return shortUrlRelation;
    }

    /**
     * 获取完整的短链地址
     * @param sourceUrl
     * @return
     */
    public String getTargetFullShortUrl(String sourceUrl) {
        return domain + shortUrl(sourceUrl).getShortUrl();
    }

    /**
     * 根据短链获取映射关系
     * @param shortUrl
     * @return
     */
    public ShortUrlRelation getRelationByShortUrl(String shortUrl) {
        return shortUrlStorage.getRelation(shortUrl);
    }
}
