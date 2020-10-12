import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MyMockitoTest {
    @Inject
    private Injector injector;

    @Test
    public void WhenAddBookItIsPlacedInFirstFreeCell() {
        Library library = new FactoryLibrary().library("books.txt", 105);
        int i = 0;
        while (library.books.get(i) != null ) {
            i++;
        }
        int foundNullCell = i;
        Book checkBook = Mockito.mock(Book.class);
        library.addBook(checkBook);
        Assert.assertEquals(checkBook, library.takeBook(foundNullCell));
    }
}
