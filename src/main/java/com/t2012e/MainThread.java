package com.t2012e;

import com.t2012e.worker.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainThread {
    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.connect("https://vnexpress.net/ronaldo-khong-vao-doi-hinh-tieu-bieu-euro-2021-4308980.html").get();
//        Element title = doc.select(".title-detail").first();
//        Element description = doc.select(".description").first();
//        System.out.println(title.text());
//        System.out.println(description.text());
        ArrayList<Article> list = new ArrayList<>();
        Document doc = Jsoup.connect("https://vnexpress.net/").get();
        Elements els = doc.select(".title-news a");
        for (int i = 0; i < els.size(); i++) {
            Article article = new Article();
            article.setUrl(els.get(i).attr("href"));
            article.setStatus(0);
            list.add(article);
        }

        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            Document currentDoc = Jsoup.connect(article.getUrl()).get();
            String title = currentDoc.select(".title-detail").first().text();
            String content = currentDoc.select(".fck_detail").first().text();
            System.out.printf("%d - %s\n", i+1, title);
        }
//        Article article = new Article("url", "title", "description", "content", "thumb", 0);
//        try {
//            insertArticle(article);
//        } catch (SQLException throwables) {
//            System.out.println("Action failed.");
//        }
    }
//    static void insertArticle(Article article) throws SQLException {
//        Connection cnn = ConnectionHelper.getConnection();
//        StringBuilder sqlBuilder = new StringBuilder();
//        sqlBuilder.append("insert into articles");
//        sqlBuilder.append(" ");
//        sqlBuilder.append("(url, title, description, content, thumbnail, createdAt, updatedAt, status)");
//        sqlBuilder.append(" ");
//        sqlBuilder.append("values");
//        sqlBuilder.append(" ");
//        sqlBuilder.append("(?, ?, ?, ?, ?, ?, ?, ?)");
//        PreparedStatement preparedStatement = cnn.prepareStatement(sqlBuilder.toString());
//        preparedStatement.setString(1, article.getUrl());
//        preparedStatement.setString(2, article.getTitle());
//        preparedStatement.setString(3, article.getDescription());
//        preparedStatement.setString(4, article.getContent());
//        preparedStatement.setString(5, article.getThumbnail());
//        preparedStatement.setString(6, article.getCreatedAtString());
//        preparedStatement.setString(7, article.getUpdatedAtString());
//        preparedStatement.setInt(8, article.getStatus());
//        preparedStatement.execute();
//    }
}

