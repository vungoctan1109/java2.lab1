package com.t2012e.worker;

import com.t2012e.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleModel {
    public boolean insert(Article article) {
        try {
            Connection cnn = ConnectionHelper.getConnection();
            if (cnn == null) {
                System.err.println("Can not open connection to database.");
                return false;
            }
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("insert into articles");
            sqlBuilder.append(" ");
            sqlBuilder.append("(url, title, description, content, thumbnail, createdAt, updatedAt, status)");
            sqlBuilder.append(" ");
            sqlBuilder.append("values");
            sqlBuilder.append(" ");
            sqlBuilder.append("(?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement preparedStatement = cnn.prepareStatement(sqlBuilder.toString());
            preparedStatement.setString(1, article.getUrl());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setString(4, article.getContent());
            preparedStatement.setString(5, article.getThumbnail());
            preparedStatement.setString(6, article.getCreatedAtString());
            preparedStatement.setString(7, article.getUpdatedAtString());
            preparedStatement.setInt(8, article.getStatus());
            preparedStatement.execute();
            return true;
        } catch (SQLException sqlException){
            System.err.println(sqlException.getMessage());
        }
        return false;
    }
}
