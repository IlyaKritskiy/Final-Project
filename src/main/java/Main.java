import entity.*;
import mailService.Mail;
import service.BookService;
import service.UserService;

import javax.mail.Session;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Mail mail = new Mail();
        mail.sendMail();

        Book book1 = new Book("Harry Potter Pidor", 2000, 219);
        Book book2 = new Book("War and Peace ", 1904, 1200);
        Book book3 = new Book("A little prince", 1934, 189);

        Author author1 = new Author("Antoine", "Saint-Exupery");

        User user1 = new User("ilya.kritskiy@gmail.com", "050803", UserRole.ADMIN);
        User user2 = new User("luntilk228@gmail.com", "111111", UserRole.USER);
        User user3 = new User("kuzmich@gmail.com", "22224", UserRole.USER);


        UserService userService = new UserService();
        BookService bookService = new BookService();

//        System.out.println(userService.usersCount());
//        System.out.println(userService.addUser(user1));
//        System.out.println(userService.checkUser(user3));

//        System.out.println(bookService.addBook(book1));
//        System.out.println(bookService.addBook(book3));

//        System.out.println(bookService.updateBook(6, book1));
//        System.out.println(bookService.removeById(4));
//        System.out.println(bookService.findByYearWrite(1999));
//        System.out.println(bookService.findByAuthor(author1));
//        System.out.println(bookService.findByName("War"));
//        System.out.println(bookService.findAll());
//        System.out.println(userService.getUserRole("ilya.kritskiy@gmail.com"));

//        System.out.println("Authentication");
//        System.out.print("Input e-mail: ");
//        String email = getInput();
//        System.out.print("Input password: ");
//        String password = getInput();
//        if (userService.checkUser(email, password)) {
//            String role = userService.getUserRole(email);
//            User user = new User(email, password, UserRole.valueOf(role));
//            if (user.getRole().equals(UserRole.USER)) {
//                while (true) {
//                    System.out.println("");
//                    System.out.println("1 - Book search by name");
//                    System.out.println("2 - Book search by year write");
//                    System.out.println("3 - Book search by author");
//                    System.out.println("4 - View the entire catalog");
//                    System.out.println("0 - Exit");
//                    System.out.print("Input menu point: ");
//                    int menuPoint = Integer.parseInt(getInput());
//                        switch (menuPoint) {
//                            case 1:
//                                System.out.print("Input book name: ");
//                                System.out.println(bookService.findByName(getInput()));
//                                break;
//                            case 2:
//                                System.out.print("Input book by year write: ");
//                                System.out.println(bookService.findByYearWrite(Integer.parseInt(getInput())));
//                                break;
//                            case 3:
//                                System.out.print("Input author last name: ");
//                                System.out.println(bookService.findByAuthor(getInput()));
//                                break;
//                            case 4:
//                                System.out.println(bookService.findAll());
//                                break;
//                            case 0:
//                                break;
//
//                        }
//                }
//            }
//            if (user.getRole().equals(UserRole.ADMIN)) {
//                while (true) {
//                    System.out.println("");
//                    System.out.println("1 - Book search by name");
//                    System.out.println("2 - Book search by year write");
//                    System.out.println("3 - Book search by author");
//                    System.out.println("4 - View the entire catalog");
//                    System.out.println("5 - Add book");
//                    System.out.println("6 - Update book");
//                    System.out.println("7 - Remove book");
//                    System.out.println("0 - Exit");
//                    System.out.print("Input menu point: ");
//                    int menuPoint = Integer.parseInt(getInput());
//                    switch (menuPoint) {
//                        case 1:
//                            System.out.print("Input book name: ");
//                            System.out.println(bookService.findByName(getInput()));
//                            break;
//                        case 2:
//                            System.out.print("Input book by year write: ");
//                            System.out.println(bookService.findByYearWrite(Integer.parseInt(getInput())));
//                            break;
//                        case 3:
//                            System.out.print("Input author last name: ");
//                            System.out.println(bookService.findByAuthor(getInput()));
//                            break;
//                        case 4:
//                            System.out.println(bookService.findAll());
//                            break;
//                        case 5:
//                            System.out.print("Input name: ");
//                            String name = getInput();
//                            System.out.print("Input year write: ");
//                            int yearWrite = Integer.parseInt(getInput());
//                            System.out.print("Input page count: ");
//                            int pageCount = Integer.parseInt(getInput());
//                            Book newBook = new Book(name, yearWrite, pageCount);
//                            bookService.addBook(newBook);
//                            break;
//                        case 6:
//                            System.out.print("Input book id for update this book: ");
//                            int id = Integer.parseInt(getInput());
//                            System.out.print("Input name: ");
//                            String updateName = getInput();
//                            System.out.print("Input year write: ");
//                            int updateYearWrite = Integer.parseInt(getInput());
//                            System.out.print("Input page count: ");
//                            int updatePageCount = Integer.parseInt(getInput());
//                            Book updateBook = new Book(updateName, updateYearWrite, updatePageCount);
//                            System.out.println(bookService.updateBook(id, updateBook));
//                            break;
//                        case 7:
//                            System.out.print("Input book id for remove this book: ");
//                            System.out.println(bookService.removeById(Integer.parseInt(getInput())));
//                            break;
//                        case 0:
//                            break;
//                    }
//                }
//            }
//        } else {
//            System.out.println("Do you want to register?");
//            System.out.println("1 - Yes");
//            System.out.println("2 - No");
//            switch (getInput()){
//                case "1":
//                    System.out.print("Input you e-mail: ");
//                    String newEmail = getInput();
//                    System.out.print("Input you password: ");
//                    String newPassword = getInput();
//                    System.out.println("Choose your role");
//                    System.out.println("1 - Admin");
//                    System.out.println("2 - User");
//                    switch (getInput()){
//                        case "1":
//                            User userA = new User(newEmail, newPassword, UserRole.ADMIN);
//                            userService.addUser(userA);
//                        case "2":
//                            User userU = new User(newEmail, newPassword, UserRole.USER);
//                            userService.addUser(userU);
//                    }
//                case "2":
//                    break;
//            }
//        }
//    }
//        public static String getInput () {
//            Scanner in = new Scanner(System.in);
//            return in.nextLine();
//        }
    }
}


