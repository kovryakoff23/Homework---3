
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class  Book {
   private Author author;
   private String name;
}