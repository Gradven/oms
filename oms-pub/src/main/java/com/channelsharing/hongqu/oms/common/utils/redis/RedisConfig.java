package com.channelsharing.hongqu.oms.common.utils.redis;

import com.channelsharing.hongqu.oms.common.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getReturnType().getSimpleName());
            for (Object obj : params) {
                sb.append("_");
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 根据model的id生成缓存的key值
     * @return
     */
    @Bean
    public KeyGenerator keyGeneratorModelId() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getReturnType().getSimpleName());

            for (Object object : params) {
                sb.append("_");
                BaseEntity model = (BaseEntity)object;
                sb.append(model.getId());
            }
            return sb.toString();
        };
    }

    private CacheManager cacheManager;


    @Override
    public CacheManager cacheManager() {
        return this.cacheManager;
    }

    @SuppressWarnings("rawtypes")
    @Bean(name = "redisCacheManager")
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new SpringRedisCacheManager(redisTemplate);
        //设置缓存过期时间
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }

    @Resource
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @PostConstruct
    public void afterPropertiesSet(){
        cacheManager = new SpringRedisCacheManager(redisTemplate);
    }
}
