package javatown.service;

import javatown.DTO.AdminFormDTO;
import javatown.modele.Administrator;
import javatown.repository.AdminRepository;
import javatown.repository.ClientRepository;
import javatown.repository.DebtRepository;
import javatown.repository.LoanRepository;

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

    
}
