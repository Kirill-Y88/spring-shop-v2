package ru.gb.market.happy.core.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(timeToLive = 10000L)
public class TokenRedis implements Serializable {

    private String tokenRedis;

    private  Long ttl;

    public TokenRedis(String tokenRedis, Long ttl) {
        this.tokenRedis = tokenRedis;
        this.ttl = ttl;
    }

    public TokenRedis() {
    }


    public String getTokenRedis() {
        return tokenRedis;
    }

    public void setTokenRedis(String tokenRedis) {
        this.tokenRedis = tokenRedis;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
