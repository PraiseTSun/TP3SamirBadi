package javatown.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateLoanFormDTO {
    private String clientId;
    private String documentId;
    private String dateOfLoan;

    public CreateLoanFormDTO(String clientId, String documentId, String dateOfLoan) {
        this.clientId = clientId;
        this.documentId = documentId;
        this.dateOfLoan = dateOfLoan;
    }
}
