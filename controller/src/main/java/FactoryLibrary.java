import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class FactoryLibrary {

    @SneakyThrows
    public Library library (@NotNull String fileName, int capacity) {
        ArrayList<Book> books = new FactoryBook().getBooks(fileName);
        Library library = new Library();
        library.capacity = capacity;
        library.books = new ArrayList<>(capacity);
        if (books.size() <= capacity) {
            library.books.addAll(books);
            if(books.size() < capacity)
                for (int i=books.size(); i< capacity; i++)
                    library.books.add(null);
            return library;
        }
        else{
                throw new IOException("Size Library < quantity books");
        }
    }
}
