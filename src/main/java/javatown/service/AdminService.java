package javatown.service;

import javatown.DTO.AdminFormDTO;
import javatown.DTO.DebtFormDTO;
import javatown.DTO.LoanFormDTO;
import javatown.modele.Administrator;
import javatown.modele.Debt;
import javatown.modele.Loan;
import javatown.repository.AdminRepository;
import javatown.repository.ClientRepository;
import javatown.repository.DebtRepository;
import javatown.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AdminService extends AbstractCommunService{
    private AdminRepository adminRepository;

    public AdminService(ClientRepository clientRepository, LoanRepository loanRepository, DebtRepository debtRepository, AdminRepository adminRepository) {
        super(clientRepository, loanRepository, debtRepository);
        this.adminRepository = adminRepository;
    }

    public AdminFormDTO createAdmin(String firstName, String lastName, String password) {
        return createAdmin(new Administrator(firstName, lastName, password));
    }

    public AdminFormDTO createAdmin(Administrator admin){
        adminRepository.save(admin);
        return new AdminFormDTO(admin);
    }

    public List<LoanFormDTO> getLoansOfMonth(int month) {
        var loansOpt = loanRepository.findAllByDateOfLoan(month);
        List<Loan> loans = getListOfMonth(loansOpt);
        List<LoanFormDTO> loansDTO = new ArrayList<>();
        for (Loan loan : loans) {
            loansDTO.add(new LoanFormDTO(loan));
        }

        return loansDTO;
    }

    public List<DebtFormDTO> getDebtsOfMonth(int month) {
        var debtsOpt = debtRepository.findAllByDateOfDebt(month);
        List<Debt> debts = getListOfMonth(debtsOpt);
        List<DebtFormDTO> debtsDTO = new ArrayList<>();
        for (Debt debt : debts) {
            debtsDTO.add(new DebtFormDTO(debt));
        }

        return debtsDTO;
    }

    private <T> List<T> getListOfMonth(Optional<List<T>> listOpt){
        if(listOpt.isEmpty())
            return new ArrayList<>();
        return listOpt.get();
    }
}
