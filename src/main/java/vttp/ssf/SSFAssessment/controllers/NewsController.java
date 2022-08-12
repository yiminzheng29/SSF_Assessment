package vttp.ssf.SSFAssessment.controllers;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.SSFAssessment.models.News;
import vttp.ssf.SSFAssessment.services.NewsService;

@Controller
@RequestMapping("/")
public class NewsController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping(produces = "text/html")
    public String getArticles(Model model) {
        List<News> results = newsSvc.getArticles();
        model.addAttribute("results", results);
        return "index";
        
    }

        @PostMapping(path="/articles")
        public String saveArticles(@RequestParam(value="selected") String[] selected, Model model) {
            List<News> articles = newsSvc.getArticles();
            List<News> savedNews = new LinkedList<>();
            for (News news:articles) {
                for (int i = 0; i < selected.length; i++) {
                    if (news.getId().equals(selected[i])) {
                        savedNews.add(news);
                        System.out.println(news.getId());
                    }
                }
            }
            newsSvc.saveArticles(savedNews);
            model.addAttribute("articles", savedNews);


            return "articles";
        }
        
    }
    

