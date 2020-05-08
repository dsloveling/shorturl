package com.dsloveling.shorturl.convert.controller;

import com.dsloveling.shorturl.convert.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-03 14:52
 */
@RequestMapping("/api")
@RestController
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/generate")
    public String getTargetShortUrl(@RequestParam String sourceUrl) {
        return shortUrlService.getTargetFullShortUrl(sourceUrl);
    }

}
