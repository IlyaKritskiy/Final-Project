package service;

import entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserService {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/final_project";
    static final String USER = "postgres";
    static final String PASSWORD = "qwe357iop";

    public boolean checkUser(String emailCheck, String passwordCheck){
        int count = 0;
        Connection connection = null;
        try{
            Map<String, String> map = new HashMap<>();
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sql = "select email, password from users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                map.put(email,password);
            }
            Set<Map.Entry<String, String>> set = map.entrySet();
            for (Map.Entry<String, String> authentication : set) {
                if(!emailCheck.equals(authentication.getKey())){
                    count++;
                }
                if(emailCheck.equals(authentication.getKey()) && passwordCheck.equals(getDecoding(authentication.getValue()))){
                    System.out.println("Welcome!");
                }
            }

//            if(count == usersCount()){
//                addUser(user);
//            } else {
            if(count!=usersCount()){
                System.out.println("User already exists");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addUser(User user) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sql = "insert into users(email, password, role) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, getEncryption(user.getPassword()));
            preparedStatement.setString(3, String.valueOf(user.getRole()));

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("User not added");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getEncryption(String password){
        char[] arraySymbols = password.toCharArray();
        for (int i = 0; i < arraySymbols.length; i++) {
            arraySymbols[i] = (char) ((int) arraySymbols[i] + 3);
        }
        return String.valueOf(arraySymbols);
    }

    public static String getDecoding(String password){
        char[] arraySymbols = password.toCharArray();
        for (int i = 0; i < arraySymbols.length; i++) {
            arraySymbols[i] = (char) ((int) arraySymbols[i] - 3);
        }
        return String.valueOf(arraySymbols);
    }

    public int usersCount() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sql = "select count(*) from users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("Unable to execute request, please fix errors!");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public String getUserRole(String emil){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sql = "select role from users where email like '" + emil + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getString(1);

        } catch (SQLException e) {
            System.out.println("User not added");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    BookService bookService = new BookService();

}
