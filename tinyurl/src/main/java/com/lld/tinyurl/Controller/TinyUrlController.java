package com.lld.tinyurl.Controller;

import com.lld.tinyurl.Model.UrlRequest;
import com.lld.tinyurl.Model.UrlResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lld.tinyurl.Service.TinyUrlService;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TinyUrlController {

    @Autowired
    private final TinyUrlService tinyUrlService;

    public TinyUrlController(TinyUrlService tinyUrlService){
        this.tinyUrlService = tinyUrlService;
    }

    @PostMapping("/getShortUrl")
    public ResponseEntity<String> getShortUrl(@RequestBody UrlRequest urlRequest){
        LocalDateTime expiration = urlRequest.getExpiresAt() !=null
                ? urlRequest.getExpiresAt()
                : LocalDateTime.now().plusDays(7);
        String shortUrl = tinyUrlService.createShortUrl(urlRequest.getLongUrl() , expiration);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){
        Optional<String> originalUrl = tinyUrlService.getOriginalUrl(shortCode);

        return originalUrl
                .map(url -> ResponseEntity.status(302).location(URI.create(url)).build())
                .orElse(ResponseEntity.notFound().build());
    }




}
