package com.kcy.common.redis;

import com.kcy.common.utils.SerializingUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Log
@Component
public class RedisComponent {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    //向redis里存入数据
    public void opsForValue(String key, String val) {
        stringRedisTemplate.opsForValue().set(key, val);
    }

    //向redis里存入对象数据
    public void opsForValue(String key, Object val) {
        byte[] serialize = SerializingUtil.serialize(val);
        stringRedisTemplate.opsForValue().set(key, String.valueOf(serialize));
    }

    //向redis里存入数据和设置缓存时间
    public void opsForValue(String key, String val, Long time) {
        stringRedisTemplate.opsForValue().set(key, val, time, TimeUnit.SECONDS);
    }

    //向redis里存入数据和设置缓存时间
    public void opsForValue(String key, Object val, Long time) {
        byte[] serialize = SerializingUtil.serialize(val);
        stringRedisTemplate.opsForValue().set(key, String.valueOf(serialize), time, TimeUnit.SECONDS);
    }

    //根据key获取缓存中的val
    public String getOpsForValue(String key) {
        String result = stringRedisTemplate.opsForValue().get(key);
        return result;
    }

    //根据key获取缓存中的val
    public Object getOpsForObject(String key) {
        try {
            String result = stringRedisTemplate.opsForValue().get(key);
            if(result == null) {
                return null;
            }
            Object deserialize = SerializingUtil.deserialize(result.getBytes());
            return deserialize;
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    //对key做加减val操作
    public Long boundValueOpsIncrement(String key, Long l) {
        Long increment = stringRedisTemplate.boundValueOps(key).increment(l);
        return increment;
    }

    //设置过期时间
    public Boolean expire(String key, Long time) {
        Boolean expire = stringRedisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
        return expire;
    }

    //根据key获取过期时间
    public Long getExpire(String key) {
        Long expire = stringRedisTemplate.getExpire(key);
        return expire;
    }

    //根据key获取过期时间并换算成指定单位
    public Long getExpire(String key, TimeUnit timeUnit) {
        Long expire = stringRedisTemplate.getExpire("test", timeUnit == null ? TimeUnit.SECONDS : timeUnit);
        return expire;
    }

    //根据key删除缓存
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    //检查key是否存在，返回boolean值
    public boolean hasKey(String key) {
        Boolean aBoolean = stringRedisTemplate.hasKey(key);
        return aBoolean;
    }

    //向指定key中存放set集合
    public Long add(String key, String... vals) {
        Long red = stringRedisTemplate.opsForSet().add(key, vals);
        return red;
    }

    //根据key查看集合中是否存在指定数据
    public Boolean isMember(String key, String val) {
        Boolean member = stringRedisTemplate.opsForSet().isMember(key, val);
        return member;
    }

    //根据key获取set集合
    public Set <String> members(String key) {
        Set <String> members = stringRedisTemplate.opsForSet().members(key);
        return members;
    }
}
