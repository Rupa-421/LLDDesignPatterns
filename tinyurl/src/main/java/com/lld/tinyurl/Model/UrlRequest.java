package com.lld.tinyurl.Model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UrlRequest {
    private String longUrl;
    private LocalDateTime expiresAt;

}
