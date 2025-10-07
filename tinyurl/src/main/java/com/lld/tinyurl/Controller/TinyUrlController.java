package com.lld.tinyurl.Controller;

import com.lld.tinyurl.Model.UrlRequest;
import com.lld.tinyurl.Model.UrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lld.tinyurl.Service.TinyUrlService;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class TinyUrlController {

    @Autowired
    private TinyUrlService tinyUrlService;

    @PostMapping("/getShortUrl")
    public UrlResponse getShortUrl(@RequestBody UrlRequest urlRequest){
        String shortUrl = tinyUrlService.createShortUrl(urlRequest.getLongUrl());
        return new UrlResponse(shortUrl);
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode){
        System.out.println("Hit the get");
        String originalUrl = tinyUrlService.getOriginalUrl(shortCode);
        if(originalUrl != null){
            return new RedirectView(originalUrl);
        }
        return new RedirectView("/not-found");
    }


}
