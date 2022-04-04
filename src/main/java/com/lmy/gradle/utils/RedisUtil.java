package com.lmy.gradle.utils;

import com.lmy.gradle.constant.RedisKeyConst;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        if (StringUtils.isNotEmpty(key)) {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            System.out.println("redis写入异常：(" + key + "," + value.toString() + ")" + ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("redis写入异常：(" + key + "," + value.toString() + ")" + ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setList(final String key, Object value) {
        boolean result = false;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            operations.rightPushAll(key, value);
            result = true;
        } catch (Exception e) {
            System.out.println("redis写入异常：(" + key + "," + value.toString() + ")" + ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }

    /**
     * 取出缓存
     *
     * @param key
     * @return
     */
    public List getList(final String key) {
        List<Object> list = null;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            list = operations.range(key, 0, -1);
        } catch (Exception e) {
            System.out.println("redis读取异常：(" + key + ")" + ExceptionUtils.getFullStackTrace(e));
        }
        return list;
    }

    /**
     * 左入队列操作
     */
    public long lpush(final String key, Object value) {
        long length = 0;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            length = operations.leftPush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("redis写入异常：" + e.getMessage());
        }
        return length;
    }

    /**
     * 队列trim操作
     */
    public void ltrim(final String key, long start, long end) {
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            operations.trim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("redis写入异常：" + e.getMessage());
        }
    }

    /**
     * 右出队列操作rpop
     */
    public Object rpop(final String key) {
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            return operations.rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("redis写入异常：" + e.getMessage());
        }
        return null;
    }

    /**
     * 右入队列操作
     */
    public long rpush(final String key, Object value) {
        long length = 0;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            length = operations.rightPush(key, value);
        } catch (Exception e) {
            System.out.println("redis写入异常：" + ExceptionUtils.getFullStackTrace(e));
        }
        return length;
    }

    public Set<String> keys(final String pattern) {
        try {
            Set<String> keys = redisTemplate.keys(pattern);
            return keys;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("redis写入异常：" + e.getMessage());
        }
        return null;
    }

    public List range(String key, Integer start, Integer end) {
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            return operations.range(key, start, end);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }

    public long rpushAll(final String key, Collection values) {
        long length = 0;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            length = operations.rightPushAll(key, values);
            redisTemplate.expire(key, RedisKeyConst.EXPIRE_TIME_DAY, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("rpushAll:" + values + ExceptionUtils.getFullStackTrace(e));
        }
        return length;
    }

    public long size(final String key) {
        long length = 0;
        try {
            ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
            length = operations.size(key);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        return length;
    }

    public void putHash(String key, String hashKey, Object hashValue) {
        try {
            HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
            opsForHash.put(key, hashKey, hashValue);
        } catch (Exception e) {
            logger.error("hashPut:" + ExceptionUtils.getFullStackTrace(e));
        }
    }

    public void putHashAll(String key, Map map) {
        try {
            HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
            opsForHash.putAll(key, map);

        } catch (Exception e) {
            logger.error("hashPutAll:" + ExceptionUtils.getFullStackTrace(e));
        }
    }

    public Object getHash(String key, String hashKey) {
        try {
            HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
            return opsForHash.get(key, hashKey);
        } catch (Exception e) {
            logger.error("hashGet:" + ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }

    public Map getHashAll(String key) {
        try {
            HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
            return opsForHash.entries(key);
        } catch (Exception e) {
            logger.error("hashGetAll:" + ExceptionUtils.getFullStackTrace(e));
        }
        return new HashMap();
    }

    public Long getHashSize(String key) {
        try {
            HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
            return opsForHash.size(key);
        } catch (Exception e) {
            logger.error("getHashSize:" + ExceptionUtils.getFullStackTrace(e));
        }
        return 0L;
    }

    public List multiGet(String key, Collection hashKeys) {
        try {
            HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
            List multiGet = opsForHash.multiGet(key, hashKeys);
            return multiGet;
        } catch (Exception e) {
            logger.error("multiGet:" + ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }

    public Long deleteHash(String key, Object... hashKeys){
        try {
            HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
            return opsForHash.delete(key, hashKeys);
        } catch (Exception e) {
            logger.error("multiGet:" + ExceptionUtils.getFullStackTrace(e));
        }
        return 0L;
    }

    public void expire(String key, Long expireTime) {
        try {
            redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("expire:" + ExceptionUtils.getFullStackTrace(e));
        }
    }

    public Set<String> getZSet(String key, long start, long end){
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            return zSetOperations.reverseRange(key, start, end);
        } catch (Exception e) {
            logger.error("getZSet:" + ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }

    public Long getZSetSize(String key){
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            return zSetOperations.size(key);
        } catch (Exception e) {
            logger.error("getZSet:" + ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }

    public void addZSet(String key, Set<ZSetOperations.TypedTuple> tuples){
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            Long add = zSetOperations.add(key, tuples);
            System.out.println(add);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("addZSet:" + ExceptionUtils.getFullStackTrace(e));
        }
    }

    public Long removeZSet(String key,Object... values){
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            return zSetOperations.remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("removeZSet:" + ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }

}
