package com.nikhil.anand.retrofittutorial;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

  @SerializedName("articles")
   private List<Articles>  articles;

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
