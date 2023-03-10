package service;

import entity.Author;
import entity.Book;
import entity.BookType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/final_project";
    static final String USER = "postgres";
    static final String PASSWORD = "qwe357iop";

    public List<Object> findAll() throws SQLException {
        List<Object> list = new ArrayList<>();
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
                "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Book book;
        Author author;
        while (resultSet.next()){
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
    }

    public List<Object> findByName(String nameFindBook) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
        "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id" +
                " where name like '" + nameFindBook + "%'" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Object> list = new ArrayList<>();
        Book book;
        Author author;
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String bookName = resultSet.getString("name");
            int yearWrite = resultSet.getInt("year_write");
            int pageCount = resultSet.getInt("page_count");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int count = resultSet.getInt("instance_count");
            BookType format = BookType.valueOf(resultSet.getString("type"));
            book = new Book(id, bookName, yearWrite, pageCount, count, format);
            author = new Author(firstName, lastName);
            list.add("\n" + book);
            list.add(author);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    public List<Object> findByAuthor(Author author) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
                "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id" +
                " where last_name like '" + author.getLastName() + "%'" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Object> list = new ArrayList<>();
        Book book;
        Author findAuthor;
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String bookName = resultSet.getString("name");
            int yearWrite = resultSet.getInt("year_write");
            int pageCount = resultSet.getInt("page_count");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int count = resultSet.getInt("instance_count");
            BookType format = BookType.valueOf(resultSet.getString("type"));
            book = new Book(id, bookName, yearWrite, pageCount, count, format);
            findAuthor = new Author(firstName, lastName);
            list.add("\n" + book);
            list.add(findAuthor);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    public List<Object> findByYearWrite(int year) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String sql = "select books.id, name, year_write, page_count, first_name, last_name, instance_count, type " +
                "from books join books_authors on books.id = books_authors.books_id join authors on authors.id = books_authors.authors_id " +
                "join books_format on books.id = books_format.books_id join format on format.id = books_format.format_id" +
                " where year_write = " + year;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Object> list = new ArrayList<>();
        Book book;
        Author findAuthor;
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String bookName = resultSet.getString("name");
            int yearWrite = resultSet.getInt("year_write");
            int pageCount = resultSet.getInt("page_count");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int count = resultSet.getInt("instance_count");
            BookType format = BookType.valueOf(resultSet.getString("type"));
            book = new Book(id, bookName, yearWrite, pageCount, count, format);
            findAuthor = new Author(firstName, lastName);
            list.add("\n" + book);
            list.add(findAuthor);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    public boolean addBook(Book book) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String sql = "insert into books(name, year_write, page_count) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, book.getName());
        preparedStatement.setInt(2, book.getYearWrite());
        preparedStatement.setInt(3, book.getPageCount());

        return preparedStatement.execute();
    }

    public boolean removeById(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String sql = "delete from books_authors where books_id = " + id +";" +
                " delete from books_format where books_id = " + id +";" +
                "delete from books where id = " + id +";" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        return preparedStatement.execute();
    }

    public boolean updateBook(int id, Book book) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String sql = "update books set name = ?," +
                "year_write = ?,"+
                "page_count = ?" +
                "where id = " + id;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, book.getName());
        preparedStatement.setInt(2, book.getYearWrite());
        preparedStatement.setInt(3, book.getPageCount());

        return preparedStatement.execute();
    }
}
