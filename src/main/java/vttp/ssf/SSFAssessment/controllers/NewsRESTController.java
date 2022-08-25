package vttp.ssf.SSFAssessment.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.ssf.SSFAssessment.models.News;
import vttp.ssf.SSFAssessment.services.NewsService;

@RestController
@RequestMapping(path="/news", produces=MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
    
    @Autowired
    private NewsService newsSvc;

    @GetMapping(value="{id}")
    public ResponseEntity<String> getArticle(@PathVariable String id) {
        Optional<News> news = newsSvc.getArticle(id);

        if (news.isEmpty()) {
            JsonObject errMsg = Json.createObjectBuilder().add("error", "Cannot find news article %s".formatted(id)).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMsg.toString());
        }
        News result = news.get();
        return ResponseEntity.ok(result.toJson().toString());
    }
}
