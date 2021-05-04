package ru.gb.market.happy.core.repository;


import ru.gb.market.happy.core.model.TokenRedis;
import ru.gb.market.happy.core.model.UserInfo;

import java.util.Map;

public interface RedisRepository {

    void addToken(TokenRedis tokenRedis);

    void add(String token);

    String findRedisToken(String token);
    
}
