package org.dsloveling.shorturl.convert.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.dsloveling.shorturl.convert.entity.VisitRank;
import org.dsloveling.shorturl.convert.service.ShortUrlService;
import org.dsloveling.shorturl.convert.service.ShortUrlVistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-03 14:52
 */
@RequestMapping("/api")
@RestController
public class ShortUrlController {

    private static final String REFUSE = "服务暂时不可用，请3s后再试";

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private ShortUrlVistsService shortUrlVistsService;

    @Autowired
    private RateLimiter rateLimiter;

    @GetMapping("/generate")
    public String getTargetShortUrl(@RequestParam String source) {
        if(rateLimiter.tryAcquire()) {
            return shortUrlService.getTargetFullShortUrl(source);
        }
        return REFUSE;
    }

    @GetMapping("/rank")
    public List<VisitRank> getRankList() {
        return shortUrlVistsService.getVisitRankList();
    }

}
