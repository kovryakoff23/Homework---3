import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import net.lamberto.junit.GuiceJUnitRunner;
import net.lamberto.junit.GuiceJUnitRunner.GuiceModules;
import org.jetbrains.annotations.NotNull;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(GuiceJUnitRunner.class)
@GuiceModules(MyTest.TestModule.class)
public class MyTest {


    @Inject
    private Injector injector;


    @Rule
    public @NotNull ExpectedException exception = ExpectedException.none();

    @Test (expected = IOException.class)
    public void theCapacityLessThanNumberOfBooks() {
        injector.getInstance(FactoryLibrary.class).library("books.txt", 20);
    }

    @Test
    public void booksArrangedInCellsInOrderTheyReturnedBookFactory(){
        int capacity = 105;
        String fileNAme = "books.txt";
        ArrayList<Book> actual = injector.getInstance(FactoryBook.class).getBooks(fileNAme);
        for (int i = actual.size(); i<capacity; i++)
            actual.add(null);
        ArrayList<Book> expected = injector.getInstance(FactoryLibrary.class).library(fileNAme,capacity).books;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void takeBookFromEmptyCell(){
        Library library = injector.getInstance(FactoryLibrary.class).library("books.txt", 20);
        library.books.set(5, null);
        library.takeBook(5);
    }

    @Test
    public void exactlyBookThatInThisCell(){
        Library library = injector.getInstance(FactoryLibrary.class).library("books.txt", 120);
        Book actuallyBook = library.books.get(5);
        Assert.assertEquals(actuallyBook, library.takeBook(5));
    }


    @Test(expected = IOException.class)
    public void whenAddingBookThereAreNoFreeCells(){
        Library library = injector.getInstance(FactoryLibrary.class).library("books.txt", 95);
        Book checkBook = Mockito.mock(Book.class);
        library.addBook(checkBook);
    }





    public static class TestModule extends AbstractModule {

        @Override
        protected void configure() {

        }
    }


}