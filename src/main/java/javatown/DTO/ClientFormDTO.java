package javatown.DTO;

import javatown.modele.Client;
import javatown.modele.Debt;
import javatown.modele.Loan;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ClientFormDTO extends AbstractUserForm implements IFormDTO<Client>{
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    private String resident;
    private List<LoanFormDTO> loans = new ArrayList<>();
    private List<DebtFormDTO> debts = new ArrayList<>();

    public ClientFormDTO(String id, String firstName, String lastName, String password, String resident) {
        super(id, firstName, lastName, password);
        this.resident = resident;
    }

    public ClientFormDTO(Client client){
        this(
            Long.toString(client.getId()),
            client.getFirstName(),
            client.getLastName(),
            client.getPassword(),
            client.getResident()
        );
        loans = loansDTO(client.getLoans());
        debts = debtsDTO(client.getDebts());
    }

    @Override
    public Client toModel() {
        return new Client(firstName, lastName, password, resident);
    }

    private List<LoanFormDTO> loansDTO (List<Loan> loans){
        List<LoanFormDTO> dto= new ArrayList<>();
        for (Loan loan : loans) {
            dto.add(new LoanFormDTO(loan));
        }

        return dto;
    }

    private List<DebtFormDTO> debtsDTO (List<Debt> debts){
        List<DebtFormDTO> dto= new ArrayList<>();
        for (Debt debt : debts) {
            dto.add(new DebtFormDTO(debt));
        }

        return dto;
    }
}
