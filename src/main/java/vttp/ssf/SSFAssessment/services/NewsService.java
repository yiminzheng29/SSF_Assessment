package vttp.ssf.SSFAssessment.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.SSFAssessment.models.News;
import vttp.ssf.SSFAssessment.repositories.NewsRepository;

@Service
public class NewsService {
    
    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/";

    @Autowired
    private NewsRepository newsRepo;

    @Value("${API_KEY}")
    private String key;

    public List<News> getArticles() {
        List<News> newsList = new LinkedList<>(); 
        String payload;

        RequestEntity<Void> req = RequestEntity.get(URL).build();
        RestTemplate temp = new RestTemplate();
        ResponseEntity<String> resp;

        try {
            resp = temp.exchange(req, String.class);

        } catch (Exception ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
            return newsList;
        }
        payload = resp.getBody();
        System.out.println("Success: " + payload);
        JsonReader jr = Json.createReader(new StringReader(payload));
        JsonObject jo = jr.readObject();
        JsonArray jArr = jo.getJsonArray("Data");
        for (int i = 0; i < jArr.size(); i++) {
            JsonObject news = jArr.getJsonObject(i);
            newsList.add(News.create(news));
        }
        return newsList;
    }

    public void saveArticles(List<News> articles) {
        for (int i = 0; i < articles.size(); i++) {
                newsRepo.saveArticles(articles.get(i));;
        }
    }

    public Optional<News> getArticle(String id) {
        return newsRepo.getArticle(id);
    }
}
