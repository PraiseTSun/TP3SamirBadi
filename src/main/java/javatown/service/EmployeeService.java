package javatown.service;

import javatown.repository.*;

public class EmployeeService extends AbstractCommunService{
    private EmployeRepository employeRepository;
    private DocumentRepository documentRepository;

    public EmployeeService(ClientRepository clientRepository, LoanRepository loanRepository, DebtRepository debtRepository, EmployeRepository employeRepository, DocumentRepository documentRepository) {
        super(clientRepository, loanRepository, debtRepository);
        this.employeRepository = employeRepository;
        this.documentRepository = documentRepository;
    }
}
