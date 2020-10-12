import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


public class FactoryBook {
    @NotNull
    private static final Type listBooksType = new TypeToken<ArrayList<Book>>() {}.getType();
    private String fileName;
    @NotNull
    public Collection<Book> books() {
        try {
            return new Gson().fromJson(new BufferedReader(new FileReader(fileName)), listBooksType);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
    public  ArrayList<Book> getBooks(@NotNull String fileName) {
        this.fileName = fileName;
        ArrayList<Book> books = new ArrayList<>();
        books = (ArrayList<Book>) books();
        return books;
    }
}
