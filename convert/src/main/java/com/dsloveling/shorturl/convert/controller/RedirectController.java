package com.dsloveling.shorturl.convert.controller;

import com.dsloveling.shorturl.convert.entity.ShortUrlRelation;
import com.dsloveling.shorturl.convert.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-29 13:49
 */
@RequestMapping("/short")
@Controller
public class RedirectController {
    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/{sourceUrl}")
    public void redirect(@PathVariable String sourceUrl, HttpServletResponse response) throws IOException {
        ShortUrlRelation shortUrlRelation = shortUrlService.getRelationByShortUrl(sourceUrl);
        if (shortUrlRelation != null) {
            response.sendRedirect(shortUrlRelation.getSourceUrl());
        }
    }
}
