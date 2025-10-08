package com.lld.tinyurl.Service;
import java.time.LocalDateTime;
import java.util.*;

import com.lld.tinyurl.Entity.UrlMapping;
import com.lld.tinyurl.Repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlService {
    private final UrlMappingRepository repository;

    public TinyUrlService(UrlMappingRepository repository){
        this.repository = repository;
    }
    private final String BASE_URL = "http://localhost:8080/";

    public String createShortUrl(String originalUrl, LocalDateTime expiresAt){
        String shortCode = generateCode();
        UrlMapping urlMapping = UrlMapping.builder()
                        .shortCode(shortCode)
                                .originalUrl(originalUrl)
                                        .createdAt(LocalDateTime.now())
                                                .expiresAt(expiresAt)
                                                        .build();
                repository.save(urlMapping);
        return BASE_URL + shortCode;
    }

    public Optional<String> getOriginalUrl(String shortCode){

        Optional<UrlMapping> mapping = repository.findByShortCode(shortCode);
        if(mapping.isEmpty()) return Optional.empty();

        UrlMapping url = mapping.get();
        if(url.getExpiresAt()!=null && url.getExpiresAt().isBefore(LocalDateTime.now())){
            repository.delete(url);
            return Optional.empty();
        }
        return Optional.of(url.getOriginalUrl());
    }

    private String generateCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return code.toString();
    }
}
