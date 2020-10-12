import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import java.io.IOException;
import java.util.ArrayList;

public class Library {

   public int capacity;
   public ArrayList <Book> books = new ArrayList<>();


    @SneakyThrows
    public Book takeBook (int numberCell){
        if (books.get(numberCell) != null) {
            return books.get(numberCell);
        }
        else
                throw new IOException("null Cell");
    }
    @SneakyThrows
    public void addBook (Book book)
    {   int i=0;
            while (i<capacity){
                if(books.get(i) == null) {
                    books.set(i, book);
                    System.out.println("AddBook successfully  " + (i+1));
                    return;
                }
                i++;
            }
            if (i == capacity)
                throw new IOException("Not found free cell");
    }
    public void printContent (){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(books);
        System.out.println(json);
    }
}
