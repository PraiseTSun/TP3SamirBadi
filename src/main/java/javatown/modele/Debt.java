package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Debt {
    @ToString.Exclude
    private final float FINE_PER_DAY = 0.25f;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Basic
    private LocalDate dateOfDebt;
    @ManyToOne
    @JoinColumn(name = "fk_client")
    @ToString.Exclude
    private Client client;
    private float fine;

    public Debt(Client client, String dateOfDebt, int nbDays) {
        this.client = client;
        this.dateOfDebt = LocalDate.parse(dateOfDebt);
        fine = (float) nbDays * FINE_PER_DAY;
    }

    public long getId() {
        return id;
    }
}
