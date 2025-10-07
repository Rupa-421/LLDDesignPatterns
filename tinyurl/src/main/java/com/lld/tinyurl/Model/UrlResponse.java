package com.lld.tinyurl.Model;

public class UrlResponse {

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    private String shortUrl;
    public UrlResponse(String shortUrl){
        this.shortUrl = shortUrl;
    }

}
