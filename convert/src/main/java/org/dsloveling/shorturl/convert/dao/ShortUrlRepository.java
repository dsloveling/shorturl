package com.dsloveling.shorturl.convert.dao;

import com.dsloveling.shorturl.convert.entity.ShortUrlRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-28 19:06
 */
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrlRelation, Long> {
    /**
     * 根据keyStr获取url
     *
     * @param keyStr
     * @return
     */
    ShortUrlRelation findByKey(String keyStr);

    /**
     * 根据短链获取映射关系
     * @param shortUrl
     * @return
     */
    ShortUrlRelation findByShortUrl(String shortUrl);

}
