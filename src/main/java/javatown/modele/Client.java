package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Client extends AbstractUser{
    private String resident;
    @OneToMany(mappedBy = "client")
    private List<Loan> loans;
    @OneToMany(mappedBy = "client")
    private List<Debt> debts;

    public Client(String firstName, String lastName, String password, String resident) {
        super(firstName, lastName, password);
        this.resident = resident;
        this.loans = new ArrayList<>();
        this.debts = new ArrayList<>();
    }

    public boolean isReseidentOfJavatown(){
        return resident.equals("Javatows");
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public Loan getLoanOfId(long loanId) {
        for (Loan loan: loans) {
            if(loan.getId() == loanId) return loan;
        }
        return null;
    }

    public Debt getDebtById(long debtId) {
        for (Debt debt: debts) {
            if(debt.getId() == debtId) return debt;
        }
        return null;
    }
}
