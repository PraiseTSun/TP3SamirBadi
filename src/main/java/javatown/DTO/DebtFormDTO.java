package javatown.DTO;

import javatown.modele.Debt;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class DebtFormDTO implements IFormDTO<Debt> {
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    private String id;
    private String dateOfDebt;
    private float fine;

    public DebtFormDTO(String id, String dateOfDebt, float fine) {
        this.id = id;
        this.dateOfDebt = dateOfDebt;
        this.fine = fine;
    }

    public DebtFormDTO(Debt debt){
        this(
            Long.toString(debt.getId()),
            debt.getDateOfDebt().toString(),
            debt.getFine()
        );
    }

    @Override
    public Debt toModel() {
        return null;
    }
}
