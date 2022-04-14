package javatown.service;

import javatown.repository.ClientRepository;
import javatown.repository.DebtRepository;
import javatown.repository.LoanRepository;

public class AbstractCommunService {
    protected ClientRepository clientRepository;
    protected LoanRepository loanRepository;
    protected DebtRepository debtRepository;

    public AbstractCommunService(ClientRepository clientRepository, LoanRepository loanRepository, DebtRepository debtRepository) {
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
        this.debtRepository = debtRepository;
    }
}
