package org.dsloveling.shorturl.convert.controller;

import org.dsloveling.shorturl.convert.entity.ShortUrlRelation;
import org.dsloveling.shorturl.convert.service.ShortUrlService;
import org.dsloveling.shorturl.convert.service.ShortUrlVistsService;
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

    @Autowired
    private ShortUrlVistsService shortUrlVistsService;

    @GetMapping("/{source}")
    public void redirect(@PathVariable String source, HttpServletResponse response) throws IOException {
        ShortUrlRelation shortUrlRelation = shortUrlService.getRelationByShortUrl(source);
        if (shortUrlRelation != null) {
            shortUrlVistsService.updateRank(shortUrlRelation);
            response.sendRedirect(shortUrlRelation.getSourceUrl());
        }
    }
}
