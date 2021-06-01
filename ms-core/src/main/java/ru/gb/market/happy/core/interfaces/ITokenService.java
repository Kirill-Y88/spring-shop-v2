package ru.gb.market.happy.core.interfaces;


import ru.gb.market.happy.core.model.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);

    String findRedisToken(String redistoken);

}
