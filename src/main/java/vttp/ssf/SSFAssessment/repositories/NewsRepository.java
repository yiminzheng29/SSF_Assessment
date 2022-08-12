package vttp.ssf.SSFAssessment.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp.ssf.SSFAssessment.models.News;

@Repository
public class NewsRepository {
    
    @Autowired @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public void saveArticles(News news) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(news.getId(), news.toJson().toString());
        
    }
}
