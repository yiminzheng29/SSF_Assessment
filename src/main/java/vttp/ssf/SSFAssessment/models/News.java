package vttp.ssf.SSFAssessment.models;

import java.io.Reader;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class News {
    
    private String id;
    private Long published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Long getPublished_on() {
        return published_on;
    }
    public void setPublished_on(Long published_on) {
        this.published_on = published_on;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }

    public static News create(String s) {
        Reader r = new StringReader(s);
        JsonReader jr = Json.createReader(r);
        return create(jr.readObject());
    }

    public static News create(JsonObject jo) {
        News news = new News();
        news.setId(jo.getString("id"));
        news.setPublished_on(jo.getJsonNumber("published_on").longValue());
        news.setTitle(jo.getString("title"));
        news.setUrl(jo.getString("url"));
        news.setImageurl(jo.getString("imageurl"));
        news.setBody(jo.getString("body"));
        news.setTags(jo.getString("tags"));
        news.setCategories(jo.getString("categories"));
        return news;
    }
     
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("published_on", published_on)
            .add("title", title)
            .add("url", url)
            .add("imageurl", imageurl)
            .add("body", body)
            .add("tags", tags)
            .add("categories", categories)
            .build();
    }



}
