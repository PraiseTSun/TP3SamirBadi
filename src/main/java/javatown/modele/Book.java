package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Book extends AbstractDocument {
    private int nbPage;

    public Book(String title, String author, String editor, String publicationYear, int nbPage, String genre) {
        super(title, author, editor, publicationYear, genre);
        this.nbPage = nbPage;
    }

    @Override
    public int loanTimeInDay() {
        return 21;
    }
}
