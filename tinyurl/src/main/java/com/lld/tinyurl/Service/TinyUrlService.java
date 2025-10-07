package com.lld.tinyurl.Service;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlService {
    private final Map<String, String> urlStore = new HashMap<>();
    private final String BASE_URL = "http://localhost:8080/";

    public String createShortUrl(String longUrl){
        String shortCode = generateCode();
        urlStore.put(shortCode, longUrl);
        return BASE_URL + shortCode;
    }

    public String getOriginalUrl(String shortCode){
        return urlStore.get(shortCode);
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
