package javatown.DTO;

import javatown.modele.Book;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class BookFormDTO extends AbstractDocumentFormDTO implements IFormDTO<Book> {
    private int nbPage;

    public BookFormDTO(String id, String title, String author, String editor, int publicationYear, String genre, int nbPage) {
        super(id, title, author, editor, publicationYear, genre);
        this.nbPage = nbPage;
    }

    public BookFormDTO(Book book){
        this(
                Long.toString(book.getId()),
                book.getTitle(),
                book.getAuthor(),
                book.getEditor(),
                (book.getPublicationYear() == null)? 0 : Integer.parseInt(book.getPublicationYear().toString()),
                book.getGenre(),
                book.getNbPage()
        );
    }

    @Override
    public Book toModel() {
        return new Book(title, author, editor, Integer.toString(publicationYear), nbPage, genre);
    }
}
