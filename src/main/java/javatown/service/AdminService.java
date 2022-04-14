package javatown.service;

import javatown.repository.ClientRepository;
import javatown.repository.DebtRepository;
import javatown.repository.LoanRepository;

public class AdminService extends AbstractCommunService{
    private AdminService adminService;

    public AdminService(ClientRepository clientRepository, LoanRepository loanRepository, DebtRepository debtRepository, AdminService adminService) {
        super(clientRepository, loanRepository, debtRepository);
        this.adminService = adminService;
    }
}
