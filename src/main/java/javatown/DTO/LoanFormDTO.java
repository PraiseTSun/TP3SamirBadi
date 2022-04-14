package javatown.DTO;

import javatown.modele.Loan;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class LoanFormDTO implements IFormDTO<Loan> {
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    private String id;
    private String documentName;
    private String dateOfLoan;
    private String dateOfReturn;

    public LoanFormDTO(String id, String documentName, String dateOfLoan, String dateOfReturn) {
        this.id = id;
        this.documentName = documentName;
        this.dateOfLoan = dateOfLoan;
        this.dateOfReturn = dateOfReturn;
    }

    public LoanFormDTO(Loan loan) {
        this(
            Long.toString(loan.getId()),
            loan.getDocument().getTitle(),
            loan.getDateOfLoan().toString(),
            loan.getDateOfReturn().toString()
        );
    }

    @Override
    public Loan toModel() {
        return null;
    }
}
