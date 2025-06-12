package ir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String BLACKLIST_PREFIX = "blacklisted:";

    @Override
    public void blacklistToken(String token) {
        redisTemplate.opsForValue().set(
                BLACKLIST_PREFIX + token,
                "true",
                86400000,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public Boolean isTokenBlacklisted(String token) {
        Boolean a =  redisTemplate.hasKey(BLACKLIST_PREFIX + token);
        return a;
    }
}

