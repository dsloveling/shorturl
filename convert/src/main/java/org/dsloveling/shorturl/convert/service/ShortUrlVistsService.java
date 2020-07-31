package org.dsloveling.shorturl.convert.service;

import lombok.extern.slf4j.Slf4j;
import org.dsloveling.shorturl.convert.dao.ShortUrlStorage;
import org.dsloveling.shorturl.convert.entity.ShortUrlRelation;
import org.dsloveling.shorturl.convert.entity.VisitRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author SwordZ
 * @version 1.0
 * @date 2020/6/16
 */
@Slf4j
@Service
public class ShortUrlVistsService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ShortUrlStorage shortUrlStorage;

    public void updateRank(ShortUrlRelation shortUrlRelation) {
        redisTemplate.opsForZSet().incrementScore("rank-list", shortUrlRelation.getKey(), 1.0);
    }

    public List<VisitRank> getVisitRankList() {
        Set<ZSetOperations.TypedTuple<Object>> res = redisTemplate.opsForZSet().reverseRangeWithScores("rank-list",0, - 1);
        List<VisitRank> rankList = new ArrayList<>();
        long start = 1;
        for (ZSetOperations.TypedTuple<Object> element : res) {
            rankList.add(VisitRank.builder().rank(start++)
                    .address(shortUrlStorage.getTargetUrlByMd5Str(element.getValue().toString()).getSourceUrl())
                    .score(element.getScore())
                    .build());
        }
        return rankList;
    }
}
