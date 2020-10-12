import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        int capacity;
        String fileName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("input Capacity");
        capacity = scanner.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("input FileName");
        fileName = scanner1.nextLine();
        final Injector injector = Guice.createInjector(new TestModule());
        Library library = injector.getInstance(FactoryLibrary.class).library(fileName,capacity);
        while (true) {
            System.out.println("Input type actions. Key in Ctrl+D to exit.");
            System.out.println("'1' - take book");
            System.out.println("'2' - add book");
            System.out.println("'3' - Print content");
            int type = scanner.nextInt();
            switch (type) {
                case 1: {
                    System.out.println("Input number cell: ");
                    int numberCell = scanner.nextInt();
                    library.takeBook(numberCell);
                    Gson gson = new GsonBuilder()
                            .setPrettyPrinting()
                            .create();
                    String json = gson.toJson(library.books.get(numberCell));
                    System.out.println(numberCell);
                    System.out.println(json);
                    library.books.set(numberCell, null);
                    break;
                }
                case 2: {
                    System.out.println("Input name author");
                    String nameAuthor = scanner1.nextLine();
                    System.out.println("Input name book");
                    String nameBook = scanner1.nextLine();
                    Book addBook = Book.builder()
                            .author(new Author(nameAuthor))
                            .name(nameBook)
                            .build();
                    library.addBook(addBook);
                    break;
                }
                case 3:{
                    library.printContent();
                    break;
                }
                default:{
                    System.out.println("Error");
                    break;
                }
            }
        }
    }
}
