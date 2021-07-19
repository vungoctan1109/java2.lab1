package com.t2012e.worker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ArticleThread extends Thread{
    private String url;
    private Article article;
    public ArticleThread(String url){
        this.url = url;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        System.out.printf("Crawling data from url %s\n", url);
        crawlData();
    }

    public void crawlData() {
        article = new Article();
        article.setUrl(url);
        try {
            Document document = Jsoup.connect(url).get();
            Element titleNode = document.selectFirst("h1.title-detail");
            if (titleNode != null) {
                String title = titleNode.text();
                article.setTitle(title);
            }

            Element contentElement = document.selectFirst("h1.title-detail");
            if (contentElement != null) {
                String content = contentElement.text();
                article.setContent(content);
            }

            Element descriptionElement = document.selectFirst("p.description");
            if (descriptionElement != null) {
                String description = descriptionElement.text();
                article.setDescription(description);
            }

            Element thumbnailElement = document.selectFirst("div.fig-picture picture img");
            if (thumbnailElement != null) {
                String thumbnail = thumbnailElement.attr("data-src");
                article.setThumbnail(thumbnail);
                System.out.println(article.getThumbnail());
            }else {
                article.setThumbnail("http://default.jpeg");
            }
            article.setStatus(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("Error %s", e.getMessage());
        }

    }
}
