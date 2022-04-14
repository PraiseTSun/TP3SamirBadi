package javatown.DTO;

import javatown.modele.CD;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class CDFormDTO extends AbstractDocumentFormDTO {
    public CDFormDTO(String id, String title, String author, String editor, int publicationYear, String genre) {
        super(id, title, author, editor, publicationYear, genre);
    }

    public CDFormDTO(CD cd){
        this(
            Long.toString(cd.getId()),
            cd.getTitle(),
            cd.getAuthor(),
            cd.getEditor(),
            (cd.getPublicationYear() == null)? 0 : Integer.parseInt(cd.getPublicationYear().toString()),
            cd.getGenre()
        );

    }

    public CD toCD(){
        return new CD(title, author, editor, Integer.toString(publicationYear), genre);
    }
}
