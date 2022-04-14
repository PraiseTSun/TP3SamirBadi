package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class CD extends AbstractDocument {
    public CD(String title, String author, String editor, String publicationYear, String genre) {
        super(title, author, editor, publicationYear, genre);
    }

    @Override
    public int loanTimeInDay() {
        return 14;
    }
}
