package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class DVD extends AbstractDocument {
    public DVD(String title, String author, String editor, String publicationYear, String genre) {
        super(title, author, editor, publicationYear, genre);
    }

    @Override
    public int loanTimeInDay() {
        return 7;
    }
}
