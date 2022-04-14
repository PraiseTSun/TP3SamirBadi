package javatown.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractDocumentFormDTO {
    protected String id;
    protected String title;
    protected String author;
    protected String editor;
    protected int publicationYear;
    protected String genre;

    public AbstractDocumentFormDTO(String id, String title, String author, String editor, int publicationYear, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }
}
