package service;

import entity.Author;
import entity.Book;
import entity.BookType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookService {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/final_project";
    static final String USER = "postgres";
    static final String PASSWORD = "qwe357iop";

    public List<Object> findAll() throws SQLException {
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
                    "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                    "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id;";
        BookMapper bookMapper = new BookMapper();

        return bookMapper.mapper(sql);
    }

    public List<Object> findByName(String nameFindBook) throws SQLException {
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
                    "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                    "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id" +
                    " where name like '%" + nameFindBook + "%'";
        BookMapper bookMapper = new BookMapper();
        return bookMapper.mapper(sql);
    }

    public List<Object> findByAuthor(String lastName) throws SQLException {
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
                    "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                    "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id" +
                    " where last_name like '%" + lastName + "%'";
        BookMapper bookMapper = new BookMapper();
        return bookMapper.mapper(sql);
    }

    public List<Object> findByYearWrite(int year) throws SQLException {
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
                "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id" +
                " where year_write = " + year;
        BookMapper bookMapper = new BookMapper();
        return bookMapper.mapper(sql);
    }
    public boolean addBook(Book book) throws SQLException {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sql = "insert into books(name, year_write, page_count) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getYearWrite());
            preparedStatement.setInt(3, book.getPageCount());

            return preparedStatement.execute();
        } catch (SQLException e){
            System.out.println("Book not added");
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e){
            e.printStackTrace();
            }
        }
        return false;
    }

    public boolean removeById(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sql = "delete from books_authors where books_id = " + id + ";" +
                    " delete from books_format where books_id = " + id + ";" +
                    "delete from books where id = " + id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            return preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Book not removed");
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateBook(int id, Book book) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sql = "update books set name = ?," +
                    "year_write = ?," +
                    "page_count = ?" +
                    "where id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getYearWrite());
            preparedStatement.setInt(3, book.getPageCount());

            return preparedStatement.execute();
        } catch (SQLException e){
            System.out.println("The book has not been changed");
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
