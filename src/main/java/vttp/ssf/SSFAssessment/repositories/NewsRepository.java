package vttp.ssf.SSFAssessment.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp.ssf.SSFAssessment.models.News;

@Repository
public class NewsRepository {
    
    @Autowired @Qualifier ("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public void saveArticles(News article) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(article.getId(), article.toJson().toString());
    }

    public Optional<News> getArticle(String id) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(id); 
        if (value==null) {
            return Optional.empty();
        }
        return Optional.of(News.create(value));
    }
}
