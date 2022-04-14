package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private String editor;
    private Year publicationYear;
    private String genre;
    private int nbExemplaire;
    @OneToMany(mappedBy = "document")
    @ToString.Exclude
    private List<Loan> loans;

    public AbstractDocument(String title, String author, String editor, String publicationYear, String genre) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.publicationYear = Year.parse(publicationYear);
        this.genre = genre;
        nbExemplaire = 1;
        loans = new ArrayList<>();
    }

    public void addExempliares(int nb){
        nbExemplaire += nb;
    }

    public boolean canBeLoan(){ return loans.size() < nbExemplaire;}

    public abstract int loanTimeInDay();
}
