package org.dsloveling.shorturl.convert.dao;

import org.dsloveling.shorturl.convert.entity.ShortUrlRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-28 19:02
 */
@CacheConfig(cacheNames = "shortUrl")
@Component
public class ShortUrlStorage {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Cacheable(key = "#root.caches[0].name + ':relation:' + #md5Str", unless = "#result == null")
    public ShortUrlRelation getTargetUrlByMd5Str(String md5Str) {
        return shortUrlRepository.findByKey(md5Str);
    }

    @CachePut(key = "#root.caches[0].name + ':relation:' + #md5Str")
    public ShortUrlRelation updateTargetUrlByMd5Str(String md5Str, ShortUrlRelation shortUrlRelation) {
        return shortUrlRelation;
    }

    @CacheEvict(key = "#root.caches[0].name + ':relation:' + #md5Str")
    public void deleteTargetUrlByMd5Str(String md5Str) {

    }

    @Cacheable(key = "#root.caches[0].name + ':short:' + #shortUrl", unless = "#result == null")
    public ShortUrlRelation getRelation(String shortUrl) {
        return shortUrlRepository.findByShortUrl(shortUrl);
    }

    @CachePut(key = "#root.caches[0].name + ':short:' + #shortUrl")
    public ShortUrlRelation updateRelation(String shortUrl, ShortUrlRelation shortUrlRelation) {
        return shortUrlRelation;
    }
    @CacheEvict(key = "#root.caches[0].name + ':short:' + #shortUrl")
    public void deleteRelation(String shortUrl) {

    }

    public void insertShortUrl(ShortUrlRelation shortUrlRelation) {
        shortUrlRepository.save(shortUrlRelation);
    }
}
