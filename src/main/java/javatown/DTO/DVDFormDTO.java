package javatown.DTO;

import javatown.modele.DVD;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class DVDFormDTO extends AbstractDocumentFormDTO {
    public DVDFormDTO(String id, String title, String author, String editor, int publicationYear, String genre) {
        super(id, title, author, editor, publicationYear, genre);
    }

    public DVDFormDTO(DVD dvd){
        this(
                Long.toString(dvd.getId()),
                dvd.getTitle(),
                dvd.getAuthor(),
                dvd.getEditor(),
                (dvd.getPublicationYear() == null)? 0 : Integer.parseInt(dvd.getPublicationYear().toString()),
                dvd.getGenre()
        );
    }

    public DVD toDVD (){
        return new DVD(title, author, editor, Integer.toString(publicationYear), genre);
    }
}
