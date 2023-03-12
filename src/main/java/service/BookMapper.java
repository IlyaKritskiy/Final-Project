package service;

import entity.Author;
import entity.Book;
import entity.BookType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookMapper {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/final_project";
    static final String USER = "postgres";
    static final String PASSWORD = "qwe357iop";

    public List<Object> mapper(String sql){
        Connection connection = null;
        try {
            List<Object> list = new ArrayList<>();
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book book;
            Author author;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int yearWrite = resultSet.getInt("year_write");
                int pageCount = resultSet.getInt("page_count");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int count = resultSet.getInt("instance_count");
                BookType format = BookType.valueOf(resultSet.getString("type"));
                book = new Book(id, name, yearWrite, pageCount, count, format);
                author = new Author(firstName, lastName);
                list.add("\n" + book);
                list.add(author);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return list;
        } catch (Exception e){
            System.out.println("Unable to execute request, please fix errors!");
        } finally {
            try{
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }
}
